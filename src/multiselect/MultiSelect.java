package multiselect;

import java.util.List;
import java.util.Optional;

import javax.swing.JTextField;

public interface MultiSelect<T> {
	
	public void removeTag(Tag<T> tag);
	
	public Optional<T> findItemViaText(String text);
	
	public List<Tag<T>> getTags();
	
	public JTextField getEditor();
	
	public void createLayout();
}
