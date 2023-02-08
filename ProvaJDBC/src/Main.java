import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			  
			  String sql="SELECT employee_id,count(maintenance_work_id) as numero_lavori "
			  		+ "FROM employee_maintenance_work "
			  		+ "GROUP BY employee_id";
			 
			  try(PreparedStatement ps=con.prepareStatement(sql)) {
				 // ps.setString(1, email);
				 // ps.setString(2, pwd);
				  //qui posso utilizzare il PreparedStatement
				  try(ResultSet rs =ps.executeQuery()) {
					//qui posso utilizzare il ResultSet
					  System.out.println("IMPIEGATO\t\tLAVORI");
					  while (rs.next()) { //se c'è qualcosa da leggere
						  System.out.println(
								  rs.getInt("employee_id") +"\t\t\t"+
								  rs.getInt("numero_lavori"));
						 					  
					  } 
				  }
				  
			  }
		  } catch (SQLException ex) {
		     ex.printStackTrace();
		  }

	}

}
