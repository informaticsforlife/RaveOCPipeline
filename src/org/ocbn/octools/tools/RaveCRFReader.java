package org.ocbn.octools.tools;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import org.ocbn.octools.model.CRF;
import org.ocbn.octools.model.Group;
import org.ocbn.octools.model.Item;
import org.ocbn.octools.model.ItemResponse;
import org.ocbn.octools.model.ModelContainer;
import org.ocbn.octools.model.ModelCV;
import org.ocbn.octools.model.Section;

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
       
    private CSVReader crfReader, formsReader, fieldsReader;
    private ModelContainer OCContainer;
    private TreeMap <String, ModelContainer> CRFMap;
    
    protected RaveCRFReader (String sourceDir) throws IOException {
        
        this.CRFMap = new TreeMap <String, ModelContainer>();
        GenUtil.validateString(sourceDir);
        this.crfReader = new CSVReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_CRF_FILENAME))));
        this.formsReader = new CSVReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_FOLDERS_FILENAME)))); 
        this.fieldsReader = new CSVReader (new FileReader (new File (
        sourceDir, DefParams.getDefaultProp(DefParams.RAVE_FIELDS_FILENAME))));
    }
    
    @Override
    protected void finalize () throws Throwable {
        
        this.crfReader.close();
        this.formsReader.close ();
        this.fieldsReader.close (); 
        super.finalize();
    }
    
    public TreeMap <String, ModelContainer> getCRFMap () { return this.CRFMap; }
    
    //fakes a hierarchial approach in reading entries, although there is no
    //linking between crf draft, forms, and fields within the Rave source sheets
    protected final void readEntries () throws IOException {
    
        readCRFDraftEntries ();
        readCRFFormsEntries();
        readCRFFieldsEntries();
    }

    protected final void readCRFDraftEntries () throws IOException {
        
        String [] headersArr = null;
        int cnt = 0;
        String [] tempArr;
        while ((tempArr = this.crfReader.readNext()) != null) {
            this.OCContainer = new ModelContainer();
            CRF OCCRF = new CRF ();
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }            
            //set the global CRF parameters.
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0
                    || tempArr [i].equals (GenUtil.NA)) {
                    continue;
                }
                switch (headersArr [i]) {
                    case ModelCV.RAVE_PROJECT_NAME:
                        OCCRF.setName(tempArr [i]);
                        break;
                    case ModelCV.RAVE_DRAFT_NAME:
                        OCCRF.setVersionDesc(tempArr [i]);
                        break;
                }
            }
            OCCRF.setVersion(ModelCV.OC_CRF_DEF_VERSION);
            OCCRF.setRevNotes(ModelCV.OC_CRF_DEF_REV_NOTES);
            this.OCContainer.addCRF (OCCRF);
            this.CRFMap.put (OCCRF.getName(), this.OCContainer);          
            cnt++;
            addDefOCGroup ();
        }
        System.out.println ("Read : " + cnt + " CRF draft project.");
    }
        
    protected final void readCRFFormsEntries () throws IOException {
        
        String [] headersArr = null;
        int cnt = 0;
        String [] tempArr;
        while ((tempArr = this.formsReader.readNext()) != null) {
            Section section = new Section ();
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0
                    || tempArr [i].equals (GenUtil.NA)) {
                    continue;
                }
                switch (headersArr [i]) {
                    case ModelCV.RAVE_OID:
                        section.setOID(tempArr [i]);
                        section.setLabel(tempArr [i]);
                        break;
                    case ModelCV.RAVE_DRAFT_FORM_NAME:
                        section.setTitle(tempArr [i]);
                        break;
                    case ModelCV.RAVE_HELP_TEXT: 
                        section.setInstruct(tempArr [i]);
                        break;
                    case ModelCV.RAVE_ORDINAL:
                        section.setPageNum(tempArr [i]);
                        break;
                    case ModelCV.RAVE_LINK_FORM_OID:
                        Section dummySection = new Section ();
                        dummySection.setLabel(tempArr [i]);
                        section.setParentSection(dummySection);
                        break;
                }
            }
            this.OCContainer.addSection(section);
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
               
        String [] headersArr = null;
        int cnt = 0;
        String [] tempArr;
        while ((tempArr = this.fieldsReader.readNext()) != null) {
            Item item = new Item ();
            ItemResponse itemResponse = new ItemResponse ();
            item.setItemResponse(itemResponse);
            if (headersArr == null) {
                headersArr = tempArr;
                continue;
            }
            for (int i = 0; i < tempArr.length; i++) {
                String lt = null, gt = null, ncl = null, ncu = null;
                if (tempArr [i] == null || tempArr [i].trim ().length () == 0 
                    || tempArr [i].equals (GenUtil.NA)) {
                    continue;
                }
                switch (headersArr [i]) {
                    case ModelCV.RAVE_FIELD_OID:
                        item.setName(tempArr [i]);
                        break;
                    case ModelCV.RAVE_FORM_OID:
                        Section dummySection = new Section ();
                        dummySection.setLabel(tempArr [i]);
                        item.setSectionRef(dummySection);
                        break;
                    case ModelCV.RAVE_HELP_TEXT:
                        item.setRText(tempArr [i]);
                        break;
                    case ModelCV.RAVE_DRAFT_FIELD_NAME:
                        item.setDesc(tempArr [i]);
                        break;
                    case ModelCV.RAVE_PRE_TEXT: 
                        item.setLText(tempArr [i]);
                        break;
                    case ModelCV.RAVE_FIXED_UNIT:
                        item.setUnits(tempArr [i]);
                        break;
                    case ModelCV.RAVE_HEADER_TEXT:
                        item.setHeader(tempArr [i]);
                        break;
                    case ModelCV.RAVE_CONTROL_TYPE:
                        itemResponse.setResponseType(
                        ModelCV.getOCResponseType (tempArr [i]));
                        break;
                    case ModelCV.RAVE_DATA_DICTIONARY_NAME:
                        processDD (itemResponse, tempArr [i]);
                        break;
                    case ModelCV.RAVE_DEFAULT_VALUE:
                        itemResponse.setDefValue(tempArr [i]);
                        break;
                    case ModelCV.RAVE_DATA_FORMAT:
                        itemResponse.setDataType(ModelCV.OC_CRF_CV_DATAT_ST);
                        //itemResponse.setDataType(tempArr [i]);
                        break;
                    case ModelCV.RAVE_IS_REQUIRED:
                        int flag = -1;
                        switch (tempArr [i]) {
                            case ModelCV.RAVE_TRUE:
                                flag = ModelCV.OC_CRF_1;
                                break;
                            case ModelCV.RAVE_FALSE:
                                flag = ModelCV.OC_CRF_0;
                                break;
                        }
                        item.setIsRequired(flag);
                        break;
                    case ModelCV.RAVE_IS_VISIBLE:
                        item.setDisStatus(tempArr [i]);
                        break;
                    case ModelCV.RAVE_ORDINAL:
                        item.setQuestNum(Integer.valueOf(tempArr [i]));
                        break;
                    case ModelCV.RAVE_INDENT_LEVEL:
                        //currently ignored.
                        break;
                    case ModelCV.RAVE_LOWER_RANGE: 
                        lt = tempArr [i];
                        break;
                    case ModelCV.RAVE_UPPER_RANGE: 
                        gt = tempArr [i];
                        break;
                    case ModelCV.RAVE_NCLOWER_RANGE: 
                        ncl = tempArr [i];
                        break;
                    case ModelCV.RAVE_NCUPPER_RANGE: 
                        ncu = tempArr [i];
                        break;
                }
                String validStr = ItemResponse.buildValidStr (lt, gt, ncl, ncu);
                if (validStr != null) {
                    itemResponse.setValid (validStr);
                    itemResponse.setValidErrMsg("Response should satisfy the following "
                        + "rule(s): " + validStr);
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
