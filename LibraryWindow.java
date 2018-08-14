package LibraryInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * 
 * @author 10887523 Seungwoo Hong
 * 
 * LibraryWindow Class, almost working as a main of this program, has 
 * three inner class which are AddWindow, OpenWindow, and SaveWindow. 
 * They work when adding a book, opening new library, and saving the 
 * current library list.
 * 
 * 
 * 
 * ::::::::::::::: Functions :::::::::::::::
 * 
 * ::: Menu Bar		- Open : opens new library
 * 							- Save :  saves the current list
 *							- Exit   : terminates the program
 *
 *
 *::: Add				- when clicking the button, another window will be displayed
 *							  so as to fill out the information of new book. You can check
 *							  out the book right after adding.
 *
 *
 *::: Search			- Fill out the keyword that you want to find at the text field on
 *							  the bottom. You may check out the result by an additional 
 *							  comment at the front of every single book that has the keyword.
 *							  You may also see a window with "No Result for the Keyword"
 *							  unless the list contains the keyword.
 *
 *
 */

public class LibraryWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	
	Library CurrentLibrary = new Library();
	JList CurrentBooklist = new JList();
	AddWindow newbook;
	static final DefaultListModel ListModel = new DefaultListModel();
	
	
	public class AddWindow extends JFrame implements ActionListener{
		/**
		 * An Inner Class of Library Window
		 */
		private static final long serialVersionUID = 1L;
		public static final int WIDTH = 300;
		public static final int HEIGHT = 400;
		
		Book addedBook;//an temporary object, moving the data from the textfields to InputBook.
		Book InputBook;//an book object to store new book's information from text fields.
		
		JPanel MainPanel=new JPanel();
		JPanel ButtonPanel=new JPanel();
		final JTextField Title = new JTextField("Title.",20);
		final JTextField ListofAuthors = new JTextField("ListofAuthors.",20);
		final JTextField CatalogNumber = new JTextField("CatalogNumber.",20);
		final JTextField SubjectHeadings = new JTextField("Subject.",20);
		final JTextField Publisher = new JTextField("Publisher.",20);
		final JTextField PublicationYear = new JTextField("PublicationYear.",20);
		final JTextField circulating = new JTextField("circulating? (ONLY true or false)",20);
		final JButton SubmitButton  = new JButton("Submit");
		
		public AddWindow() {
			
			super ("Add");
			setSize(WIDTH, HEIGHT);
			setLayout(new BorderLayout());
			
			
			Dimension MainPanelSize=new Dimension(300,350);
			MainPanel.setPreferredSize(MainPanelSize);	
			Dimension ButtonPanelSize=new Dimension(300,50);
			ButtonPanel.setPreferredSize(ButtonPanelSize);
			
			
			MainPanel.add(Title);
			MainPanel.add(ListofAuthors);
			MainPanel.add(CatalogNumber);
			MainPanel.add(SubjectHeadings);
			MainPanel.add(Publisher);
			MainPanel.add(PublicationYear);
			MainPanel.add(circulating);
			ButtonPanel.add(SubmitButton);
			
			this.add(MainPanel, BorderLayout.CENTER);
			this.add(ButtonPanel, BorderLayout.SOUTH);
			
			SubmitButton.addActionListener(this);
			//the information of a book will be transmitted when clicking the button
			
			setVisible(true);
			
		}	
		
		public Book getBook() {
			return InputBook;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String SubmitButton= e.getActionCommand( );
			
			if ( SubmitButton.equals("Submit") ) {
				
				addedBook = new Book(Title.getText());
				String inputListofAuthors = ListofAuthors.getText();
				addedBook.setListofAuthors(inputListofAuthors);
				String catalog = CatalogNumber.getText();
				addedBook.setCatalogNumber( Integer.parseInt(catalog) );
				String inputSubject = SubjectHeadings.getText();
				addedBook.setSubjectHeadings(inputSubject);			
				addedBook.setPublisher(Publisher.getText());
				String Pubyear = PublicationYear.getText();
				addedBook.setPublicationYear(Integer.parseInt(Pubyear));
				String inputCir = circulating.getText();
				if(inputCir.equals(false))
					addedBook.setcirculating(false);
				else if (inputCir.equals(true))
					addedBook.setcirculating(true);	
				
				
				if(	addedBook != null) {
					InputBook = addedBook;//to make sure any information is not empty
					
					ListModel.addElement(		"Title : " + Title.getText() +"    Authors : "+ ListofAuthors.getText()+"    Catalog No. : " + CatalogNumber.getText() +
							"	    Subject :  "+SubjectHeadings.getText()+"    Publisher : " + Publisher.getText()+"    Pub' Year : "+ PublicationYear.getText() +
							"	    Cir : "+circulating.getText());// The information of the book is displayed in the list.
					
					new AddCompletePopUp();
					dispose();
				}
			}
		}
	}
	
	public class OpenWindow extends JFrame {

		/**
		  * An Inner Class of Library Window
		 */
		private static final long serialVersionUID = 1L;
		public static final int WIDTH = 300;
		public static final int HEIGHT = 500;
		
		public OpenWindow() throws InterruptedException {
			
			super ("The File is Opened");
			setSize(WIDTH, HEIGHT);
			setLayout(new BorderLayout());
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			fileChooser.setFileFilter(filter);//it only reads text files
			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
					ListModel.clear();//cleaning up the previous list only to display the current one
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
					fileChooser.cancelSelection();
			    
					try {
						BufferedReader br = new BufferedReader(new FileReader( 	selectedFile ));
						String line = null;
						while ( true ) {	
							line = br.readLine( );
							if (line == null) break;// break at the end of the list
							ListModel.addElement(line);		
						}//reads the line till there is no line to read
				    
						br.close( );
					}
					catch(FileNotFoundException e) {
							System.out.println("Problem opening files.");
					}
					catch (IOException e) {
							System.err.println("Error: " + e);
					}
			}
			
			this.add(fileChooser, BorderLayout.CENTER);
			setVisible(true);
			Thread.sleep(1000);
			this.dispose();//the window is automatically closed in a second when the saving is done.
		}	
	}
	
	public class SaveWindow extends JFrame {

		/**
		  * An Inner Class of Library Window
		 */
		private static final long serialVersionUID = 1L;
		public static final int WIDTH = 300;
		public static final int HEIGHT = 500;
		
		public SaveWindow() throws InterruptedException {
			
			super ("The File has been saved");
			setSize(WIDTH, HEIGHT);
			setLayout(new BorderLayout());
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
			fileChooser.setFileFilter(filter);
			int result = fileChooser.showSaveDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
		        try {
		            FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".txt");
		            
		            for(int index=0; index < ListModel.getSize() ; index++ ) {
		            		String saveline = (String) ListModel.getElementAt(index);
		            		fw.write(   saveline +"\n"  );  		
		            }
		            
		            fw.flush();
		            fw.close();
		        } 
		        catch (Exception ex) {
		            ex.printStackTrace();
		        }  
		    }
			
			setVisible(true);
			Thread.sleep(1000);
			this.dispose();//the window is automatically closed in a second when the saving is done.
		}	
		
	}
	
	
	public LibraryWindow() {
		
		super ("Library  Program");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		//Main Window's Layout
		JPanel MainPanel=new JPanel();
		Dimension MainPanelSize=new Dimension(600,750);
		MainPanel.setPreferredSize(MainPanelSize);
		JPanel ButtonPanel=new JPanel();
		Dimension ButtonPanelSize=new Dimension(600,50);
		ButtonPanel.setPreferredSize(ButtonPanelSize);
		
		MainPanel.setBackground(Color.WHITE);
		ButtonPanel.setBackground(Color.GRAY);
		
		this.add(MainPanel, BorderLayout.CENTER);
		this.add(ButtonPanel, BorderLayout.SOUTH);
		
		
		
		//Main Panel :: Main Panel with the List's Layout
		MainPanel.setLayout(new BorderLayout());
		CurrentBooklist.setModel(ListModel);
		for(int i=0;  CurrentLibrary.list[i] !=null  ;i++) {
			ListModel.addElement(CurrentLibrary.list[i].toString());
		}
		JScrollPane pane = new JScrollPane(CurrentBooklist);
		MainPanel.add(pane);
		
		
		
		//Bottom Panel :: Bottom buttons and Search Text's Layout
		ButtonPanel.setLayout(new BorderLayout());
		final JButton AddButton  = new JButton("Add");
		ButtonPanel.add(AddButton, BorderLayout.WEST);
		AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	newbook = new AddWindow();
            }
        });
		JPanel SearchtextPanel = new JPanel(new FlowLayout());
		final JTextField SearchInputField = new JTextField("Enter the Subject here.",35);
		SearchInputField.setBackground(Color.WHITE);
		SearchtextPanel.add(SearchInputField);
		ButtonPanel.add(SearchtextPanel, BorderLayout.CENTER);
		
		final JButton SearchButton  = new JButton("Search");
		ButtonPanel.add(SearchButton, BorderLayout.EAST);
		SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String find = SearchInputField.getText(); 
            	int findnumbers = 0;// the number of keyword's being found
                        for (int index = 0; index < ListModel.getSize(); index++) {
                        	String Line = (String) ListModel.getElementAt(index);
                        	if( Line.contains(find) ) { // to check the line contains the keyword
                        		ListModel.setElementAt("******Your Search : " +find+ " ****** " 	+ ListModel.getElementAt(index) , index);
                        		findnumbers++;
                        	}     		         
                        }
                 if(findnumbers == 0) new NoKeywordPopUp();//if the keyword couldn't being found in the list
            }
        });
		
		
		
		//Menu :: Menu Bar Setting
		JMenu Menu = new JMenu("Menu");
		JMenuItem OpenChoice = new JMenuItem("Open");
		OpenChoice.addActionListener(this);
		Menu.add(OpenChoice);
		JMenuItem SaveChoice = new JMenuItem("Save");
		SaveChoice.addActionListener(this);
		Menu.add(SaveChoice);
		JMenuItem ExitChoice = new JMenuItem("Exit");
		ExitChoice.addActionListener(this);
		Menu.add(ExitChoice);
		JMenuBar bar = new JMenuBar( );
		bar.add(Menu);
		setJMenuBar(bar);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) { //Menu Bar Options
		
		String MenuButton= e.getActionCommand( );
		
		if (MenuButton.equals("Open")) {
			try {
				new OpenWindow();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
			
		else if (MenuButton.equals("Save")) {
			try {
				new SaveWindow();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
			
		else if (MenuButton.equals("Exit")) 
			System.exit(0);
	}// Menu Options	
}










