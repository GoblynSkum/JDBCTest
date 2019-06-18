import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class JDBCTest {
	static String url = "jdbc:odbc:Driver={Oracle73 Ver 2.5}";	
	static Connection con = null;
	static String queryStr= "select SYSDATE from dual";
	static String result = "";
	static Statement stmt;
	public static void main(String[] args) throws Exception {
	    Connection con = getOracleJDBCConnection();
	    if(con!= null){
	       System.out.println("Got Connection.");
	       DatabaseMetaData meta = con.getMetaData();
	       System.out.println("Driver Name : " + meta.getDriverName());
	       System.out.println("Driver Version : " + meta.getDriverVersion());
	       System.out.println(queryStr);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
			    String name = rs.getString("SYSDATE");
			    result+=name;
			}
			System.out.println(result);
	    }else{
		    System.out.println("Could not Get Connection");
	    }
	}

	public static Connection getOracleJDBCConnection(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			} 
		catch(java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		try {
			//con = DriverManager.getConnection(url, userid, password);
			//con = oracle.jdbc("jdbc:odbc:Driver={OracleDriver};DBQ=DATABASE;UID=USER;PWD=PASSWORD");
			con = DriverManager.getConnection("jdbc:odbc:Driver={Oracle73 Ver 2.5};DBQ=DATABASE;UID=USER;PWD=PASSWORD");
		} catch(SQLException ex) {
			System.err.println(" ");
			System.err.println("SQLException: " + ex.getMessage());
		}
		return con;
	}

}

