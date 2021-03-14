package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Text2SpeechEditorView {
	
	public Text2SpeechEditorView() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Open file");
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,70,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Text2Speech");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new Text2SpeechEditorView();

	}

}
