package org.ocbn.octools.model;

import org.ocbn.octools.util.GenUtil;

/**
 * Root for all OC CRF entities, type control and allows unified identifying.
 * Like most entities, it encapsulates a brief identifier (aka name, label), 
 * usually required and unique, and a detailed/long identifier (aka description, 
 * header, ...etc). It also includes a self-pointer which has its implementations
 * in some subclasses but not all, but is always a useful placeholder. Most
 * of the attributes in this class are indirectly accessed through wrapper 
 * accessors in subclasses. 
 * For now, the OID represents an OID from the converted CRF. 
 * 
 * @author Rashad Badrawi
 */

public abstract class CRFEntity {
 
    protected String OID; 
    protected String IDShort;
    protected String IDLong;
    protected CRFEntity pointer;
    
    public void setOID (String nOID) {
        
        GenUtil.validateString(nOID);
        this.OID = nOID;
    }
    
    protected void setIDShort (String nIDShort) {
        
        GenUtil.validateString (nIDShort);
        this.IDShort = nIDShort;
    }
    
    protected void setIDLong (String nIDLong) {
        
        GenUtil.validateString(nIDLong);
        this.IDLong = nIDLong;
    }
    
    protected void setPointer (CRFEntity crf) {
        
        GenUtil.validateNotNull(crf);
        this.pointer = crf;
    }
    
    public String getOID () { return this.OID; }
    
    protected String getIDShort () { return this.IDShort; } 
    
    protected String getIDLong () { return this.IDLong; }
    
    protected CRFEntity getPointer () { return this.pointer; }

    @Override
    public String toString () {
        
        return this.getOID();
    }
}
