package multiselect;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TagMouseListener<T> implements MouseListener
{
    Tag<T> tag;
    MultiSelect<T> mulitSelect;

    public TagMouseListener(Tag<T> tag, MultiSelect<T> multiSelect)
    {
        this.tag = tag;
        this.mulitSelect = multiSelect;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        mulitSelect.removeTag(tag);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

};
