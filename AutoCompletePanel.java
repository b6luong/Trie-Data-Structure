import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class AutoCompletePanel extends JPanel {
	
	//Change this variable!!
	private static final String SOURCEFOLDER = "C://";
	
	private static final Color BGCOLOR = new Color(129, 192, 249);
	private JLabel title;
	private JTextField textField;
	private JTextArea textArea;
	private JButton clrButton;
	private JScrollPane scrollPane;
	private Trie trie;
	private String CurrentString = "";
	private JPanel topPanel = new JPanel();
	private JPanel contentPanel = new JPanel();
	
	class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String word = textField.getText();
			if (!CurrentString.equals(word)){
				CurrentString = word;
				textArea.setText("");
				trie.print(word, textArea);
			}
		}
	}
	
	public AutoCompletePanel() throws FileNotFoundException{
		trie = new Trie();
		
		Scanner in = null;
		try{
			in = new Scanner(new File(SOURCEFOLDER + "dictionary"));
			while (in.hasNextLine())
				trie.insert(in.nextLine());
		}
		catch(FileNotFoundException e){
			throw new FileNotFoundException("NO FILE FOUND, Please check SOURCEFOLDER");
		}
		finally{
			if (in != null)
				in.close();
		}
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10,10,10,10));
		setBackground(BGCOLOR);
		
		contentPanel.setLayout(new BorderLayout());
		
		title = new JLabel("Auto-Complete");
		title.setFont(new Font("Serif", Font.BOLD, 40));
		add(title);
		
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(BGCOLOR);
		
		JLabel label = new JLabel("Enter a word: ");
		label.setFont(new Font("Serif", Font.BOLD, 16));
		topPanel.add(label);
		
		textField = new JTextField(25);
		topPanel.add(textField);
		
		clrButton = new JButton("Clear");
		clrButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				textField.setText("");
			}
		});
		topPanel.add(clrButton);
		
		contentPanel.add(topPanel, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		add(contentPanel);
		
		Timer timer = new Timer(500, new TimerListener());
		timer.start();
	}
}
