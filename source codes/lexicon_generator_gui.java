import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class lexicon_generator_gui extends JFrame implements ActionListener{
	Queue<String> conditional_queue =  new LinkedList<String>();
	String[] multi_arr_conditional = {};
	JLayeredPane layeredPane = new JLayeredPane();
	JLayeredPane layerOpinions = new JLayeredPane();
	JLayeredPane layeredConditional = new JLayeredPane();
	JLayeredPane layeredNonConditional = new JLayeredPane();
	JScrollPane scrollPnOpinion = new JScrollPane();
	JScrollPane scrollPnConditional = new JScrollPane();
	JScrollPane scrollPnNonconditional = new JScrollPane();
	JScrollPane scrollPane = new JScrollPane();
	
	String temp_affix = "";
	String[][] newVal = {};
	JLabel icon_open = new JLabel();
	JLabel iconcond = new JLabel();
	JLabel lbl_input = new JLabel("Enter verb here");
	JLabel explain = new JLabel();
	JComboBox comboBox = new JComboBox();

	JTextArea txt_root = new JTextArea("");
	JTextArea txt_input = new JTextArea("");
	JTextArea txt_tense = new JTextArea("");
	JTextArea txt_aff = new JTextArea("");
	JTextArea txt_infinitive = new JTextArea("");
	ImageIcon iconopen = new ImageIcon("images\\icon-open.png");
	ImageIcon iconopen2 = new ImageIcon("images\\icon-open(2).png");
	ImageIcon iconopin = new ImageIcon("images\\icon-opinions.png");
	ImageIcon iconopin2 = new ImageIcon("images\\icon-opinions(2).png");
	ImageIcon iconNconditional = new ImageIcon(
			"images\\icon-nonconditional.png");
	ImageIcon iconNconditional2 = new ImageIcon(
			"images\\icon-nonconditional(2).png");
	ImageIcon iconConditional = new ImageIcon("images\\icon-conditional.png");
	ImageIcon iconConditional2 = new ImageIcon(
			"images\\icon-conditional(2).png");
    JButton add2lex = new JButton("Add to Lexicon");
    JButton clear = new JButton("Clear Fields");
	
	String[] containerFinal = {};
	String[] subjectives = {};
	String[] subjpos = {};
	String[] non_conditional = {};
	String[][] conditional_GLOBAL = new String[subjectives.length][2];
	JTextArea textArea = new JTextArea();
	int pos_count = 0;
	int neg_count = 0;

	public static JTextArea txtarea = new JTextArea();
	JTable Opinions;
	JTable CONDITIONALS;
	JTable NON_CONDITIONALS;
	JTable rawInput = new JTable();
	JTextArea txtaNonConditional = new JTextArea();
	private JButton btnAnalyze = new JButton("GO");
	private JFrame frmSapfo;
	static JFrame frame = new JFrame();
	/**
	 * Launch the application.
	 * @throws InvocationTargetException 
	 * @throws InterruptedException 
	 */

	public static void main(String[] args) throws InterruptedException, InvocationTargetException{

		try {
			
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		       
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		
		SwingUtilities.invokeAndWait(new Runnable() {
		    public void run() {
				try {
					lexicon_generator_gui window = new lexicon_generator_gui();
					window.frmSapfo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
		
	
	}
	/**
	 * Create the application.
	 */
	public lexicon_generator_gui() {
		initialize();

		layeredPane.setVisible(true);
		layerOpinions.setVisible(false);
		layeredConditional.setVisible(false);
		layeredNonConditional.setVisible(false);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSapfo = new JFrame();
		frmSapfo.setTitle("SAPFO");
		frmSapfo.setBounds(100, 100, 320, 440);
		frmSapfo.setResizable(false);
		frmSapfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSapfo.setLocationRelativeTo(null);
		frmSapfo.getContentPane().setLayout(null);
		frmSapfo.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    }
		});
	
//Label of HEADER===========================================================================================//
		
		ImageIcon imahe = new ImageIcon("images\\header.png");
		JLabel label = new JLabel("", imahe, JLabel.CENTER);
		label.setBounds(0, 0, 314, 81);
		frmSapfo.getContentPane().add(label);	
		
		
//JLayeredPane for CONDITIONAL=================================================================================//
		
		layeredConditional.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredConditional.setForeground(Color.WHITE);
		layeredConditional.setBackground(Color.WHITE);
		layeredConditional.setBounds(-11, 149, 343, 280);
		frmSapfo.getContentPane().add(layeredConditional);
				
		scrollPnConditional.setBounds(22, 13, 292, 205);
		layeredConditional.add(scrollPnConditional);
		String[][] tempConditionals = new String[1][3];
		String[] temp1Conditionals =  {"REVIEW NO.","POLARITY","CONDITIONAL"};
		scrollPnConditional.setViewportView(CONDITIONALS = new JTable(tempConditionals,temp1Conditionals));
		
		final JTextField rownum = new JTextField();
		rownum.setBounds(50, 230, 100,20);
		layeredConditional.add(rownum);
		
		JButton delete = new JButton ("Delete row");
		delete.setEnabled(false);
		delete.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				/*Connection c = null;
			    Statement stmt = null;
			    try {
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
			      System.out.println("Opened database successfully");
			      stmt = c.createStatement();
			      
			      stmt = c.createStatement();
			      String sql = "DELETE from lexicon where id='"+rownum.getText()+"';";
			      stmt.executeUpdate(sql);
			  
			      
			      Connection c1 = null;
					Statement stmt1 = null;
					try{
						
					
					Class.forName("org.sqlite.JDBC");
				    c1 = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
				    c1.setAutoCommit(false);
				    
				      stmt1 = c1.createStatement();
				      ResultSet rs = stmt1.executeQuery( "SELECT * FROM lexicon;" );
				      int ctrRows= 0;
				      while ( rs.next() ) {
				         ctrRows++;
				      }
				      rs.close();
				      stmt1.close();
				      
				      newVal = new String[ctrRows][5];
				      stmt1 = c1.createStatement();
				      ResultSet rs1 = stmt1.executeQuery( "SELECT * FROM lexicon;" );
				      int j = 0;
				      while ( rs1.next() ) {
				    	  newVal[j][0] = rs1.getString("id");
				    	  newVal[j][1] = rs1.getString("input");
				    	  newVal[j][2] = rs1.getString("infinitive");
				    	  newVal[j][3] = rs1.getString("tense");
				    	  newVal[j][4] = rs1.getString("affixes");
				         j++;
				      }
				      rs1.close();
				      stmt1.close();
				      c1.close();  
				      
				      
					}catch( Exception e2 ) {
					      System.err.println( e2.getClass().getName() + ": " + e2.getMessage() );
					      System.exit(0);
					 }
					String[] temp1 = {"Entry no.","Input","Infinitive","Tense","Affixes"};
					scrollPnConditional.setViewportView(CONDITIONALS = new JTable(newVal, temp1 ));
			      
			    } catch ( Exception e1 ) {
			      System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
			      System.exit(0);
			    }*/
			}
		});
		delete.setBounds(170, 230, 100,20);
		//layeredConditional.add(delete);
		
		
//===========================================================================================================//	
				

		
		
//JLayeredPane for OPEN=======================================================================================//
		
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setForeground(Color.WHITE);
		layeredPane.setBackground(Color.WHITE);
		layeredPane.setBounds(-11, 149, 343, 280);
		frmSapfo.getContentPane().add(layeredPane);
		
		txt_input.setBounds(110,22,180,20);
		layeredPane.add(txt_input);
		lbl_input.setBounds(22,10,100,50);
		layeredPane.add(lbl_input);
		
		btnAnalyze.setBounds(250,50, 50, 20);
		btnAnalyze.addActionListener(this);
		layeredPane.add(btnAnalyze);
		
		comboBox.addItem("Select affix used");
		comboBox.addItem("-um- / um-");
		comboBox.addItem("i-/ipag-/-in-/-ni-");
		comboBox.addItem("-in-/in-/-han/-an");
		comboBox.addItem("ma-/na-");
		comboBox.addItem("maka-/naka-");
		comboBox.addItem("-in-/in-/-in");
		comboBox.addItem("magpa-/nagpa-");
		comboBox.addItem("pina-/pa-/pa- -hin/pa- -in");
		comboBox.addItem("mag-/(mag-)-/nag-/(nag-)-");
		
		comboBox.setBounds(110,50, 140, 20);
		comboBox.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Object selected = comboBox.getSelectedItem();
				String selectedString = (String)selected;
				if(selectedString  != "Select affix used"){
				btnAnalyze.setEnabled(true);
				temp_affix = (String) comboBox.getSelectedItem();
				}else{
					//btnAnalyze.setEnabled(false);
				}
				
			}
		});
		comboBox.setEnabled(false);
		//layeredPane.add(comboBox);
		
		txt_infinitive.setBounds(110,84,180,20);
		txt_infinitive.setBackground(Color.GRAY);
		txt_infinitive.setForeground(Color.white);
		txt_infinitive.setEditable(false);
		layeredPane.add(txt_infinitive);
		JLabel lbl_infinitive = new JLabel("Infinitive form");
		lbl_infinitive.setBounds(22,70,100,50);
		layeredPane.add(lbl_infinitive);
		
		txt_tense.setBounds(110,124,180,20);
		txt_tense.setBackground(Color.GRAY);
		txt_tense.setForeground(Color.white);
		txt_tense.setEditable(false);
		layeredPane.add(txt_tense);
		JLabel lbl_tense = new JLabel("Tense of input");
		lbl_tense.setBounds(22,110,100,50);
		layeredPane.add(lbl_tense);
		
		txt_aff.setBounds(110,166,180,20);
		txt_aff.setBackground(Color.GRAY);
		txt_aff.setForeground(Color.white);
		txt_aff.setEditable(false);
		layeredPane.add(txt_aff);
		JLabel lbl_aff = new JLabel("Affix used");
		lbl_aff.setBounds(22,150,100,50);
		layeredPane.add(lbl_aff);
		
		txt_root.setBounds(110,210,180,20);
		txt_root.setBackground(Color.GRAY);
		txt_root.setForeground(Color.white);
		txt_root.setEditable(false);
		layeredPane.add(txt_root);
		JLabel root_word = new JLabel("Root word");
		root_word.setBounds(22,194,100,50);
		layeredPane.add(root_word);
		
		add2lex.setBounds(70, 210, 110, 20); add2lex.setEnabled(false);
		clear.setBounds(190, 210, 110,20); clear.setEnabled(false);
		//layeredPane.add(add2lex);
		//layeredPane.add(clear);
		clear.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				txt_infinitive.setText(" ");
				txt_aff.setText(" ");
				txt_tense.setText(" ");
				txt_input.setText("");
				clear.setEnabled(false);
				comboBox.setSelectedIndex(0);
			}
			
		});
		add2lex.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				add2lex.setEnabled(false);
				Connection c = null;
				Statement stmt = null;
				try{
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
					c.setAutoCommit(false);
					System.out.println("Opened database successfully");
					stmt = c.createStatement();
		      		String sql = "INSERT INTO lexicon (input,infinitive,tense,affixes) " +
		                   "VALUES (" +"'"+ txt_input.getText() +"', "+"'"+ txt_infinitive.getText() +"', "+"'"+ txt_tense.getText() +"', "+"'"+ txt_aff.getText() +"'"+ ");"; 
		      		stmt.executeUpdate(sql);
		      		c.commit();
		      		stmt.close();
		  	        c.close();
				} catch ( Exception e1 ) {
			      System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
			      System.exit(0);
			    }
			}
		});
		
//============================================================================================================//
		
		
//OPEN TAB===================================================================================================//	
		
		JLayeredPane layeredOpen = new JLayeredPane();
		layeredOpen.setBorder(null);
		layeredOpen.setBounds(0, 78, 80, 72);
		frmSapfo.getContentPane().add(layeredOpen);
		
		//open label
		JLabel lblOpen = new JLabel("Input");
		lblOpen.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblOpen.setBounds(20, 49, 32, 14);
		layeredOpen.add(lblOpen);
		
		//image icon
		icon_open = new JLabel("", iconopen2, JLabel.CENTER);
		
		icon_open.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				icon_open.setIcon(iconopen2);
				
				iconcond.setIcon(iconConditional);
				
				layeredPane.setVisible(true);
				layerOpinions.setVisible(false);
				layeredConditional.setVisible(false);
				layeredNonConditional.setVisible(false);
			}
		});
		
		icon_open.setBounds(16, 15, 32, 32);
		layeredOpen.add(icon_open);

//============================================================================================================//
		
		

		
//CONDITIONAL TAB=============================================================================================//	
		
		JLayeredPane layerConditional = new JLayeredPane();
		layerConditional.setBorder(null);
		layerConditional.setBounds(78, 78, 80, 72);
		frmSapfo.getContentPane().add(layerConditional);
		
		//label for conditional tab
		JLabel lblConditional = new JLabel("Lexicon");
		lblConditional.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblConditional.setBounds(7, 49, 46, 14);
		layerConditional.add(lblConditional);
		
		//imageicon
		iconcond = new JLabel("", iconConditional, JLabel.CENTER);
		iconcond.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
				Connection c = null;
				Statement stmt = null;
				try{
					
				
				Class.forName("org.sqlite.JDBC");
			    c = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
			    c.setAutoCommit(false);
			    
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery( "SELECT * FROM lexicon;" );
			      int ctrRows= 0;
			      while ( rs.next() ) {
			         ctrRows++;
			      }
			      rs.close();
			      stmt.close();
			      
			      newVal = new String[ctrRows][5];
			      stmt = c.createStatement();
			      ResultSet rs1 = stmt.executeQuery( "SELECT * FROM lexicon;" );
			      int j = 0;
			      while ( rs1.next() ) {
			    	  newVal[j][0] = rs1.getString("root");
			    	  newVal[j][1] = rs1.getString("input");
			    	  newVal[j][2] = rs1.getString("infinitive");
			    	  newVal[j][3] = rs1.getString("tense");
			    	  newVal[j][4] = rs1.getString("affixes");
			         j++;
			      }
			      rs1.close();
			      stmt.close();
			      c.close();  
			      
			      
				}catch( Exception e2 ) {
				      System.err.println( e2.getClass().getName() + ": " + e2.getMessage() );
				      System.exit(0);
				 }
				String[] temp1 = {"Root","Input","Infinitive","Tense","Affixes"};
				scrollPnConditional.setViewportView(CONDITIONALS = new JTable(newVal, temp1 ));
				icon_open.setIcon(iconopen);
				iconcond.setIcon(iconConditional2);
				
				layeredPane.setVisible(false);
				layerOpinions.setVisible(false);
				layeredConditional.setVisible(true);
				layeredNonConditional.setVisible(false);
				
			}
		});
		iconcond.setBounds(10, 15, 32, 32);
		layerConditional.add(iconcond);
		
//============================================================================================================//		

	}
	public void actionPerformed(ActionEvent arg0){
		txt_aff.setText("");txt_root.setText("");txt_infinitive.setText("");txt_tense.setText("");
		Connection c = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:morphology_lexicon_db.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String temp_input = txt_input.getText();
			temp_input = temp_input.replace("�", "a");
 			temp_input = temp_input.replace("�","e");
 			temp_input = temp_input.replace("�","i");
 			temp_input = temp_input.replace("�","o");
 			temp_input = temp_input.replace("�","u");
 			temp_input = temp_input.replace("'","");
      		String sql = "Select * from lexicon Where input='"+temp_input+"';";
      		
      		ResultSet set = stmt.executeQuery(sql);
      		
      		if(set.next()){
      		txt_aff.setText(set.getString("affixes"));
      		txt_infinitive.setText(set.getString("infinitive"));
      		txt_tense.setText(set.getString("tense"));
      		txt_root.setText(set.getString("root"));
      		
      		
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
      		    	  
	       			
			    }catch (ArrayIndexOutOfBoundsException e) {
     		         System.out.println("Skip"+temp_input);
      		         continue;
      		    } 
			  }
					String[][] temp1 = {um.split("`"),mag.split("`"),magpa.split("`"),maka.split("`"),pa_in.split("`"),i11.split("`"),in.split("`"),an.split("`"),ma.split("`")};
	       			 for (int x = 0; x < temp1.length; x++){
	       				if(!temp1[x][3].equals(" ")){ 
	       					txt_aff.setText(temp1[x][2]);
	       		      		txt_infinitive.setText(temp1[x][0]);
	       		      		txt_tense.setText(temp1[x][1]);
	       		      		txt_root.setText(temp1[x][3]);
	       				}
	       			 }
      			
	       				
      			
      		}
      		c.commit();
      		stmt.close();
  	        c.close();
		} catch ( Exception e1 ) {
	      System.err.println( e1.getClass().getName() + ": " + e1.getMessage() );
	      
	    }
		/*System.out.print(temp_affix);
		 if(this.txt_input.getText().isEmpty()){
			 JOptionPane.showMessageDialog(null, "Input any word", "Warning", 0, iconopen2);
		 }else{
			 System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ ");
			 System.out.println();
			 add2lex.setEnabled(true);
			 clear.setEnabled(true);
			 txt_aff.setText(" ");
			 txt_infinitive.setText(" ");
			 txt_tense.setText(" ");
			 if(temp_affix=="-um- / um-"){
				String[] temp = new String[3];
				String um = in2inf_um.um(txt_input.getText());
				temp = um.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="i-/ipag-/-in-/-ni-"){
		    	String[] temp = new String[3];
				String i = in2inf_i.i(txt_input.getText());
				temp = i.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="-in-/in-/-han/-an"){
		    	String[] temp = {};
				String an = in2inf_an.an(txt_input.getText());
				temp = an.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="ma-/na-"){
		    	String[] temp = new String[3];
				String ma = in2inf_ma.ma(txt_input.getText());
				temp = ma.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="maka-/naka-"){
		    	String[] temp = new String[3];
				String maka = in2inf_maka.maka(txt_input.getText());
				temp = maka.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="-in-/in-/-in"){
		    	String[] temp = new String[3];
				String in = in2inf_in.in(txt_input.getText());
				temp = in.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix=="magpa-/nagpa-"){
		    	String[] temp = new String[3];
				String magpa = in2inf_magpa.magpa(txt_input.getText());
				temp = magpa.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix == "pina-/pa-/pa- -hin/pa- -in"){
		    	String[] temp = new String[3];
				String pa_in = in2inf_pa_in.pa_in(txt_input.getText());
				temp = pa_in.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }else if(temp_affix == "mag-/(mag-)-/nag-/(nag-)-"){
		    	String[] temp = new String[3];
				String mag = in2inf_mag.mag(txt_input.getText());
				temp = mag.split("`");
			    this.txt_infinitive.setText(temp[0]);
			    this.txt_tense.setText(temp[1]);
			    this.txt_aff.setText(temp[2]);
			    JOptionPane.showMessageDialog(null, "It's root word: "+temp[3]);
		    }
			 
	     }*/
		 
	}
	

}
