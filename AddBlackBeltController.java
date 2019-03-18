package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddBlackBeltController implements Initializable {

	@FXML
	private TextField fName;
	
	@FXML
	private TextField lName;
	
	@FXML
	private TextField guardian;
	
	@FXML
	private TextField age;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField pNumber;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField bBeltDegree;
	
	@FXML
	private TextField numStars;
	
	@FXML
	private Text submitted;
	
	private SqliteConnection db;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.db = new SqliteConnection();		
	}
	
	@FXML
	public void addBlackBeltStudent (ActionEvent event1) throws SQLException
	{
		String firstName = fName.getText();
		String lastName = lName.getText();
		String guard = guardian.getText();
		String studentAge = age.getText();
		String studentAddress = address.getText();
		String studentPhoneNumber = pNumber.getText();
		String studentEmail = email.getText();
		String degree = bBeltDegree.getText();
		String stars = numStars.getText();
		
		String addStudent = "INSERT INTO Students (fname,lname,guardian,age,address,phoneNumber,email) VALUES(?,?,?,?,?,?,?)";
		String addBlackBelt = "INSERT INTO BlackBelt (fName,lName,degree,numOfStars) VALUES(?,?,?,?)";
        try {
        	Connection conn = SqliteConnection.Connector();
			PreparedStatement preparedStatement = conn.prepareStatement(addStudent);
			PreparedStatement preparedStatement2 = conn.prepareStatement(addBlackBelt);
             
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, guard);
            preparedStatement.setString(4, studentAge);
            preparedStatement.setString(5, studentAddress);
            preparedStatement.setString(6, studentPhoneNumber);
            preparedStatement.setString(7, studentEmail);
            preparedStatement.execute();
            preparedStatement.close();
            
            preparedStatement2.setString(1, firstName);
            preparedStatement2.setString(2, lastName);
            preparedStatement2.setString(3, degree);
            preparedStatement2.setString(4, stars);
            preparedStatement2.execute();
            preparedStatement2.close();
            
            submitted.setText("Submitted");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
	}
	
	@FXML
	public void clearForm(ActionEvent event2)
	{
		fName.setText("");
		lName.setText("");
		guardian.setText("");
		age.setText("");
		address.setText("");
		pNumber.setText("");
		email.setText("");
		bBeltDegree.setText("");
		numStars.setText("");
	}
}
