package org.ocbn.octools.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.octools.model.CRF;
import org.ocbn.octools.model.Group;
import org.ocbn.octools.model.Item;
import org.ocbn.octools.model.ModelCV;
import org.ocbn.octools.model.ModelContainer;
import org.ocbn.octools.model.Section;
import org.ocbn.octools.util.GenUtil;

/**
 * Dumps the tables/sheets for the OC CRF created, in tab delimited 
 * format. 
 * 
 * Could also use setters/getters for access to the 'handy' instance atts. 
 * TO DO: add the dumping functionality, allowing creating several folders, 
 * one per CRF. 
 * 
 * @author Rashad Badrawi
 */

public class OCCRFRep {
    
    private BufferedWriter bw;
    private String outputDir;
    private TreeMap <String, ModelContainer> tempMap;     
    
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
            System.out.println ("Processing CRF: " + mc.getOCCRF().getName() + " ... ");
            cnt++;
            //create new subdir.
            File newDir = new File (this.outputDir, key);
            newDir.mkdir();
            dumpCRF (newDir.getAbsolutePath(), mc.getOCCRF());
            dumpSections (newDir.getAbsolutePath(), mc.getSections());
            dumpGroups (newDir.getAbsolutePath(), mc.getGroups());
            dumpItems (newDir.getAbsolutePath(), mc.getItems());
        }
        System.out.println ("Dumped " + cnt + " OC CRFs.");
    }
        
    private void dumpCRF (String path, CRF crf) throws IOException {
        
        System.out.println ("Dumping CRF sheet: " + crf.getName());
        this.bw = new BufferedWriter (new FileWriter (path + File.separator + 
                                    ModelCV.OC_CRF + GenUtil.FILE_SUFFIX_TEXT));
        this.bw.write(CRF.toStringHeaders());
        this.bw.newLine();
        this.bw.write(crf.toString());
        this.bw.flush();
        this.bw.close();
        //debugging
    }
    
    private void dumpSections (String path, TreeMap <String, Section> 
                               sectionsMap) throws IOException {
        
        System.out.println ("Dumping Section sheet: " + sectionsMap.size());
        this.bw = new BufferedWriter (new FileWriter (path + File.separator + 
                               ModelCV.OC_SECTIONS + GenUtil.FILE_SUFFIX_TEXT));
        this.bw.write (Section.toStringHeaders());
        this.bw.newLine();
        this.bw.flush();
        Iterator iterator = sectionsMap.keySet().iterator();
        while (iterator.hasNext()) {
            this.bw.write (sectionsMap.get((String)iterator.next()).toString());
            this.bw.newLine();
            this.bw.flush();
        }
        this.bw.close();
    }
    
    private void dumpGroups (String path, TreeMap <String, Group> 
                             groupsMap) throws IOException {
        
        System.out.println ("Dumping Group sheet: " + groupsMap.size());
        Iterator iterator = groupsMap.keySet().iterator();
        this.bw = new BufferedWriter (new FileWriter (path + File.separator + 
                               ModelCV.OC_GROUPS + GenUtil.FILE_SUFFIX_TEXT));     
        this.bw.write (Group.toStringHeaders());
        this.bw.newLine();
        this.bw.flush();
        while (iterator.hasNext()) {
            this.bw.write (groupsMap.get((String)iterator.next()).toString());
            this.bw.newLine();
            this.bw.flush();
        }
        this.bw.close();
    }
    
    private void dumpItems (String path, TreeMap <String, Item> itemsMap) 
                                                           throws IOException {
        
        System.out.println ("Dumping Item sheet: " + itemsMap.size());
        this.bw = new BufferedWriter (new FileWriter (path + File.separator + 
                       ModelCV.OC_ITEMS + GenUtil.FILE_SUFFIX_TEXT)); 
        this.bw.write (Item.toStringHeaders());
        this.bw.newLine();
        this.bw.flush();
        Iterator iterator = itemsMap.keySet().iterator();
        while (iterator.hasNext()) {
            this.bw.write (itemsMap.get((String)iterator.next()).toString());
            this.bw.newLine();
            this.bw.flush();
        }
        this.bw.close();
    }
}
