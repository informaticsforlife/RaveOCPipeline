package org.ocbn.octools.tools;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.ocbn.octools.model.CRF;
import org.ocbn.octools.model.Group;
import org.ocbn.octools.model.Item;
import org.ocbn.octools.model.ItemResponse;
import org.ocbn.octools.model.ModelContainer;
import org.ocbn.octools.model.ModelCV;
import org.ocbn.octools.model.Section;
import org.ocbn.octools.util.AttValLabel;

import org.ocbn.octools.util.DefParams;

import org.ocbn.octools.util.GenUtil;

/**
 * A custom reader for the RAVE source sheets. Currently reading data from 
 * CRFDraft, forms, and fields sheet. Can be extended to include the folders
 * sheet. 
 * No special class(es) constructs made for the Rave data elements. 
 * The processStudyInstruct () method may eventually evolve in its own class, 
 * which is edited with every new data source for conversion. 
 * 
 * @author Rashad Badrawi
 */

public class RaveCRFReader {
       
    private CSVReader crfReader, formsReader, fieldsReader;
    private ModelContainer OCContainer;
    private TreeMap <String, ModelContainer> CRFMap;
    
    protected RaveCRFReader (String sourceDir) throws IOException {
        
        this.CRFMap = new TreeMap <String, ModelContainer>();
        RaveDDMapper.initializeMappingFile(sourceDir, DefParams.getDefaultProp(
                                           DefParams.RAVE_DDENTRIES_FILENAME));
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
            String lt = null, gt = null, ncl = null, ncu = null;
            for (int i = 0; i < tempArr.length; i++) {
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
            }
            String validStr = ItemResponse.buildValidStr (lt, gt, ncl, ncu);
            if (validStr != null) {
                itemResponse.setValid (validStr);
                itemResponse.setValidErrMsg("Response should satisfy the following "
                    + "rule(s): " + validStr);
            }
            if (processStudyInstruct (item)) {
                this.OCContainer.addItem(item);
            }
            cnt++;
        }
        System.out.println ("Read: " + cnt + " CRF fields.");
    }
    
    //limited to POND for now
    private boolean processStudyInstruct (Item item) {
        
        final String RAVE_STUDY_POND = "POND";
        final String BLANK_LINE_ITEM = "BLANK_LINE";
        final String YES_NO_DD = "No_0_Yes_1";
        
        //To begin with, for now, skip POND CRF blank lines. 
        if (OCContainer.getOCCRF().getName().equals (RAVE_STUDY_POND)) {
            if (item.getName().startsWith(BLANK_LINE_ITEM)) {
                System.out.println ("Warning: blank line item: " +
                item.getName() + " " + item.getSectionRef().getLabel());
                return false;
                //return true;
            }
        }
        //add default group for POND 
        Group defGroup = OCContainer.getGroups().firstEntry().getValue();
        item.setGroupRef(defGroup);
        //some POND items do not specify a data type. 
        if (item.getItemResponse().getDataType() == null) {
            item.getItemResponse().setDataType(ModelCV.OC_CRF_CV_DATAT_ST);
        }
        //checkbox(es) with no DD associated with it. 
        if (item.getItemResponse().getResponseType()
                .equals (ModelCV.OC_CRF_CV_RESPONSET_CHECKBOX) &&
            item.getItemResponse().getResponseLabel() == null) {
            item.getItemResponse().setResponseType(ModelCV.OC_CRF_CV_RESPONSET_RADIO);
            processDD (item.getItemResponse(), YES_NO_DD);
        }
        //search lists with no DD. May also add the multiple select case.
        if (item.getItemResponse().getResponseType()
                .equals (ModelCV.OC_CRF_CV_RESPONSET_SINGLES) &&
            item.getItemResponse().getResponseLabel() == null) {
            item.getItemResponse().setResponseType(ModelCV.OC_CRF_CV_RESPONSET_TEXT);
        }
        return true;
    } 
    
    //To be implemented.
    private void processDD (ItemResponse itemResponse, String DDName) {
    
        RaveDD rdd = RaveDDMapper.getRaveDD (DDName);
        ArrayList <AttValLabel> tempList = rdd.getDDEntries();
        String optionsStr = "", valuesStr = "";
        for (int i = 0; i < tempList.size (); i++) {
            optionsStr += escapeCommas (tempList.get (i).getValLabel());
            valuesStr += escapeCommas (tempList.get (i).getVal());
            if (i < tempList.size () - 1) {
                optionsStr += GenUtil.COMMA;
                valuesStr += GenUtil.COMMA;
            }
        }
        itemResponse.setResponseLabel(rdd.getName());
        itemResponse.setResponseOptions(optionsStr);
        itemResponse.setResponseValues(valuesStr);    
    }
    
    private String escapeCommas (String str) {
        
        int index = str.indexOf(GenUtil.COMMA);
        //comma would not be the first character.
        if (index > 0) {
            str = str.replace(GenUtil.COMMA,  GenUtil.DOUBLE_BACK_SLASH + 
                              GenUtil.COMMA);
        }
        
        return str;
    }
}
