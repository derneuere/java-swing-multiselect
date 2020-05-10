package multiselect;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Tag<T> extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	JButton close;
    JLabel label;
    T object;
    MultiSelect<T> p;

    public Tag(T object, MultiSelect<T> p)
    {
        this.p = p;
        this.object = object;
        createElements();
        createLayout();
        createListener();
    }

    void createElements()
    {
        label = new JLabel();
        close = new JButton();
        Image image = getImage();
		
        ImageIcon resizedIcon = new ImageIcon(getScaledImage(image, 15, 15));
        ImageIcon rolloverIcon = new ImageIcon(getRolloverImage(image, 15, 15, 0.5f));
        close.setIcon(resizedIcon);
        close.setRolloverIcon(rolloverIcon);
        close.setBorderPainted(false);
        close.setBorder(null);
        close.setMargin(new Insets(0, 0, 0, 0));
        close.setContentAreaFilled(false);
        close.setPreferredSize(new Dimension(15, 15));
        close.setSize(new Dimension(15, 15));
        label.setText(object.toString());
    }
    
    Image getImage() {
    	try {
			return ImageIO.read(getClass().getResource("cross.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    void createListener()
    {
        close.addMouseListener(new TagMouseListener<T>(this, p));
    }

    void createLayout()
    {
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
	    gbc.gridy = 0; 
	    add(label, gbc);
	    
	    gbc.gridx = 1; 
	    gbc.gridy = 0; 
	    add(close,gbc);
	
    }

    private Image getRolloverImage(Image srcImg, int w, int h, float opacity)
    {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private Image getScaledImage(Image srcImg, int w, int h)
    {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension arcs = new Dimension(5, 5); // Border corners arcs {width,height}, change this to whatever you want
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draws the rounded panel with borders.
        graphics.setColor(UIManager.getColor("Button.background"));
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint border
    }

    public T getObject()
    {
        return object;
    }
}
