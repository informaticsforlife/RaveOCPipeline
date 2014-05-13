package org.ocbn.octools.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import org.ocbn.octools.util.AttVal;
import org.ocbn.octools.util.GenUtil;

/**
 * An OO encapsulation of an XOC CRF mapping file (currently implemented in 
 * Rave OC mapping). 
 * The mapping file is of the format: OC-Sheet, OC-Field, X-Sheet, X-Field, 
 * Fixed Value, Comments.
 * It does not allow null values, and uses 'NA's instead. For now, the 
 * assumed mapping is a simple 1:1, and as such, the OC-Sheet/Field combination
 * identify a unique tuple.   
 * 
 * @author Rashad Badrawi
 */

public class XOCMapper {
   
    private static ArrayList <XOCMapper> mapperList = new ArrayList <XOCMapper>();
    private AttVal attValX;
    private AttVal attValOC; 
    private String constVal;
    private String comment; 
    
    public void setAttValX (String categoryName, String fieldName) {
        
        attValX = new AttVal ();
        attValX.setName(categoryName);
        attValX.setVal(fieldName);
    }
    
    public void setAttValOC (String categoryName, String fieldName) {
        
        attValX = new AttVal ();
        attValX.setName(categoryName);
        attValX.setVal(fieldName);       
    }
    
    public void setConstValue (String nConstVal) {
        
        GenUtil.validateString(nConstVal);
        this.constVal = nConstVal;
    }
    
    public void setComment (String nComment) {
        
        GenUtil.validateString(nComment);
        this.comment = nComment;
    }
    
    public AttVal getAttValX () { return this.attValX; }
    
    public AttVal getAttValOC () { return this.attValOC; }
    
    public String getConstValue () { return this.constVal; }
    
    public String getComment () { return this.comment; }
   
    @Override
    public boolean equals (Object o) {
        
        if (!(o instanceof XOCMapper)) {
            throw new IllegalArgumentException ("Invalid instance: " + 
                                                 o.getClass().getName());
        }
        XOCMapper otherMapper = (XOCMapper)o;
        AttVal otherAttValOC = otherMapper.getAttValOC();
        
        return this.getAttValOC().getName().equals (otherAttValOC.getName()) &&
                this.getAttValOC().getVal().equals(otherAttValOC.getVal());
    }

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.attValX);
        hash = 97 * hash + Objects.hashCode(this.attValOC);
        hash = 97 * hash + Objects.hashCode(this.constVal);
        hash = 97 * hash + Objects.hashCode(this.comment);
        
        return hash;
    }
    //mostly for debugging
    @Override
    public String toString () {
        
        String str = "";
        str += getAttValOC().getName() + GenUtil.TAB + 
               getAttValOC().getVal()  + GenUtil.TAB; 
        if (getAttValX() == null) {
            str += GenUtil.NA + GenUtil.TAB + GenUtil.NA + GenUtil.TAB;
        } else {
            str += getAttValX().getName() + GenUtil.TAB + 
                   getAttValX().getVal()  + GenUtil.TAB;    
        }
        if (getConstValue() == null) {
            str += GenUtil.AT + GenUtil.TAB;
        } else {
            str += getConstValue() + GenUtil.TAB;
        }
        if (getComment () == null) {
            str += GenUtil.AT;
        } else {
            str += getComment ();
        }
        
        return str;
    }
    
    public static void initializeMappingFile (String mappingFileName) {
        
        GenUtil.validateString(mappingFileName);
        try {
            BufferedReader br = new BufferedReader (new FileReader (mappingFileName));
            String line;
            String [] headersArr = null;
            while ((line = br.readLine()) != null) {
                XOCMapper mapper;
                line = line.trim();
                String [] tempArr = line.split(GenUtil.TAB);
                if (tempArr.length != 6) {                 //i.e. no code/label
                    throw new IllegalStateException ("Invalid row in mapping file: "
                            + tempArr.length);
                }
                if (headersArr == null) {
                    headersArr = tempArr;  
                    continue;
                }
                //save entries. 
                mapper = new XOCMapper(); 
                mapper.setAttValOC (tempArr [0], tempArr [1]);
                //either both carry values or neither do.
                if (!GenUtil.NA.equals (tempArr [2]) &&    
                    !GenUtil.NA.equals (tempArr [3])) {
                    mapper.setAttValX (tempArr [2], tempArr [3]);
                }
                if (!GenUtil.NA.equals (tempArr [4])) {
                    mapper.setConstValue (tempArr [4]);
                }
                if (!GenUtil.NA.equals (tempArr [5])) {
                    mapper.setComment (tempArr [5]);
                } 
                XOCMapper.mapperList.add(mapper);
            }
        } catch (IOException e) {
            throw new RuntimeException ("Unable to read mapping file."
                                        + mappingFileName);
        }
        //debugging     
        System.out.println ("Listing all code/label pairs: ");
        for (int i = 0; i < XOCMapper.mapperList.size (); i++) {
            System.out.println (XOCMapper.mapperList.get (i));
        }
    }

    public static final ArrayList <XOCMapper> getMapping (String OCCategoryName,
                                                          String OCFieldName) {
    
        XOCMapper dummy = new XOCMapper();
        ArrayList <XOCMapper> tempList = new ArrayList <XOCMapper>();
        dummy.setAttValOC(OCCategoryName, OCFieldName);
        for (int i = 0; i < XOCMapper.mapperList.size (); i++) {
            if (XOCMapper.mapperList.get (i).equals (dummy)) {
                tempList.add (XOCMapper.mapperList.get(i));
            }
        }
        
        return tempList;
    }
}
