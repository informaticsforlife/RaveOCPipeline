package org.ocbn.octools.tools;

import java.util.Objects;
import org.ocbn.octools.util.AttVal;
import org.ocbn.octools.util.GenUtil;

/**
 * An OO encapsulation of an XOC CRF mapping file (currently implemented in 
 * Rave OC mapping). 
 * 
 * @author Rashad Badrawi
 */

public class XOCMapper {
   
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
}
