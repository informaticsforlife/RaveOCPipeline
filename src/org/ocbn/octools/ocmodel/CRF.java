package org.ocbn.octools.ocmodel;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * The root/container class for a CRF. 
 * 
 * @author Rashad Badrawi
 */

public class CRF extends CRFEntity {

    private String name; 
    private String version; 
    private String versionDesc;
    private String revNotes;
    
    public void setName (String nName) {
        
        GenUtil.validateString(nName);
        this.name = nName;
    }
    
    public void setVersion (String nVersion) {
        
        GenUtil.validateString (nVersion);
        this.version = nVersion;
    }
    
    public void setVersionDesc (String nVersionDesc) {
        
        GenUtil.validateString (nVersionDesc);
        this.versionDesc = nVersionDesc;
    }
    
    public void setRevNotes (String nRevNotes) {
        
        GenUtil.validateString(nRevNotes);
        this.revNotes = nRevNotes;
    }
    
    public String getName () { return this.name; }
    
    public String getVersion () { return this.version; }
    
    public String getVersionDesc () { return this.versionDesc; }
    
    public String getRevNotes () { return this.revNotes; }
    
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
        
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.version);
        hash = 71 * hash + Objects.hashCode(this.versionDesc);
        hash = 71 * hash + Objects.hashCode(this.revNotes);
        
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
