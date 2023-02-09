import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		 String url = "jdbc:mysql://localhost:3306/areoporto";
		  String user = "root";
		  String password = "B00lean_2022";
		  Scanner s=new Scanner(System.in);
		
		 
		
		  try (Connection con = DriverManager.getConnection(url, user, password)){
			  //qui posso utilizzare il Connection
			 /* System.out.print("Inserisci la tua mail: ");
			  String email=s.nextLine();
			  System.out.print("Inserisci la tua password: ");
			  String pwd=s.nextLine();*/
			  con.setAutoCommit(false);
			  
			  
			  String sql="update airline_employee SET layoff_date=current_date() "+
					  "where id=?";
			 
			  try(PreparedStatement ps=con.prepareStatement(sql)) {
				  ps.setInt(1, 31);			
				  ps.executeUpdate();
			  }
			  
			  //qui si verificherà un errore
			  sql="insert into airline_employee(employee_id, airline_id, hiring_date)"
			  		+ " Values(?, ?, current_date()) ";
			 
			  try(PreparedStatement ps=con.prepareStatement(sql)) {
				  ps.setInt(1, 9);		
				  ps.setInt(2, 270);
				  ps.executeUpdate();
			  }
			  con.commit();
			  System.out.println("Trasferimento effettuato");
		  } catch (SQLException ex) {
			// con.rollback();
		     ex.printStackTrace();
		  }
		  s.close();
		}
	

}
