import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
	  Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:resultdb1.db");
	      System.out.println("Opened database successfully");
	      stmt = c.createStatement();
	      
	     String sql = "CREATE TABLE lexicon" +
	                   "(root              TEXT," +
	                   " input           TEXT, " + 
	                   " infinitive      TEXT, " + 
	                   " tense       	 TEXT, " + 
	                   " affixes         VARCHAR(30)," +
                           " root_score     INT," +
                           " input_score     INT," + 
                           " inf_score       INT," +
                           " tense_score     INT," +
                           " aff_score       INT)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();

	      c.close();
	     
	      //DELETE
	      /*stmt = c.createStatement();
	      String sql = "DELETE from lexicon where input='tumatae';";
	      stmt.executeUpdate(sql);
	      c.commit();*/
	      /*
	      //INSERT
	      stmt = c.createStatement();
      		String sql1 = "INSERT INTO lexicon (input,infinitive,tense,affixes) " +
                   "VALUES ('sd','df','gh','jk' );"; 
      		stmt.executeUpdate(sql1);
	       
	      */
          /*READ
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM lexicon;" );
	      while ( rs.next() ) {
	         String  input = rs.getString("input");
	         //String  infinitive = rs.getString("infinitive");
	        // String  tense = rs.getString("tense");
	         //String  affixes = rs.getString("affixes");
	         System.out.println(input);
	      }
	      rs.close();
	      stmt.close();
	      c.close();*/
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
  }
}