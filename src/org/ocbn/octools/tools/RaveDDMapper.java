package org.ocbn.octools.tools;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.ocbn.octools.util.GenUtil;

/**
 * Utility class for the mapping for Rave DD. 
 * Assumes DD entries in source file are sorted by DataDictionaryName.
 * 
 * @author Rashad Badrawi
 */

public class RaveDDMapper {

    private static ArrayList <RaveDD> RaveDDList = new ArrayList <RaveDD> ();
    
    private RaveDDMapper () {}
    
    private static void addDD (RaveDD DD) {
        
        GenUtil.validateNotNull(DD);
        RaveDDList.add (DD);
    }
    
    public static RaveDD getRaveDD (String DDName) {

        GenUtil.validateString(DDName);
        for (int i = 0; i < RaveDDList.size(); i++) {
            if (RaveDDList.get (i).getName().equals (DDName)) {
                return RaveDDList.get (i);
            }
        }
        System.out.println ("Rave Data Dictionary requested not found: " + DDName);
        
        return null;
    }
         
    public static void initializeMappingFile (String sourceDir, String mappingFileName)
                                              throws IOException {
        
        GenUtil.validateString (sourceDir);
        GenUtil.validateString(mappingFileName);
        try {
            CSVReader br = new CSVReader (new FileReader (new File (sourceDir, 
                                          mappingFileName)));
            String [] headersArr = null;
            String [] tempArr;
            RaveDD rdd = null;
            int cnt = 0;
            while ((tempArr = br.readNext()) != null) {
                if (tempArr.length < 5) {
                    throw new IllegalStateException ("Source Data dictionary "
                    + "file not valid: " + mappingFileName + " " + tempArr.length);
                }
                //debugging
                if (tempArr [0] == null || tempArr [1].length () == 0) {
                    continue;
                }
                if (headersArr == null) {
                    headersArr = tempArr;  
                    continue;
                }
                //if new DD list of entries
                if (rdd == null || !tempArr [0].equals (rdd.getName())) {
                    if (rdd != null) {
                        RaveDDMapper.addDD(rdd);
                        cnt++;
                    }
                    rdd = new RaveDD();
                    rdd.setName (tempArr [0]);
                } 
                rdd.addDDEntry (tempArr [1], Integer.parseInt (tempArr [2]),                    
                                 tempArr [3], tempArr [4]);  
            }
            RaveDDMapper.addDD(rdd);
            cnt++;
            System.out.println ("Read Rave DD Entries: " + cnt);
        } catch (IOException e) {
            throw new RuntimeException ("Unable to read mapping file."
                                        + mappingFileName);
        }
        //debugging  
        /*
        System.out.println ("Listing all code/label pairs: ");
        for (int i = 0; i < RaveDDMapper.RaveDDList.size (); i++) {
            System.out.print (RaveDDMapper.RaveDDList.get (i).toString());
        }
        */
    }
}
