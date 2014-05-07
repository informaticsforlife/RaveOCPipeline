package org.ocbn.octools.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.octools.util.GenUtil;

/**
 * Dumps the tables of the relational database created, in tab delimited 
 * format. 
 * 
 * Could also use setters/getters for access to the 'handy' instance atts. 
 * 
 * @author ocbn
 */

public class DEPStudyRep {
    
    private BufferedWriter bw;
    private TreeMap tempMap;     
    private String tableHeaders;
    private String repMode; 
    
    protected DEPStudyRep () {}
    
    protected void setRepMode (String nRepMode) {
        
        GenUtil.validateString(nRepMode);
        this.repMode = nRepMode;
    }

    protected String getRepMode () { return this.repMode; }
    
    protected void dumpEntries() throws IOException {
        
        switch (getRepMode ()) {
            case ModelCV.REP_MODE_DB:
                dumpEntriesDB ();
                break;
            case ModelCV.REP_MODE_OCCRF:
                dumpEntriesCRF ();
                break;
        }
    }
    
    private void dumpEntriesDB () throws IOException {
    
        this.tempMap = ModelContainer.getPatients();
        System.out.println ("Dumping Patients table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.P_TABLE));
        this.tableHeaders = Patient.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getEncounters();
        System.out.println ("Dumping Encounters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.ENC_TABLE));
        this.tableHeaders = Encounter.toStringHeaders();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getSamples();
        System.out.println ("Dumping Samples table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.SAMPLE_TABLE));
        this.tableHeaders = Sample.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getProteins();
        System.out.println ("Dumping Proteins table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.PP_TABLE));
        this.tableHeaders = Protein.toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getClinParams();
        System.out.println ("Dumping 'Clinical' Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.CP_TABLE));
        this.tableHeaders = ((Params)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getLabParams();
        System.out.println ("Dumping 'Lab' Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.LP_TABLE));
        this.tableHeaders = ((Params)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
        
        this.tempMap = ModelContainer.getProtParams();
        System.out.println ("Dumping Protein Parameters table: " + this.tempMap.size ());
        this.bw = new BufferedWriter (new FileWriter (ModelCV.ProtP_TABLE));
        this.tableHeaders = ((ProtParams)this.tempMap.firstEntry().getValue()).toStringHeader();
        this.dumpOutput();
    }

    private void dumpEntriesCRF () throws IOException {
        
        System.out.println ("Dumping objects as one table/CRF (no proteomics)...");
        //build one all inclusive header
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
    
    //build all inclusive rows, based on the joint ProtParam(s) objects
    //ProtParams themselves are not part of the dump.
    //does not account for patients, samples with no matching protein
    //params, but that is not a use case for this data set. 
    private void dumpInclusiveRows (String encounterType) throws IOException {
        
        this.tempMap = ModelContainer.getProtParams();
        Iterator iterator = this.tempMap.keySet().iterator();
        Patient previousP = null;
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            //not the current encounter?
            if (!((ProtParams)this.tempMap.get(key)).getEncounter().getType().equals (encounterType)) {
                continue;
            }
            Patient p = ((ProtParams)this.tempMap.get (key)).getPatient();
            if (previousP != null) {
                if (p.equals(previousP)) {
                    continue;
                }
            }
            previousP = p;
            Sample s = ((ProtParams)this.tempMap.get (key)).getSample();
            //patients
            this.bw.write (p.toStringCRF());
            this.bw.write (GenUtil.TAB);
            //samples
            this.bw.write (s.toStringCRF());
            //clinical params, dumping by patient & encounter
            String secondaryKey = p.getSecondaryID() + GenUtil.AT + encounterType;
            if (ModelContainer.getClinParams().containsKey(secondaryKey)) {
                this.bw.write (GenUtil.TAB);
                this.bw.write(ModelContainer.getClinParams().get (secondaryKey).toStringCRF());  
            }
            //lab params, dumping by patient & encounter
            if (ModelContainer.getLabParams().containsKey(secondaryKey)) {
                this.bw.write (GenUtil.TAB);
                this.bw.write (ModelContainer.getLabParams().get(secondaryKey).toStringCRF());
            }
            this.bw.newLine();
            this.bw.flush();
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
}
