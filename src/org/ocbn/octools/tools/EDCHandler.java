package org.ocbn.octools.tools;

import java.io.BufferedReader;
import java.io.PrintWriter;
import org.ocbn.octools.ocmodel.ModelCV;
import org.ocbn.octools.util.DefParams;
import org.ocbn.octools.util.GenUtil;

/**
 * Controller for the conversion of Rave to OC CRFs.  
 * 
 * @author Rashad Badrawi
 */

public class EDCHandler {
    
    private static PrintWriter log;
    protected BufferedReader br;
    
    public static void main (String args []) {
        
        log = GenUtil.getDefaultLog ();
        log.println (GenUtil.getTimeStamp ());
        GenUtil.registerStart ();
        String usageMsg = "Usage: XOCConverter SourceFileDirectory SourceType MappingFile OutputFileDirectory";
        String warnMsg = "WARNING: Missing command line args. Using defaults from PROP file.";
       
        if (args == null || args.length < 4){
            log.println (warnMsg);
            log.println (usageMsg);
            System.out.println (warnMsg);
            System.out.println (usageMsg);
            args = DefParams.getCommandLineArgs (EDCHandler.class.getName ());
        }
        try {
            CRFConverter converter = CRFConverter.getCRFConverter(args [1], 
                                     args [0], args [3]);
            ModelCV.initializeMappingFile(args [2]);
            converter.convertCRF();
            converter.dumpCRF ();
        } catch (Throwable e) {
            System.err.println ("Unable to process request: EDCHandler.");
            e.printStackTrace (System.out);
            e.printStackTrace(log);
        } finally {
           log.println (GenUtil.getExecTimeStr ());
           log.flush ();
           log.close ();
           System.out.println (GenUtil.getExecTimeStr ());
        }
    }
}