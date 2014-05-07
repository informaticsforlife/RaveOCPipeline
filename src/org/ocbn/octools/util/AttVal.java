package org.ocbn.octools.util;

/**
 * Generic att-val class. 
 * 
 * @author Rashad Badrawi
 */

public class AttVal {
    
    private String name; 
    private String val; 
    
    public void setName (String nName) {
        
        GenUtil.validateString(nName);
        this.name = nName; 
    }
    
    public void setVal (String nVal) {
        
        GenUtil.validateString(nVal);
        this.val = nVal; 
    }
        
    public String getName () { return this.name; }
    
    public String getVal () { return this.val; }
    
    @Override
    public String toString () { return this.getVal (); }
}
