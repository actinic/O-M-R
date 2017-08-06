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
        File file = new File("C:\\Users\\sushil\\Documents\\NetBeansProjects\\OpticalMarkRecognition\\Storage\\");
        String[] names = file.list();
        String files[] = new String[50];
        int i = 0;
        for(String name : names)
        {
            if (new File("C:\\Users\\sushil\\Documents\\NetBeansProjects\\OpticalMarkRecognition\\Storage\\" + name).isDirectory())
            {
                files[i]=name;
                System.out.println(i+" "+files[i] +" "+ name);
                i++;
            }
        }
                
    }
}
