package org.ocbn.octools.ocmodel;

import org.ocbn.octools.util.GenUtil;

/**
 * Root for all OC CRF entities, type control and allows unified identifying. 
 * For now, this OID represents an OID from the converted CRF. 
 * 
 * @author Rashad Badrawi
 */

public abstract class CRFEntity {
 
    protected String OID; 
    
    public void setOID (String nOID) {
        
        GenUtil.validateString(nOID);
        this.OID = nOID;
    }
    
    public String getOID () { return this.OID; }
    
    @Override
    public String toString () {
        
        return this.getOID();
    }
}
