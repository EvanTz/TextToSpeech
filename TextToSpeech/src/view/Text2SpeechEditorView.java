package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import commands.*;
import model.Document;

public class Text2SpeechEditorView implements ActionListener{
	private String encoding = "Atbash";
	private OpenDocument openDoc;
	private SaveDocument saveDoc;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox<String> encodingsList;
	private JButton button;
	private JButton saveButton;
	private JScrollPane scroll;
	private JLabel encodingLabel;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == encodingsList) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
	        encoding = (String)cb.getSelectedItem();
	        if(encoding != null) {
	        	openDoc.setEncoding(encoding);
	        	saveDoc.setEncoding(encoding);
	        }
	        else {
	        	openDoc.setEncoding("None");
	        	saveDoc.setEncoding("None");
	        }
		}
	}
	
	public Text2SpeechEditorView() {
		String[] encodings = {"None", "Atbash", "Rot13"};
		encoding = encodings[0];
		CommandsFactory comFactory = new CommandsFactory();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel textPanel = new JPanel();	
		

		JButton button = new JButton("Open file");

		button.setBounds(2,2,2,2);  

		JButton saveButton = new JButton("Save File");

		button = new JButton("Open file");
		saveButton = new JButton("Save File");
		encodingLabel = new JLabel("Encoding:");
		encodingsList = new JComboBox<String>(encodings);

		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		
		model = new DefaultTableModel();
		model.setColumnCount(2);
		model.setRowCount(2);
		table = new JTable(model);
		
		scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(false);
		
		openDoc = (OpenDocument) comFactory.createCommand("OpenDocument");
		openDoc.setTextArea(textArea);
		openDoc.setTableModel(model);
		openDoc.setTable(table);
		openDoc.setScroll(scroll);
		openDoc.setFrame(frame);
		openDoc.setEncoding(encoding);
		
		button.addActionListener(openDoc);
		
		saveDoc = (SaveDocument) comFactory.createCommand("SaveDocument");
		saveDoc.setDocument(openDoc.getDocument());
		saveDoc.setTextArea(textArea);
		saveDoc.setTableModel(model);
		saveDoc.setTable(table);
		saveDoc.setEncoding(encoding);
		saveDoc.setScroll(scroll);
		
		saveButton.addActionListener(saveDoc);
		
		encodingsList.addActionListener(this);

		
		textPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		textPanel.setLayout(new GridLayout(1,0));
		panel.add(button);
		panel.add(saveButton);
		panel.add(encodingLabel);
		panel.add(encodingsList);
		textPanel.add(scroll);
		frame.add(panel, BorderLayout.PAGE_START);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Text2Speech");
		frame.setPreferredSize(new Dimension(800,400));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		new Text2SpeechEditorView();

	}

}
