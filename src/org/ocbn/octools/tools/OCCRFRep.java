package org.ocbn.octools.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.octools.ocmodel.CRF;
import org.ocbn.octools.ocmodel.Group;
import org.ocbn.octools.ocmodel.Item;
import org.ocbn.octools.ocmodel.ModelContainer;
import org.ocbn.octools.ocmodel.Section;
import org.ocbn.octools.util.GenUtil;

/**
 * Dumps the tables/sheets for the OC CRF created, in tab delimited 
 * format. 
 * 
 * Could also use setters/getters for access to the 'handy' instance atts. 
 * 
 * @author Rashad Badrawi
 */

public class OCCRFRep {
    
    private BufferedWriter bw;
    private String outputDir;
    private TreeMap <String, ModelContainer> tempMap;     
    private String tableHeaders;
    
    protected OCCRFRep (String nOutputDir, TreeMap <String, ModelContainer> CRFMap) {
    
        GenUtil.validateString (nOutputDir);
        GenUtil.validateNotNull(CRFMap);
        if (CRFMap.size() == 0) {
            throw new IllegalArgumentException ("No OC CRF to dump: " + 
                                                CRFMap.size());
        }
        this.outputDir = nOutputDir;
        this.tempMap = CRFMap;
    }
    
    @Override
    protected void finalize () throws Throwable {
        
        if (bw != null) {
            bw.flush();
            bw.close ();
        }
        super.finalize();
    }
    
    protected void dumpEntries() throws IOException {
    
        dumpEntriesSingleCRF ();
    }
    
    private void dumpEntriesSingleCRF () throws IOException {
        
        System.out.println ("Dumping all as single OC CRF (4 files/spreadsheets)...");
        //build one all inclusive header
        Iterator iterator = this.tempMap.keySet().iterator();
        int cnt = 0;
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            ModelContainer mc = tempMap.get (key);
            System.out.println ("Processing CRF: " + key + " ... ");
            cnt++;
            dumpCRF (mc.getOCCRF());
            dumpSections (mc.getSections());
            dumpGroups (mc.getGroups());
            dumpItems (mc.getItems());
        }
        System.out.println ("Dumped " + cnt + " OC CRFs.");
    }
        
    private void dumpCRF (CRF crf) {
        
        System.out.println ("Dumping CRF sheet: " + crf.getName());
    }
    
    private void dumpSections (TreeMap <String, Section> sectionsMap) {
        
    }
    
    private void dumpGroups (TreeMap <String, Group> groupsMap) {
        
    }
    
    private void dumpItems (TreeMap <String, Item> itemsMap) {
        
    }
    
    /*
        this.tableHeaders = Patient.toStringHeaderCRF () + GenUtil.TAB + 
                            Sample.toStringHeaderCRF () + GenUtil.TAB;
        this.tempMap = ModelContainer.getClinParams();
        this.tableHeaders += ((Params)this.tempMap.firstEntry().getValue()).toStringHeaderCRF();
        this.tableHeaders += GenUtil.TAB;
        this.tempMap = ModelContainer.getLabParams();
        this.tableHeaders += ((Params)this.tempMap.firstEntry().getValue()).toStringHeaderCRF(); 
        
        //Group output in files, based on encounter type
        TreeMap <String, Encounter> encMap = ModelContainer.getEncounters();
        Iterator encIterator = encMap.keySet().iterator();
        while (encIterator.hasNext()) {
            Encounter enc = encMap.get ((String)encIterator.next());
            this.bw = new BufferedWriter (new FileWriter (ModelCV.OC_CRF + 
                                          GenUtil.UNDERSCORE + enc.getLabel()));
            //temp step. Done to match naming in OC. 
            this.tableHeaders = this.tableHeaders.replace(GenUtil.DOT, GenUtil.UNDERSCORE);
            //End of temp step
            
            //System.out.println (this.tableHeaders); 
            this.bw.write(this.tableHeaders);
            this.bw.newLine();
            System.out.println ("Dumping entries for encounter: " + enc.getLabel());
            dumpInclusiveRows (enc.getType());
            this.bw.close();
        }
    }
  

    //utility method
    private void dumpOutput () throws IOException {

        Iterator iterator = this.tempMap.keySet().iterator();
        //System.out.println (this.tableHeaders);
        this.bw.write (this.tableHeaders);
        this.bw.newLine();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            //System.out.println (this.tempMap.get(key));
            this.bw.write(this.tempMap.get (key).toString());
            this.bw.newLine();
        }
        this.bw.flush();
    }
*/
}
