package org.ocbn.octools.util;

/**
 * Extended Att-Val representation, inclusive of a label. 
 * 
 * @author ocbn
 */

public class AttValLabel extends AttVal {

    private String valLabel;
    
    public void setValLabel (String nValLabel) {
        
        GenUtil.validateString(nValLabel);
        this.valLabel = nValLabel;
    }
    
    public String getValLabel () { return this.valLabel; }
    
    @Override
    public String toString () { 
    
        return super.toString() + GenUtil.TAB + this.getValLabel();
    }
}
