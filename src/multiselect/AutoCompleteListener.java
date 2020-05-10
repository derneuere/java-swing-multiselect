package multiselect;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AutoCompleteListener extends KeyAdapter
{
    private JComboBox<String> comboBox;
    // This list contains the "real" results from your query etc.
    private List<String> allResult = new ArrayList<>();
    private boolean shouldHide;

    public AutoCompleteListener(JComboBox<String> combo)
    {
        super();
        this.comboBox = combo;
        buildList();
    }

    /**
     * Wenn Ergebnisse nachgeladen wurden, können sie über diese Methode eingespeist werden.
     *
     * @param newResult neue Resultate als List<String>
     */
    public void updateResults(List<String> newResult)
    {
        allResult = newResult;
        String str = ((JTextField) comboBox.getEditor().getEditorComponent()).getText();
        comboBox.setModel(new DefaultComboBoxModel(newResult.toArray()));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(str);
        checkAndShow(str);
    }

    @Override
    public void keyTyped(final KeyEvent e)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                String text = ((JTextField) e.getComponent()).getText();
                checkAndShow(text);
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        JTextField textField = (JTextField) e.getComponent();
        String text = textField.getText();
        shouldHide = false;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                for (String s : allResult)
                {
                    if (s.startsWith(text))
                    {
                        textField.setText(s);
                        return;
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!allResult.contains(text))
                {
                    allResult.add(text);
                    Collections.sort(allResult);
                    setSuggestionModel(comboBox, getSuggestedModel(allResult, text), text);
                }
                shouldHide = true;
                break;
            case KeyEvent.VK_ESCAPE:
                shouldHide = true;
                break;
            default:
                break;
        }
    }

    /**
     * Schaut nach ob String in der Liste ist, generiert und setzt das SuggestionModel.
     *
     * @param text - Text, der gesucht wird
     */
    private void checkAndShow(String text)
    {
        ComboBoxModel<String> m;
        if (text.isEmpty())
        {
            String[] array = allResult.toArray(new String[allResult.size()]);
            m = new DefaultComboBoxModel<>(array);
            setSuggestionModel(comboBox, m, "");
            comboBox.hidePopup();
        }
        else
        {
            m = getSuggestedModel(allResult, text);
            if (m.getSize() == 0 || shouldHide)
            {
                comboBox.hidePopup();
            }
            else
            {
                setSuggestionModel(comboBox, m, text);
                comboBox.showPopup();
            }
        }
    }

    private static void setSuggestionModel(
            JComboBox<String> comboBox, ComboBoxModel<String> mdl, String str)
    {
        comboBox.setModel(mdl);
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(str);

    }

    private static ComboBoxModel<String> getSuggestedModel(List<String> list, String text)
    {
        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (String s : list)
        {
            if (s.toLowerCase().startsWith(text.toLowerCase()))
            {
                m.addElement(s);
            }
        }
        return m;
    }

    private void buildList()
    {
        allResult = new ArrayList<>();
        for (int i = 0; i < comboBox.getModel().getSize(); i++)
        {
            allResult.add(comboBox.getItemAt(i));
        }
    }
}
