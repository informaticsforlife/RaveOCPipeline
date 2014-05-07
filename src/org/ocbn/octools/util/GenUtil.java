package org.ocbn.octools.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles the general purpose utility functionalities that are not currently
 * worth devoting a class for.  Examples include: sending e-mail notifications, 
 * logging into a server, ...and so on. 
 * 
 * Implements a simple logging/timer until a third party package is used for that. 
 * 
 * @author ocbn
 */

public class GenUtil {
    
    public static final String TAB = "\t";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String AT = "@";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static final String NEWLINE = System.getProperty ("line.separator");
    public static final String SPACE = " ";
    public static final String POUND = "#";
    public static final String PIPE = "|";
    public static final String FORWARD_SLASH = "/";
    public static final String DOUBLE_BACK_SLASH = "\\";
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";
    public static final String LEFT_BRACKET = "[";
    public static final String RIGHT_BRACKET = "]";
    public static final String NA = "N/A";
    public static final String NA2 = "NA";
    public static final String ALL = "ALL";   
    public static final String NOVAL = "";
    
    private static PrintWriter log;
    private static long startTime;
    
    static {
        String fileName = null;
        try {
            fileName = System.getProperty (DefParams.LOG_FILE_PROP);
            if (fileName != null) {
                log = new PrintWriter (fileName);
            } else {
                log = new PrintWriter (DefParams
                    .getDefaultProp (DefParams.LOG_FILE_PROP));
            }
        } catch (FileNotFoundException e) {
            System.err.println ("Unable to initialize log file:" +
                fileName + " " + DefParams.LOG_FILE_PROP);
        }
    }

    private GenUtil () {}                              //no instances allowed

    public static PrintWriter getDefaultLog () { 
    
        log.flush();
        
        return log;
    }
    
    public static String getTimeStamp () {
        
        return Calendar.getInstance ().getTime ().toString ();
    }
    
    public static void registerStart () {
        
        startTime = System.currentTimeMillis ();
    }
    
    public static long getExecTime () {
        
        return (System.currentTimeMillis () - GenUtil.startTime);
    }
    
    public static String getExecTimeStr () {
        
        StringBuilder timingBuf = new StringBuilder ();
        timingBuf.append ("Time app.: ");
        timingBuf.append (getExecTime ());
        timingBuf.append (" msec.");
        timingBuf.append (GenUtil.NEWLINE);
        
        return timingBuf.toString ();
    }

    public static void validateNotNull (Object obj) {

        if (obj == null) {
            throw new IllegalArgumentException ("Invalid value for param: " +
                                                obj);
        }
    }

    public static void validateString (String param) {
        
        if (param == null || param.length () == 0) {
            throw new IllegalArgumentException ("Invalid value for param: " +
                    param);
        }
    }
    
    public static void validatePositiveInt (int param) {
        
        if (param <= 0) {
            throw new IllegalArgumentException ("Invalid value for param: " +
                    param);
        }
    }

    public static void validateNonNegativeInt (int param) {

        if (param < 0) {
            throw new IllegalArgumentException ("Invalid value for param: " +
                    param);
        }
    }
    
    public static void validateBool (String boolStr) {
        
        if (!Boolean.FALSE.toString().equalsIgnoreCase (boolStr) &&
            !Boolean.TRUE.toString().equalsIgnoreCase (boolStr) ) {
            throw new IllegalArgumentException ("Invalid value for argument: "
                                                + boolStr);
        }
    }
    //list helper method
    public static String addList (String tempStr, ArrayList <String> tempList) {
        
        for (int i = 0; i < tempList.size (); i++) {
            if (i > 0) {
                tempStr += GenUtil.SEMICOLON;
            }
            tempStr += tempList.get (i);
        }
        if (tempList.isEmpty()) {
            tempStr += GenUtil.NA;
        }
        
        return tempStr;
    }

    //list helper method: takes a file with a single column string list and
    //returns a filtered file (i.e., no redundant entries)
    public static void filterList (String fileName) throws IOException {

        final String FILTERED = "FILTERED";
        BufferedReader br = new BufferedReader (new FileReader (fileName));
        ArrayList <String> tempList = new ArrayList <> ();
        String line;
        while ((line = br.readLine ()) != null) {
            line = line.trim ();
            if (line.length () == 0) {
                continue;
            }
            if (!tempList.contains (line)) {
                tempList.add (line);
            }
        }
        try (BufferedWriter bw = new BufferedWriter (new FileWriter (fileName +
                GenUtil.UNDERSCORE + FILTERED))) {
            for (int i = 0; i < tempList.size (); i++) {
                bw.write(tempList.get (i));
                bw.newLine();
            }
            bw.flush();
        }
    }
    
    public static ArrayList <String> compareLists (ArrayList <String> l1, 
                                                   ArrayList <String> l2) {
    
        ArrayList <String> tempList = new ArrayList <> ();
        l1 = GenUtil.listToUpper (l1);
        for (int i = 0; i < l2.size (); i++) {
            if (l1.contains (l2.get (i).toUpperCase())) {
                tempList.add (l2.get (i).toUpperCase());
            }
        }
        
        return tempList;
    }
    
    public static ArrayList <String> listToUpper (ArrayList <String> l1) {
        
        ArrayList <String> tempList = new ArrayList <> ();
        for (int i = 0; i < l1.size (); i++) {
            tempList.add (l1.get (i).toUpperCase());            
        }
        
        return tempList;
    }
    
   //utility method
    public static String getStrOrDef(String str) {
   
        if (str != null) {
            return str;
        } else {
            return GenUtil.NOVAL;
        }
    }
}