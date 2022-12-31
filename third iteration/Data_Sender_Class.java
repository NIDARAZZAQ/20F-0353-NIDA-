package DataAccessLayer;
//cannot replace conditional with polymorphism because here I not use condition variables
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import LogicLayer.Bussiness_Logic;

public class Data_Sender_Class {
	//renaming variable
	static String localhoststr="jdbc:mysql://localhost:3306/spell_checker";//pull up field
	static String host="root";
	static String str="";
	static ArrayList Author=new ArrayList();
	static ArrayList Title=new ArrayList();
	static ArrayList Paragraph=new ArrayList();
	static ArrayList word_DB=new ArrayList();
	static ArrayList word_Fre=new ArrayList();
	//these all try statements are combined into one class
	//our catch and try classes by extract classes
public static void insertionTry()//function declartion
{
	//Here we are using extract function
	String str = "";
	try {
    	Connection con = DriverManager.getConnection(localhoststr,host,str);//rename field
    	
    	for(int i=0;i<Paragraph.size();i++) {
    		try {
    		str = "insert into Paragraphs values("+null+",'"+Title.get(i)+"','"+Author.get(i)+"','"+Paragraph.get(i)+"')";
	        PreparedStatement st = con.prepareStatement(str);
	        st.execute();    		
    		}
	        catch(SQLException e) {
		     }
    	}
    	con.close();
    	JOptionPane.showMessageDialog( null,"Data Inserted SuccessFully Table" );
    }
    catch(SQLException e) {
    	System.out.println(e);
    }

}
public static void wordTry() {//function declartion
	//Here we are using extract functions
	String str = "";
	try {
		//extract variable
    	Connection con = DriverManager.getConnection(localhoststr,host,str);
      		for(int i=0;i<word_DB.size();i++) {
    			try {
    		            int Fre=(int) word_Fre.get(i);
    		             String D=(String) word_DB.get(i);
    		str = "insert into word values("+null+",'"+D+"',"+Fre+")";
	        PreparedStatement st = con.prepareStatement(str);
	        st.execute();
        		}
    			catch(SQLException e) {
			    }
    		}
    		
    	con.close();
    	JOptionPane.showMessageDialog( null,"Data Inserted SuccessFully In Word Database" );
    }
    catch(SQLException e) {
    	System.out.println(e);
    }
}
//replace primitives with class
	public static void Paragraph_Data() {//function declartion
		Bussiness_Logic obj=new Bussiness_Logic();
		Author=obj.ReturnAuthor();
		Title=obj.ReturnTitle();
		Paragraph=obj.ReturnParagraph();
		//extract function
		insertionTry();  //inline function
	}
	
	public static void Word_Data() {
		
		Bussiness_Logic obj=new Bussiness_Logic();
		word_DB=obj.ReturnWord();
		word_Fre=obj.ReturnFre();
		ArrayList New=new ArrayList();
    	wordTry();	//inline function
	}

	//pull up method
	public static void Word_Read() throws SQLException {
		ArrayList arr1=new ArrayList();
		ArrayList arr2=new ArrayList();
		ArrayList arr3=new ArrayList();
		Connection con = DriverManager.getConnection(localhoststr,host,str);
		PreparedStatement ps = con.prepareStatement("SELECT * from word ORDER by Frequency");
        
        ResultSet rs = ps.executeQuery();
	      
	      while (rs.next())
	      {
	    	 
	    	 int fre=(int) rs.getObject("Frequency");
	    	 String word=(String) rs.getObject("words");
	          arr2.add(fre);
	          arr3.add(word);
	      }
	      con.close();
	      Bussiness_Logic obj=new Bussiness_Logic();
	      obj.word_Transfer(arr2, arr3);
	}
	
	public static void update(String newword,String preword) throws SQLException {
		Connection con = DriverManager.getConnection(localhoststr,host,str);
		Statement stmt = null;
		System.out.println("Connection is created successfully:");
        stmt = (Statement) con.createStatement();
        String query1 = "update word set words='"+ newword + "' where words like'" + preword + "'";
        stmt.executeUpdate(query1);
		con.close();
		System.out.println("Word updated");
	}
	
}
class pullupfield1 extends Data_Sender_Class{
	static String str="";//push down field
	//we have no function to add so push down method is already applied
}
class pullupfield2 extends Data_Sender_Class{
	static String str="";//push down field
	//we have no function to add so push down method is already applied
}