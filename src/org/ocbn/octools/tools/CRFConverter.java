package org.ocbn.octools.tools;

import java.io.IOException;

import org.ocbn.octools.model.ModelCV;

/**
 * Main converter class, seed for a factory pattern. 
 * 
 * @author Rashad
 */

public abstract class CRFConverter {

    public static CRFConverter getCRFConverter (String sourceType, String sourceDir, 
                                                String outputDir) {
        
        if (ModelCV.EDC_RAVE.equals (sourceType)) {
            return new RaveCRFConverter (sourceDir, outputDir);
        } else {
            throw new IllegalArgumentException ("Invalid source EDC type: " +
                                                sourceType);
        }
    }
    
    public abstract void convertCRF () throws IOException;
    
    public void dumpCRF () {}
}
