import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Databse {

	public static void main(String[] args){
		int DeptNr=0;
		try{
			System.out.println("Loading JDMC driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDMC driver successfully loaded!");
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
		Scanner keyboard = new Scanner(System.in);
		try{
			System.out.println("Please enter the department number): ");
			DeptNr=keyboard.nextInt();
		} catch( InputMismatchException err){
			System.out.println("Incorrect input");
		}
		String url = "jdbc:mysql://localhost:3306/employeeschema";
		String username = "root";
		String password = "password";
		String query = "select Name, Gender, DNR" + " from employee where DNR = ?";
		Connection connection = null;
		Statement stmt = null;
		try{
			System.out.println("connecting to the MySQL database....");
			connection = DriverManager.getConnection(url, username,password);
			System.out.println("MySQL Database connected!");
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1,  DeptNr);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				System.out.print(rs.getString(1));
				System.out.print((""));
				System.out.print(rs.getString(2));
				System.out.print((""));
				System.out.println(rs.getInt(3));
			}
			preparedStatement.close();
		}
		catch (SQLException e){
			System.out.println(e.toString());
		} finally{
			System.out.println("Closing the connection.");
			if(connection != null){
				try{
					connection.close();
				}
				catch(SQLException ignore){
					
				}
			}
		}
	}
}
