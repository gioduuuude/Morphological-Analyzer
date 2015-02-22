import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class lexicon_auto_gen {
 public static void main(String[] args){
     JFileChooser chooser = new JFileChooser();
     FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel (*.*)", ".xls", "XLS");
     chooser.setFileFilter(filter);
     int returnVal = chooser.showOpenDialog(null);
 		if(returnVal == JFileChooser.APPROVE_OPTION) {
 			   int i = JOptionPane.YES_NO_OPTION;
 		       if(i == JOptionPane.YES_NO_OPTION)
 			   {
 		    	 
 		    	  File inputWorkbook = new File(chooser.getSelectedFile().getPath());
 		    	  Queue<String> container = new LinkedList<String>();
 		    	    Workbook w;
 		    	    try {
 		    	      w = Workbook.getWorkbook(inputWorkbook);
 		    	      // Get the first sheet
 		    	      Sheet sheet = w.getSheet(0);
 		    	      // Loop over first 10 column and lines

 		    	        	for(int j = 0; j< sheet.getRows(); j++){
 		    	        		Cell cell = sheet.getCell(0, j);
 		    	        		CellType type = cell.getType();
 		    	        		if (type == CellType.LABEL) {
 		    	        			String temp = cell.getContents();
 		    	        			temp = temp.replace("�", "a");
 		    	        			temp = temp.replace("�","e");
 		    	        			temp = temp.replace("�","i");
 		    	        			temp = temp.replace("�","o");
 		    	        			temp = temp.replace("�","u");
 		    	        			temp = temp.replace("'","");
 		    	        			container.add(cell.getContents());
 		    	        			System.out.println(temp);
 		    	        		Connection c = null;
		                                Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String temp_input = temp;
			temp_input = temp_input.replace("�", "a");
 			temp_input = temp_input.replace("�","e");
 			temp_input = temp_input.replace("�","i");
 			temp_input = temp_input.replace("�","o");
 			temp_input = temp_input.replace("�","u");
 			temp_input = temp_input.replace("'","");
      		String sql = "Select * from lexicon Where input='"+temp_input+"';";
      		ResultSet set = stmt.executeQuery(sql);
      		
      		if(set.next()){
                    try{  
                           Connection c1 = null;
		           Statement stmt1 = null;
 		    	   Class.forName("org.sqlite.JDBC");
 		    	   c1 = DriverManager.getConnection("jdbc:sqlite:resultdb1.db");
 		    	   c1.setAutoCommit(false);
 		    	   System.out.println("Opened database successfully");
 		    	   stmt1 = c1.createStatement();
 		    	   String sql1 = "INSERT INTO lexicon (root,input,infinitive,tense,affixes,root_score,input_score,inf_score,tense_score,aff_score) " +
 		    	   "VALUES (" + "'" +set.getString("root") + "', " + "'"+ temp +"', "+"'"+ set.getString("infinitive") +"', "+"'"+ set.getString("tense") +"', "+"'"+ set.getString("affixes") +"',"+" 1, 1, 1, 1, 1"+");"; 
 		    	   stmt1.executeUpdate(sql1);
 		    	   c1.commit();
 		    	   stmt1.close();
 		    	   c1.close();
 		    	   } catch ( Exception e4 ) {
 		    	   System.err.println( e4.getClass().getName() + ": " + e4.getMessage() );
 		    	   System.exit(0);
	            }
      		
      		
      		}else {
      			System.out.println("--------------NOT FOUND IN THE LEXICON---------------");
      			    String mag="";
    				String pa_in="";
    				String magpa ="";
    				String in="";
    				String maka="";
    				String ma="";
    				String an="";
    				String i11="";
    				String um="";
      			for(int o=1; o<=9; o++ ){
      		      try{
      		    	  switch(o){
      		    	 case 1:{ mag = in2inf_mag.mag(temp_input); break;}
      		    	 case 2:{pa_in = in2inf_pa_in.pa_in(temp_input);break;}
      		    	 case 3:{ magpa = in2inf_magpa.magpa(temp_input);break;}
      		    	 case 4:{ in = in2inf_in.in(temp_input);break;}
      		    	 case 5:{ maka = in2inf_maka.maka(temp_input);break;}
      		    	 case 6:{ma = in2inf_ma.ma(temp_input);break;}
      		    	 case 7:{an = in2inf_an.an(temp_input);break;}
      		    	 case 8:{i11 = in2inf_i.i(temp_input);break;}
      		    	 case 9:{um = in2inf_um.um(temp_input);break;}
	       			}
      		    	  
	       			
			    }catch (ArrayIndexOutOfBoundsException e3) {
     		         System.out.println("Skip"+temp_input);
      		         continue;
      		          } 
			  }
		          String[][] temp1 = {um.split("`"),mag.split("`"),magpa.split("`"),maka.split("`"),pa_in.split("`"),i11.split("`"),in.split("`"),an.split("`"),ma.split("`")};
	       			 for (int x = 0; x < temp1.length; x++){
	       				if(!temp1[x][3].equals(" ")){
                                                                
                    try{  
                           Connection c2 = null;
		           Statement stmt2 = null;
 		    	   Class.forName("org.sqlite.JDBC");
 		    	   c2 = DriverManager.getConnection("jdbc:sqlite:resultdb1.db");
 		    	   c2.setAutoCommit(false);
 		    	   System.out.println("Opened database successfully");
 		    	   stmt2 = c2.createStatement();
 		    	   String sql2 = "INSERT INTO lexicon (root,input,infinitive,tense,affixes) " +
 		    	   "VALUES (" + "'" + temp1[x][3] + "', " + "'"+ temp +"', "+"'"+ temp1[x][0] +"', "+"'"+ temp1[x][1] +"', "+"'"+ temp1[x][2] +"'"+ ");"; 
 		    	   stmt2.executeUpdate(sql2);
 		    	   c2.commit();
 		    	   stmt2.close();
 		    	   c2.close();
 		    	   } catch ( Exception e2 ) {
 		    	   System.err.println( e2.getClass().getName() + ": " + e2.getMessage() );
 		    	   System.exit(0);
	            }
	       				}
	       			 }
      			
	       				
      			
      		}
      		c.commit();
      		stmt.close();
  	        c.close();
		} catch ( Exception e1 ) {
	      System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
	      
	    }
 		    	       			  
 		    	       		    
 		    	        		}
 		    	          }
 		    	        
 		    	      
 		    	    } catch (IOException e1) {
 		    	      e1.printStackTrace();
 		    	    } catch (BiffException e1) {
							e1.printStackTrace();
						}
 		    	   String[] containerFinal = {};
 		    	   Object[] containerar = container.toArray();
 		    		}
 		   }
 			 

	      
 }
}
