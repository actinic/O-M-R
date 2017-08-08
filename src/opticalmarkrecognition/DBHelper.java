/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package opticalmarkrecognition;

/**
 *
 * @author Sushil
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.drda.NetworkServerControl;
import java.io.IOException;



public class DBHelper {

    static Statement stmt;
     static NetworkServerControl server = null;
    
    static Connection conn;
    /**
     * @param args the command line arguments
     */
   
        // TODO code application logic here
        public DBHelper(){
            
                     try {
            server = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
            server.start(null);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
}
         try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Failed "+ ex);
        }
        //conn = null;
    if(conn==null){
        
    
    try {
    conn =
       DriverManager.getConnection("jdbc:derby://localhost:1527/omrDB","root","root");

    stmt = conn.createStatement();
    
  
    } catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
    }
    
          
    }

         

    void testing(String qry) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
            stmt.executeUpdate(qry);
        }  catch(SQLException e){
            
            System.out.println("Error"+e);
           
        }
        
        
    }      

    ResultSet returnResultTotal(String tableName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        
        ResultSet rs = stmt.executeQuery("Select * from "+tableName);
        
    return(rs);
            }catch(SQLException e){
                System.out.println(e);
                
            
            return(null);
            }
    }

    String[] getXname() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
            ResultSet rs = stmt.executeQuery("select * from ROOT.SUSHIL");
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount(); //number of column
            String columnName[] = new String[count];
            String[] result = new String[count];

            for (int i = 1; i <= count; i++)
            {
            columnName[i-1] = metaData.getColumnLabel(i);
            result[i-1] =columnName[i-1];
            //System.out.println(columnName[i-1]);
            
            }
            for (int i = 1; i <= count-1; i++)
            {
            System.out.println(result[i]);
            
            }
            
            return null;
        }  catch(SQLException e){
            
            System.out.println("Error"+e);
           
        }
        return null;
    }

    ResultSet getXnamevalue(String filename) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
            //String upperFilename = filename.toUpperCase();
            System.out.println("filename ="+filename);
            String query = "select * from "+filename;
            System.out.println(query);
            ResultSet rs = stmt.executeQuery("select * from defence");          
            return rs;
        }  catch(SQLException e){   
            System.out.println("Error"+e);           
        }
        return null;
    }

    int count(String key, String value,String filename) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String qry = "SELECT  COUNT("+key+")FROM ROOT."+filename+" where "+key+" = '"+value+"'";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    int counttest(String sex, String female, String filename) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String qry = "SELECT  COUNT("+sex+")FROM ROOT."+filename+" where "+sex+" = '"+female+"'";
        ResultSet rs = stmt.executeQuery(qry);
        while (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
    
    String processQRY(String qry, String colName){
        String val = null;
        try {
            ResultSet rs = stmt.executeQuery(qry);
            while(rs.next()){                
                val = rs.getString(colName);   
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    public String getLastId(String query) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String num = null;
        int lastid = 0;
        String productCode=null;
        int key=0;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            ResultSet resultSet = statement.executeQuery("select * from sushil");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productCode = resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            num = "-1";
        }
    return productCode;
    }

    String tableExist(String tableName) throws SQLException {
        //String tableName = "sushila";
        tableName = tableName.toUpperCase();
        DatabaseMetaData dbm = conn.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        if (tables.next()) {
        return "true";
        }else {
        return "false";
        }
    }

    
    
    
}