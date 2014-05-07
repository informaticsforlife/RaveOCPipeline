package org.ocbn.octools.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import org.ocbn.octools.ocmodel.CRF;
import org.ocbn.octools.ocmodel.Group;
import org.ocbn.octools.ocmodel.Item;
import org.ocbn.octools.ocmodel.ItemResponse;
import org.ocbn.octools.ocmodel.ModelContainer;
import org.ocbn.octools.ocmodel.ModelCV;
import org.ocbn.octools.ocmodel.Section;

import org.ocbn.octools.util.DefParams;

import org.ocbn.octools.util.GenUtil;

/**
 * A custom reader for the RAVE source sheets. Currently reading data from 
 * CRFDraft, forms, and fields sheet. Can be extended to include the folders
 * sheet. 
 * No special class(es) constructs made for the Rave data elements. 
 * 
 * @author Rashad Badrawi
 */

public class RaveCRFReader {
       
    private BufferedReader CRFDraftBR, formsBR, fieldsBR;
    private String RProjectName, RCRFDraftName;   //CRF level, for refactoring
    private int RFormOrdinal,
                RFieldOrdinal, RIndent; 
    private float RLR, RUR, RNCL, RNCU;
    private ModelContainer OCContainer;
    private TreeMap <String, ModelContainer> CRFMap = new TreeMap ();
    
    protected RaveCRFReader (String sourceDir) throws IOException {
        
        GenUtil.validateString(sourceDir);
        this.CRFDraftBR = new BufferedReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_CRF_FILENAME))));
        this.formsBR = new BufferedReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_FOLDERS_FILENAME))));
        this.fieldsBR = new BufferedReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_FIELDS_FILENAME))));   
    }
    
    public TreeMap <String, ModelContainer> getCRFMap () { return this.CRFMap; }
    
    //fakes a hierarchial approach in reading entries, although there is no
    //linking between crf draft, forms, and fields within the Rave source sheets
    protected final void readEntries () throws IOException {
    
        readCRFDraftEntries ();
        readCRFFormsEntries();
        readCRFFieldsEntries();
        this.CRFDraftBR.close ();
        this.formsBR.close ();
        this.fieldsBR.close ();
    }

    protected final void readCRFDraftEntries () throws IOException {
        
        String line; 
        String [] headersArr = null;
        int cnt = 0;
        while ((line = this.CRFDraftBR.readLine()) != null) {
            this.OCContainer = null;
            CRF OCCRF = new CRF ();
            this.RProjectName = null;
            this.RCRFDraftName = null;
            line = line.trim();
            String [] tempArr = line.split(GenUtil.TAB);
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }            
            //set the global CRF parameters.
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0) {
                    continue;
                }
                switch (headersArr [i]) {
                    case ModelCV.RAVE_PROJECT_NAME:
                        this.RProjectName = tempArr [i];
                        OCCRF.setName(tempArr [i]);
                    case ModelCV.RAVE_DRAFT_NAME:
                        this.RCRFDraftName = tempArr [i];
                        OCCRF.setVersionDesc(tempArr [i]);
                }
                OCCRF.setVersion(ModelCV.OC_CRF_DEF_VERSION);
                OCCRF.setRevNotes(ModelCV.OC_CRF_DEF_REV_NOTES);
                this.OCContainer.addCRF (OCCRF);
                this.CRFMap.put (OCCRF.getName(), this.OCContainer);
            }
            cnt++;
        }
        System.out.println ("Read : " + cnt + " CRF draft project.");
    }
        
    protected final void readCRFFormsEntries () throws IOException {
        
        String line; 
        String [] headersArr = null;
        int cnt = 0;
        while ((line = formsBR.readLine()) != null) {
            Section section = new Section ();
            String [] tempArr = line.trim ().split(GenUtil.TAB);
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0) {
                    continue;
                }
                switch (headersArr [i]) {
                    case ModelCV.RAVE_OID:
                        section.setOID(tempArr [i]);
                        section.setLabel(tempArr [i]);
                    case ModelCV.RAVE_DRAFT_FORM_NAME:
                        section.setTitle(tempArr [i]);
                    case ModelCV.RAVE_HELP_TEXT: 
                        section.setInstruct(tempArr [i]);
                    case ModelCV.RAVE_ORDINAL:
                        section.setPageNum(tempArr [i]);
                    case ModelCV.RAVE_LINK_FORM_OID:
                        Section dummySection = new Section ();
                        dummySection.setLabel(tempArr [i]);
                        section.setParentSection(dummySection);
                }
            }
            this.OCContainer.addSection(section);
            addDefOCGroup ();
            cnt++;
        }
        System.out.println ("Read: " + cnt + " CRF forms.");
    }
    
    private void addDefOCGroup () {
        
        Group group = new Group ();
        group.setLabel(ModelCV.OC_CRF_CV_GROUP_LABEL_BASIC);
        this.OCContainer.addGroup(group);
    }
    
    protected final void readCRFFieldsEntries () throws IOException {
               
        String line; 
        String [] headersArr = null;
        int cnt = 0;
        while ((line = fieldsBR.readLine()) != null) {
            Item item = new Item ();
            ItemResponse itemResponse = new ItemResponse ();
            item.setItemResponse(itemResponse);
            String [] tempArr = line.trim ().split(GenUtil.TAB);
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0) {
                    continue;
                }
                String validValue, validErrMsg;
                switch (headersArr [i]) {
                    case ModelCV.RAVE_FIELD_OID:
                        item.setName(tempArr [i]);
                    case ModelCV.RAVE_FORM_OID:
                        Section dummySection = new Section ();
                        dummySection.setLabel(tempArr [i]);
                        item.setSectionRef(dummySection);
                    case ModelCV.RAVE_HELP_TEXT:
                        item.setRText(tempArr [i]);
                    case ModelCV.RAVE_DRAFT_FIELD_NAME:
                        item.setDesc(tempArr [i]);
                    case ModelCV.RAVE_PRE_TEXT: 
                        item.setLText(tempArr [i]);
                    case ModelCV.RAVE_FIXED_UNIT:
                        item.setUnits(tempArr [i]);
                    case ModelCV.RAVE_HEADERTEXT:
                        item.setHeader(tempArr [i]);
                    case ModelCV.RAVE_CONTROL_TYPE:
                        itemResponse.setResponseType(tempArr [i]);
                    case ModelCV.RAVE_DATA_DICTIONARY_NAME:
                        processDD (itemResponse, tempArr [i]);
                    case ModelCV.RAVE_DEFAULT_VALUE:
                        itemResponse.setDefValue(tempArr [i]);
                    case ModelCV.RAVE_DATA_FORMAT:
                        itemResponse.setDataType(tempArr [i]);
                    case ModelCV.RAVE_IS_REQUIRED:
                        item.setIsRequired(Integer.valueOf(tempArr [i]));
                    case ModelCV.RAVE_IS_VISIBLE:
                        item.setDisStatus(tempArr [i]);
                    case ModelCV.RAVE_ORDINAL:
                        item.setQuestNum(Integer.valueOf(tempArr [i]));
                    case ModelCV.RAVE_INDENT_LEVEL:
                        //currently ignored.
                    case ModelCV.RAVE_LOWER_RANGE: 
                        this.RLR = Integer.parseInt (tempArr [i]);
                    case ModelCV.RAVE_UPPER_RANGE: 
                        this.RUR = Integer.parseInt (tempArr [i]);
                    case ModelCV.RAVE_NCLOWER_RANGE: 
                        this.RNCL = Integer.parseInt (tempArr [i]);
                    case ModelCV.RAVE_NCUPPER_RANGE: 
                        this.RNCU = Integer.parseInt (tempArr [i]);
                }
            }
            this.OCContainer.addItem(item);
            cnt++;
        }
        System.out.println ("Read: " + cnt + " CRF fields.");
    }
    
    //To be implemented.
    private void processDD (ItemResponse itemResponse, String DDName) {}
}
