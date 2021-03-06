package org.ocbn.octools.tools;

import java.io.IOException;
import java.util.TreeMap;
import org.ocbn.octools.model.ModelContainer;
import org.ocbn.octools.util.GenUtil;

/**
 * CRF conversion implementation for Rave (to OC). 

 * @author Rashad Badrawi
 */

public class RaveCRFConverter extends CRFConverter {

    private String sourceDir;
    private String outputDir;
    
    public RaveCRFConverter(String sourceDir, String outputDir) {
        
        GenUtil.validateString (sourceDir); 
        GenUtil.validateString (outputDir);
        this.sourceDir = sourceDir;
        this.outputDir = outputDir;
    }

    @Override
    public void convertCRF() throws IOException {
        
        RaveCRFReader rcr = new RaveCRFReader (sourceDir);
        rcr.readEntries();
        TreeMap <String, ModelContainer> tempMap = rcr.getCRFMap();
        OCCRFRep dumper = new OCCRFRep(this.outputDir, tempMap);
        dumper.dumpEntries ();
    }
}
