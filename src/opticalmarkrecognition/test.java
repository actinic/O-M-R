/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package opticalmarkrecognition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;



/**
 *
 * @author sushil
 */
public class test {
    
    
    
    public static void main(String[] args) throws IOException, SQLException  {
        DBHelper dbh = new DBHelper();
        String res = dbh.tableExist("sushil");
        System.out.println(res);
    }
}
