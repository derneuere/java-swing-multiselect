package example;

import javax.swing.JFrame;

public class App {
	
	public static void main(String[] args){
        JFrame frame = new JFrame();
        MultiSelectExample example = new MultiSelectExample("Test");
        frame.setTitle("Example Multiselect");
        frame.add(example);
        frame.pack();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
