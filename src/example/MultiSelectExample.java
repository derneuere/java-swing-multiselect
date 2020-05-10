package example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import multiselect.MultiSelect;
import multiselect.AutoCompleteListener;
import multiselect.Tag;
import multiselect.TagEditorListener;
import multiselect.WrapLayout;

public class MultiSelectExample extends JPanel implements MultiSelect<String>
{
	private static final long serialVersionUID = 1L;
	
	private JLabel tagNameText;
    JPanel tagPanel;
      
    private JTextField editor;
    private List<String> elements;
    private JComboBox<String> comboBox;
    private List<Tag<String>> tags;
    

    public MultiSelectExample(String name)
    {
        tags = new ArrayList<>();
        elements = new ArrayList<>();
        this.setName(name);
        tagPanel = new JPanel();
        createElements();
        createLayout();
    }

    private void createElements()
    {
    	elements.add("firstString");
    	elements.add("important");
        tagNameText = new JLabel("Elements:");
        comboBox = new JComboBox<>();
        elements.forEach(i -> tags.add(new Tag<String>(i, this)));
        elements.forEach(i -> comboBox.addItem(i.toString()));
        comboBox.setEditable(true);
        comboBox.setSelectedIndex(-1);
        editor = (JTextField) comboBox.getEditor().getEditorComponent();
        editor.setText("*");
        editor.addKeyListener(new AutoCompleteListener(comboBox));
        editor.addKeyListener(new TagEditorListener<String>(this));
    }

    public void createLayout()
    {
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0; 
	    gbc.gridy = 0; 
	    add(tagNameText, gbc);
	    
        tagPanel.setSize(500, 100);
        tagPanel.setLayout(new WrapLayout());
        for (Tag<String> tag : tags)
        {
            tagPanel.add(tag);
        }
        tagPanel.add(comboBox);
        gbc.gridx = 0; 
	    gbc.gridy = 1; 
	    add(tagPanel,gbc);
        validate();
        repaint();
    }
    
    @Override
    public void removeTag(Tag<String> removedTag)
    {
        tagPanel.remove(removedTag);
        tags = tags.stream().filter(i -> !i.equals(removedTag)).collect(Collectors.toList());
        createLayout();
    }
    
    @Override
    public JTextField getEditor()
    {
        return editor;
    }

    public void setEditor(JTextField editor)
    {
        this.editor = editor;
    }
    
    @Override
    public List<Tag<String>> getTags()
    {
        return tags;
    }

    public void setTags(List<Tag<String>> tags)
    {
        this.tags = tags;
    }

	@Override
	public Optional<String> findItemViaText(String text) {
	for (String e : elements)
	{
        if (e.toLowerCase().contains(text.toLowerCase()))
        {
            return Optional.of(e);
        }
	}
    return Optional.empty();
	}
}


