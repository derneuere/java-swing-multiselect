package example;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class App {
	
	public static void main(String[] args){
		List<String> elements = new ArrayList<>();
		elements.add("Berlin");
    	elements.add("London");
    	elements.add("Madrid");
    	elements.add("Rome");
    	elements.add("San Francisco");
    	elements.add("Austin");
    	elements.add("New York");
		
        JFrame frame = new JFrame();
        StringMultiSelect example = new StringMultiSelect("Citys:", elements, new ArrayList<>());
        frame.setTitle("Example Multiselect");
        frame.add(example);
        frame.pack();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
