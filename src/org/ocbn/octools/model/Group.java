package org.ocbn.octools.model;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * Encapsulation of an OC CRF group. 
 * 
 * @author Rashad Badrawi
 */

public class Group extends CRFEntity {
    
    private String layout = ModelCV.OC_CRF_CV_GROUP_LAYOUT_NONR; 
    private String displayStatus = ModelCV.OC_CRF_CV_SHOW;
    private int repeatNum = ModelCV.OC_CRF_GROUP_RNUM_DEFAULT;
    private int repeatMax = ModelCV.OC_CRF_GROUP_RMAX_DEFAULT;
    
    public void setLabel (String nLabel) { this.setIDShort(nLabel); }
    
    public void setHeader (String nHeader) { this.setIDLong(nHeader); }
    
    @Override
    public void setPointer (CRFEntity crf) {
        
        throw new UnsupportedOperationException ("No implementation for: " + 
                                                 "CRF.setPointer ()");
    }
    
    public void setLayout (String nLayout) {
        
        GenUtil.validateString (nLayout);
        this.layout = nLayout;
    }
       
    public void setDisplayStatus (String nDisplayStatus) {
        
        GenUtil.validateString (nDisplayStatus);
        this.displayStatus = nDisplayStatus;
    }
    
    public void setRepeatNum (int nRepeatNum) {
        
        GenUtil.validatePositiveInt(nRepeatNum);
        this.repeatNum = nRepeatNum;
    }
            
    public void setRepeatMax (int nRepeatMax) {

        GenUtil.validatePositiveInt(nRepeatMax);
        this.repeatMax = nRepeatMax;
    }
    
    public String getLabel () { return this.getIDShort(); }
    
    public String getHeader () { return this.getIDLong(); }
        
    @Override
    public CRFEntity getPointer () {
        
        throw new UnsupportedOperationException ("No implementation for: " + 
                                                 "CRF.getPointer ()");
    }
    
    public String getLayout () { return this.layout; }

    public String getDisplayStatus () { return this.displayStatus; }
    
    public int getRepeatNum () { return this.repeatNum; }
    
    public int getRepeatMax () { return this.repeatMax; }
    
    @Override
    public boolean equals (Object o) {
        
        if (!(o instanceof Group)) {
            throw new IllegalArgumentException ("Invalid instance type: " +
                                                 o.getClass().getName());
        }
        return this.getLabel().equals (((Group)o).getLabel ());
    }

    @Override
    public int hashCode() {
        
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.getLabel());
        
        return hash;
    }

    
    public static final String toStringHeaders () {
        
        return ModelCV.OC_GROUPS_GROUPLABEL + GenUtil.TAB + 
               ModelCV.OC_GROUPS_GROUPL + GenUtil.TAB + 
               ModelCV.OC_GROUPS_GROUPH + GenUtil.TAB + 
               ModelCV.OC_GROUPS_GROUPRN + GenUtil.TAB + 
               ModelCV.OC_GROUPS_GROUPRM + GenUtil.TAB + 
               ModelCV.OC_GROUPS_GROUPDS;
    }
    
    @Override
    public String toString () {
        
        String str = this.getLabel() + GenUtil.TAB + 
                     this.getLayout() + GenUtil.TAB +
                     GenUtil.getStrOrDef(this.getHeader()) + GenUtil.TAB +      
                     this.getRepeatNum() + GenUtil.TAB +
                     this.getRepeatMax() + GenUtil.TAB +
                     this.getDisplayStatus();
        
        return str;
    }
}
