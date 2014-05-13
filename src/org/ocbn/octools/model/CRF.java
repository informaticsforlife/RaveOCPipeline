package org.ocbn.octools.model;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * The root/container class for a CRF. 
 * 
 * @author Rashad Badrawi
 */

public class CRF extends CRFEntity {

    private String version; 
    private String versionDesc;
    
    public void setName (String nName) { this.setIDShort(nName); }

    public void setRevNotes (String nRevNotes) { this.setIDLong(nRevNotes); }
    
    @Override
    public void setPointer (CRFEntity crf) {
        
        throw new UnsupportedOperationException ("No implementation for: " + 
                                                 "CRF.setPointer ()");
    }
    
    public void setVersion (String nVersion) {
        
        GenUtil.validateString (nVersion);
        this.version = nVersion;
    }
    
    public void setVersionDesc (String nVersionDesc) {
        
        GenUtil.validateString (nVersionDesc);
        this.versionDesc = nVersionDesc;
    }
    
    public String getName () { return this.getIDShort(); }
    
    public String getRevNotes () { return this.getIDLong(); }
    
    @Override
    public CRFEntity getPointer () {
        
        throw new UnsupportedOperationException ("No implementation for: " + 
                                                 "CRF.getPointer ()");
    }
    
    public String getVersion () { return this.version; }
    
    public String getVersionDesc () { return this.versionDesc; }
   
    @Override
    public boolean equals (Object o) {
        
        if (!(o instanceof CRF)) {
            throw new IllegalArgumentException ("Invalid instance type: " + 
                                                o.getClass().getName());
        }
        return this.getName().equals (((CRF)o).getName ());
    }

    @Override
    public int hashCode() {
        
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.getName());
        return hash;
    }

    public static final String toStringHeaders () {
        
        return ModelCV.OC_CRF_NAME + GenUtil.TAB + 
               ModelCV.OC_CRF_VERSION + GenUtil.TAB + 
               ModelCV.OC_CRF_VERSIOND + GenUtil.TAB + 
               ModelCV.OC_CRF_REVISIONN;
    }
    
    @Override
    public String toString () {
        
        String str = getName () + GenUtil.TAB + 
                     getVersion () + GenUtil.TAB +
                     getVersionDesc() + GenUtil.TAB + 
                     getRevNotes();
        
        return str;
    }
}
