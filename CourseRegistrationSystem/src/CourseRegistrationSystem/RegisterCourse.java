package CourseRegistrationSystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;


//inheritance by extending JFrame class.
public class RegisterCourse extends JFrame implements ActionListener{

	// variables that used in this application
	private  JTextField id ,password;
	private   JComboBox course;
	private   JLabel SelectCourse;
	private  JList<String> CourseList;
	private   JButton Register;
	private   ArrayList<String> StudentsList= new ArrayList<String>();
	private  String FirstName,LastName,StudentId,StudentPassword;
	private  DefaultListModel<String> RegisterCourses;
	private  ArrayList<String> Courses= new ArrayList<String>();
	private  ArrayList<String> StudentCoursesData= new ArrayList<String>();
	private   ArrayList<String> StudentCourses= new ArrayList<String>();
		
	private   ArrayList<String> AllStudentCoursesData= new ArrayList<String>();
	private   String  AllCourse[]={"Programing Fundamentals,5.5 months","ICT,5.5 months","OOP,5.5 months","Theory of automaton,5 months","Calculus,4.5 months","Probability,6 months",
			 "Pre-Calculus,4 months","Database System,4 months","Web Engineering,4 months","System Engineering,4 months",
			 "C Sharp,4 months","Visual Pragramming,4 months","Algorthim Analysis,3 months","Orginazational Behaviour,3 months"};
	  
	 
	 
	 // main method
	public static void main(String[] args) {
		
		// calling first window using its object
		new RegisterCourse();

	}
	
	  
	 /***
	  * There are three constructors of Register Course class 
	  * 1-RegisterCourse(String Framename, String id,String password,String firstName,String lastName);
	  * 2-RegisterCourse();
	  * 3-RegisterCourse(String name);
	  * here i achieved constructor overloading.
	  * ***/
	RegisterCourse(String Framename, String id,String password,String firstName,String lastName){
		
			super(Framename);
			Image icon = Toolkit.getDefaultToolkit().getImage("C:\\\\Users\\\\nigeetawadhwani\\\\eclipse-workspace\\\\CourseRegistrationSystem\\\\CourseRegistrationSystem\\\\books.jpg");
			setIconImage(icon);
			setSize(800,600);	
			setContentPane(new JLabel(new ImageIcon("C:\\Users\\nigeetawadhwani\\eclipse-workspace\\CourseRegistrationSystem\\CourseRegistrationSystem\\books.jpg")));

			// setting values of global variables
			this.StudentId=id;
			this.StudentPassword=password;
			this.FirstName=firstName;
			this.LastName=lastName;
			
			// adding controlls to frame
			JButton RegisterNew =new JButton("View New Courses");
			RegisterNew.setBounds(200,40,200,30);
			add(RegisterNew);
			RegisterNew.addActionListener(this);
		
			JButton Registered =new JButton("View Registered Courses");
			Registered.setBounds(410,40,200,30);
			add(Registered );
			Registered.addActionListener(this);
						
			JLabel name = new JLabel("Name: "+firstName);
			name.setBounds(15,3,600,80);
			name.setFont(new Font("TimesRoman", Font.BOLD, 18));
	        add(name);
	        
	        SelectCourse = new JLabel("Select Course");
			SelectCourse.setBounds(100,200,150,40);
			SelectCourse.setFont(new Font("TimesRoman", Font.BOLD, 20));
			SelectCourse.setVisible(false);
			add(SelectCourse);
	        
			// adding courses in Courses list
			for(String item :this.AllCourse) {
				
				this.Courses.add(item);
			}
			
			course= new JComboBox(Courses.toArray());
			course.setBounds(260, 200, 200, 30);
			course.setVisible(false);
			add(course);
			
			Register = new JButton("Register");
			Register.setBounds(260, 240,150, 30);
			Register.setVisible(false);
			Register.addActionListener(this);
			add(Register);
			RegisterCourses = new DefaultListModel<>(); 
			try {
				FileReader fileReader = new FileReader("RegisterdCourses.csv"); //opening csv file
				BufferedReader bufferedReader = new BufferedReader(fileReader); //to store data into buffer from csv file.
				String line = null;
				while( (line = bufferedReader.readLine()) != null){ // while there is data in buffer continue read operation.
					
					StudentCoursesData.add(line); //adding each line to list			
				}
				for(int i=0;i<StudentCoursesData.size();i++) {
					
					String[] values=StudentCoursesData.get(i).split(",");
					if(values[0].equals(this.StudentId)) {
						this.StudentCourses.add(values[1]+","+values[2]);
						RegisterCourses.addElement(values[1]+" : "+values[2]);
					}
					
				}
				fileReader.close();
		       	bufferedReader.close();
			}catch(Exception ae) {
				ae.printStackTrace();
				
			}			
			CourseList = new JList<>(RegisterCourses);  
		    CourseList.setFont(new Font("TimesRoman", Font.BOLD, 15));
		    CourseList.setBounds(160,90,300,350); 
		    CourseList.setVisible(false);
		    add(CourseList);
		   
	        JButton logOut= new JButton("Log Out");
	        logOut.setBounds(30,500,100,50);
	        add(logOut);
	 	    logOut.addActionListener(this);
		    setVisible(true);
	        setLayout(null);
	        
	        
	        
	}
	
    RegisterCourse(){
		
		setSize(800,500);	
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\nigeetawadhwani\\eclipse-workspace\\CourseRegistrationSystem\\CourseRegistrationSystem\\books.jpg")));
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\\\Users\\\\nigeetawadhwani\\\\eclipse-workspace\\\\CourseRegistrationSystem\\\\CourseRegistrationSystem\\\\books.jpg");
		setIconImage(icon);
		 JLabel name = new JLabel("Course Registration System");
			name.setBounds(10,10,600,80);
			name.setFont(new Font("TimesRoman", Font.BOLD, 40));
	        add(name);
	        
		JLabel login_id = new JLabel("User ID");
		login_id.setBounds(150,120,150,40);
		login_id.setFont(new Font("TimesRoman", Font.BOLD, 20));
		add(login_id);

		  id = new JTextField();
         id.setBounds(150,170,300,30);
         add(id);


		JLabel password_label = new JLabel("Password");
		password_label.setBounds(150,200,100,40);
		password_label.setFont(new Font("TimesRoman", Font.BOLD, 20));
		add(password_label);
		
		 password = new JPasswordField();
		password.setBounds(150,240,300,30);
		add(password);
		
		JButton login =new JButton("Login");
		login.setBounds(150,290,300,30);
		add(login);
		login.addActionListener(this);

		JButton ViewALlRegistrations= new JButton("View All Registrations");
		ViewALlRegistrations.setBounds(550,30,160,50);
		ViewALlRegistrations.addActionListener(this);
		add(ViewALlRegistrations);
		setVisible(true);
        setLayout(null);
		
	}
   
   
	RegisterCourse(String name){
		super(name);
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\\\Users\\\\nigeetawadhwani\\\\eclipse-workspace\\\\CourseRegistrationSystem\\\\CourseRegistrationSystem\\\\books.jpg");
		setIconImage(icon);
		setSize(800,600);	
		
		try {
			
			FileReader fileReader = new FileReader("RegisterdCourses.csv"); //opening csv file
			BufferedReader bufferedReader = new BufferedReader(fileReader); //to store data into buffer from csv file.
			String line = null;
			String column[]= new String[3];
			 
			while( (line = bufferedReader.readLine()) != null){ // while there is data in buffer continue read operation.
				
				AllStudentCoursesData.add(line); //adding each line to list			
			}
			String	Completelist[][]= new String[AllStudentCoursesData.size()-1][];
			for(int i=0;i<AllStudentCoursesData.size();i++) {
				String[] values=AllStudentCoursesData.get(i).split(",");
			    if(i==0) {
			    	
			    	column=values;
			    }
			    else {
			    	Completelist[i-1]= new String[3];
			    	Completelist[i-1]=values;
			    }
			}
			
			fileReader.close();
	       	bufferedReader.close();
	       	// adding table in frame
	       	JTable data=new JTable(Completelist,column);    
		    data.setBounds(30,40,200,300);          
		    JScrollPane sp=new JScrollPane(data);    
		    add(sp);
		    
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		  
		
		setVisible(true);
        setLayout(null);
	}
	
	/***
	 * there are private method to validate data fields and view, and add courses
	 * Abstration concept of OOP
	 * Private methods
	 * 1-ValidateData(); 
	 * 2-ViewNewCourses();
	 * 3-AddCourse();
	 * */
	
	private boolean ValidateData() throws IOException {
		
			FileReader fileReader = new FileReader("Students.csv"); //opening csv file
			BufferedReader bufferedReader = new BufferedReader(fileReader); //to store data into buffer from csv file.
			String line = null;
		
			while( (line = bufferedReader.readLine()) != null){ // while there is data in buffer continue read operation.
				StudentsList.add(line); //adding each line to list
			}
			
			
			for(int i=1;i<StudentsList.size();i++) {
				
			    String[] values=StudentsList.get(i).split(",");
			   
			    if(values[0].equals(this.id.getText()) && values[1].equals(this.password.getText())) {
			    
			       	this.FirstName=values[2];
			       	this.LastName=values[3];
			       	fileReader.close();
			       	bufferedReader.close();
			       	return true;
			       	
			    }
			}
			
			return false;
	}
	
	
	
	private void ViewNewCourses() {
	
		for(String item : this.AllCourse) {
			
			if(this.StudentCourses.contains(item)) {
				
				this.Courses.remove(item);
			}
		}
		
	}
	
	
	private void AddCourse()throws IOException {
		System.out.println("in add course");
		FileWriter fw = new FileWriter("RegisterdCourses.csv",true);
		PrintWriter pw = new PrintWriter(fw);
		String str="\n"+this.StudentId+","+this.course.getSelectedItem().toString();
		pw.printf("%s", str);
		pw.close();
		fw.close();
		
	}
	
	/*
	 * overriden method of jframe,setIconImage
	 * where title is also set with frame icon 
	 * **/
	@Override
	public void setIconImage (Image image) {
		
		 super.setTitle("Course Registration System");
	      super.setIconImage(image);    
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	JButton btn= (JButton)ae.getSource();
	
	switch(btn.getText()) {
		case "Login":
			boolean isValid=false;
		    try {
				isValid=ValidateData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(isValid) {
				this.dispose();
				new RegisterCourse("Student Profile",id.getText(),password.getText(),this.FirstName,this.LastName);
			}else
				JOptionPane.showMessageDialog(this,"Id or Password is not valid");
			break;
		case "View New Courses":
			
			CourseList.setVisible(false);
			ViewNewCourses();
			SelectCourse.setVisible(true);
			course.setVisible(true);
			Register.setVisible(true);
			break;
			
		case "View Registered Courses":
			SelectCourse.setVisible(false);
			course.setVisible(false);
			Register.setVisible(false);
			CourseList.setVisible(true);
			break;
			
		case "Register":
			this.RegisterCourses.addElement(this.course.getSelectedItem().toString());
			try {
				AddCourse();
				JOptionPane.showMessageDialog(this,"New Course Registered");
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "View All Registrations":
			new RegisterCourse("View All Registrations");
		break;
		case "Log Out":
			this.dispose();
			new RegisterCourse();
			break;
		
	}
		
	}
}
