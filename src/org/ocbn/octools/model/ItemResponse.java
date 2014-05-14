package org.ocbn.octools.model;

import org.ocbn.octools.util.GenUtil;

/**
 * A sub-entity for an Item that focuses on the Response field attributes. 
 * Could be defined as an inner class within Item, but defined as public for 
 * convenience only. 
 * responseOptions (& values) att can be re-factored to support data dictionaries.
 * 
 * @author Rashad Badrawi
 */

public class ItemResponse {
    
    private String responseType;
    private String responseLabel;
    private String responseOptions;
    private String responseValues;
    private String responseLayout;
    private String defValue;
    private String dataType; 
    private String decWidth;
    private String valid;
    private String validErrMsg;
 
    public void setResponseType (String nResponseType) {
        
        if (!ModelCV.isValidOCResponseType(nResponseType)) {
            throw new IllegalArgumentException ("Invalid OC-CRF response type: "
                                                + nResponseType);
        }
        this.responseType = nResponseType;
    }
    
    public void setResponseLabel (String nResponseLabel) {
        
        GenUtil.validateString(nResponseLabel);
        this.responseLabel = nResponseLabel;
    }
        
    public void setResponseOptions (String nResponseOptions) {
        
        GenUtil.validateString(nResponseOptions);
        this.responseOptions = nResponseOptions;
    }
            
    public void setResponseValues (String nResponseValues) {
        
        GenUtil.validateString(nResponseValues);
        this.responseValues = nResponseValues;
    }
                
    public void setResponseLayout (String nResponseLayout) {
        
        if (!ModelCV.isValidOCResponseLayout(nResponseLayout)) {
            throw new IllegalArgumentException ("Invalid OC response layout: " +
                                                nResponseLayout);
        }
        this.responseLayout = nResponseLayout;
    }
                    
    public void setDefValue (String nDefValue) {
        
        GenUtil.validateString(nDefValue);
        this.defValue = nDefValue;
    }
    
    public void setDataType (String nDataType) {
        
        if (!ModelCV.isValidOCDataType(nDataType)) {
            throw new IllegalArgumentException ("Invalid OC datatype: " +
                                                 nDataType);
        }
        this.dataType = nDataType;
    }
 
    public void setDecWidth (String nDecWidth) {
        
        GenUtil.validateString (nDecWidth);
        this.decWidth = nDecWidth;
    }
    
    public void setValid (String nValidStr) {
        
        GenUtil.validateString (nValidStr);
        this.valid = nValidStr;
    }
    
    public void setValidErrMsg (String nValidErrMsg) {
        
        GenUtil.validateString (nValidErrMsg);
        this.validErrMsg = nValidErrMsg;
    }
            
    public String getResponseType () { return this.responseType; }
    
    public String getResponseLabel () { return this.responseLabel; }
    
    public String getResponseOptions () { return this.responseOptions; }
    
    public String getResponseValues () { return this.responseValues; }
    
    public String getResponseLayout () { return this.responseLayout; }
    
    public String getDefValue () { return this.defValue; }
    
    public String getDataType () { return this.dataType; }
    
    public String getDecWidth () { return this.decWidth; }
    
    public String getValid () { return this.valid; }
    
    public String getValidErrMsg () { return this.validErrMsg; }
    
    //utility method to process validations for greater/less than.
    public static final String buildValidStr (String ln, String gn, String ncl, String 
                                     ncu) {
        String validStr1 = getLnGnExp (ln, gn);
        String validStr2 = getLnGnExp (ncl, ncu);
        if (validStr1 != null && validStr2 != null) {
            return validStr1 + GenUtil.SPACE + 
                   GenUtil.AND + GenUtil.AND + GenUtil.SPACE + 
                    validStr2;
        } else if (validStr1 != null && validStr2 == null) {
            return validStr1;
        } else if (validStr1 == null && validStr2 != null) {
            return validStr2;
        } else {                      //both null, no validation params passed.
            return null;
        }
    }
    
    private static String getLnGnExp (String lt, String gt) {
               
        if ( (lt != null && lt.length () > 0) && 
             (gt != null && gt.length () > 0) ) {
            return ModelCV.OC_VALID_RANGE + "(" + 
                   Double.parseDouble (lt) + GenUtil.COMMA + 
                   Double.parseDouble (gt) + ")";
        } else if ( (lt != null && lt.length () > 0) && 
                    (gt == null || gt.length () == 0) ) {
            return ModelCV.OC_VALID_LT + "(" + Double.parseDouble (lt) + ")";
        } else if ( (lt == null || lt.length () == 0) && 
                    (gt != null && gt.length () > 0) ) {
            return ModelCV.OC_VALID_GT + "(" + Double.parseDouble (gt) + ")";
        } else {                        //no validation range.
            return null;
        }
    }
    
    public static final String toStringHeaders () {
        
        return ModelCV.OC_ITEMS_RESPONSET + GenUtil.TAB + 
               ModelCV.OC_ITEMS_RESPONSEL + GenUtil.TAB + 
               ModelCV.OC_ITEMS_RESPONSEOT + GenUtil.TAB + 
               ModelCV.OC_ITEMS_RESPONSEV + GenUtil.TAB + 
               ModelCV.OC_ITEMS_RESPONSELAYOUT + GenUtil.TAB +
               ModelCV.OC_ITEMS_DEFAULTV + GenUtil.TAB + 
               ModelCV.OC_ITEMS_DATAT + GenUtil.TAB + 
               ModelCV.OC_ITEMS_DEC_WID + GenUtil.TAB + 
               ModelCV.OC_ITEMS_VALID + GenUtil.TAB + 
               ModelCV.OC_ITEMS_VALID_ERR;
    }
        
    @Override
    public String toString () {
        
       String str = this.getResponseType() + GenUtil.TAB + 
                    GenUtil.getStrOrDef(this.getResponseLabel()) + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getResponseOptions()) + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getResponseValues()) + GenUtil.TAB + 
                    GenUtil.getStrOrDef(this.getResponseLayout()) + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getDefValue()) + GenUtil.TAB +
                    this.getDataType() + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getDecWidth()) + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getValid()) + GenUtil.TAB +
                    GenUtil.getStrOrDef(this.getValidErrMsg());
            
       return str;
    }
}