package multiselect;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TagEditorListener<T> implements KeyListener
{
    MultiSelect<T> p;

    public TagEditorListener(MultiSelect<T> p)
    {
        this.p = p;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (p.findItemViaText(p.getEditor().getText()).isPresent())
            {
                Object o = p.findItemViaText(p.getEditor().getText()).get();
                if (p.getTags().stream().map(Tag::getObject).filter(i -> i.equals(o)).count() <= 0)
                {
                    p.getTags().add(new Tag(o, p));
                }
            }
            p.createLayout();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
