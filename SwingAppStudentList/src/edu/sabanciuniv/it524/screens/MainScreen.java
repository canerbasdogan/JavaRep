package edu.sabanciuniv.it524.screens;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import edu.sabanciuniv.it524.parsers.CSVParser;
import edu.sabanciuniv.it524.parsers.TSVParser;

public class MainScreen extends JFrame {
	
	private JButton fileReadButton;
	private JTextArea fileContentArea;
	private JScrollPane fileContentScrollPane;
	private JTextField fileNameField;
	private JButton fileSaveButton;
	private JButton dbSaveButton;
	
	
	public MainScreen()
	{
		this.setTitle("SwingAPP Mini Project");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 450);
		this.setLocationRelativeTo(null);
		
		this.fileReadButton = new  JButton("Read File ...");
		this.fileReadButton.setLocation(280, 20);
		this.fileReadButton.setSize(100, 25);
		this.getContentPane().add(fileReadButton);

		this.fileNameField = new JTextField();
		this.fileNameField.setLocation(20, 20);
		this.fileNameField.setSize(250, 25);
		this.getContentPane().add(fileNameField);
		
		this.fileContentArea = new JTextArea();
		this.fileContentArea.setLocation(20, 60);
		this.fileContentArea.setSize(360, 250);
		
		this.fileContentScrollPane = new JScrollPane();
		this.fileContentScrollPane.getViewport().setView(this.fileContentArea);
		this.fileContentScrollPane.setLocation(20, 60);
		this.fileContentScrollPane.setSize(360, 250);
		this.getContentPane().add(fileContentScrollPane);
		
		this.fileReadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			        jfc.setPreferredSize(new Dimension(400, 400));
			        int returnValue = jfc.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = jfc.getSelectedFile();
			            fileNameField.setText(selectedFile.getAbsolutePath());
			            
			            if (fileNameField.getText().contains(".csv")==true)
			            {
			            	CSVParser csv = new CSVParser();
			            	
			            	fileContentArea.append(csv.readFile(fileNameField.getText()));

			            	
			            }
			            if (fileNameField.getText().contains(".tsv")==true)
			            {
			            	TSVParser tsv = new TSVParser();
			            	fileContentArea.append(tsv.readFile(fileNameField.getText()));
			            	
			            }
	         
			        }
			}
		});
		
		int buttonStartXLoc = 80;
		int buttonStartYLoc = 320;
		int buttonHeight = 25;
		int buttonWidth = 250;
		//Dosyayı kaydetme butonunu ekliyoruz
		this.fileSaveButton = new  JButton("Save File (output.txt)");
		this.fileSaveButton.setLocation(buttonStartXLoc, buttonStartYLoc);
		this.fileSaveButton.setSize(buttonWidth, buttonHeight);
		this.getContentPane().add(fileSaveButton);
		
		this.fileSaveButton.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked (MouseEvent e) {
				File newFile = new File("output.txt");
				try {
					FileWriter fileWriter = new FileWriter(newFile);
					fileWriter.write(fileContentArea.getText());
					fileWriter.close();
					
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
				
			}
			
		});

		this.dbSaveButton = new  JButton("Save Database (Students table)");
		this.dbSaveButton.setLocation(buttonStartXLoc, buttonStartYLoc+(2*buttonHeight));
		this.dbSaveButton.setSize(buttonWidth, buttonHeight);
		this.getContentPane().add(dbSaveButton);
		
		this.dbSaveButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked (MouseEvent e) {
				
				String url;
				String password;
				String username;
				
				url = "jdbc:mysql://localhost/deneme";
				username = "root";
				password = "1234";
				
				try {
					
					Connection conn = DriverManager.getConnection(url, username, password);
					System.out.println("Connected to Database...");
					PreparedStatement ps = conn.prepareStatement("insert into students (id, name, lastname) values (?, ?, ?)");
					
					String studentsFromFileContentArea = fileContentArea.getText();
					
					for (String line : studentsFromFileContentArea.split("\n")) {
						
						String [] array1 = line.split(" ");
						
						if (line.equals("\n") || line.equals(" ")) {
							continue;
						}
						
						if (line.split(" ").length > 3) {
							ps.setInt(1, Integer.parseInt(array1[0]));
							ps.setString(2, array1[1] + " " + array1[2]);
							ps.setString(3, array1[3]);
							ps.execute();
							
						}
						else {
							ps.setInt(1, Integer.parseInt(array1[0]));
							ps.setString(2, array1[1]);
							ps.setString(3, array1[2]);
							ps.execute();
							
						}
						
					}
					
					System.out.println("Inserted to Database...");
					
				} catch (SQLException e1) {
					System.out.println("Error...");
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}
	
}
