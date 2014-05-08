package org.ocbn.octools.ocmodel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.ocbn.octools.tools.XOCMapper;
import org.ocbn.octools.util.GenUtil;

/**
 * Controlled vocabularies for all the variables. Dispenses the appropriate mapping. 
 * Could optionally move some of its CV to a prop file or the likes. 
 * Reading the mapping file could be moved elsewhere. 
 * The mapping file is of the format: OC-Sheet, OC-Field, X-Sheet, X-Field, 
 * Fixed Value, Comments
 * It does not allow null values, and uses 'NA's instead. For now, the 
 * assumed mapping is a simple 1:1, and as such, the OC-Sheet/Field combination
 * identify a unique tuple. 
 * 
 * @author Rashad Badrawi
 */

public class ModelCV {
    
    private static ArrayList <XOCMapper> mapperList = new ArrayList ();
    private static ArrayList <String> RaveColsList = new ArrayList ();
    private static ArrayList <String> OCResponseTList = new ArrayList ();
    private static ArrayList <String> OCDataTypeList = new ArrayList ();
    
    //EDC CRF types
    public static final String EDC_RAVE = "RAVE";
    
    //OC CRF sheet names (Instructions sheet not generated or included)
    public static final String OC_CRF = "CRF";
    public static final String OC_SECTIONS = "Sections";
    public static final String OC_GROUPS = "Groups";
    public static final String OC_ITEMS = "Items";
    //OC CRF - CRF sheet
    public static final String OC_CRF_NAME = "CRF_NAME";	
    public static final String OC_CRF_VERSION = "VERSION";	
    public static final String OC_CRF_VERSIOND = "VERSION_DESCRIPTION";	
    public static final String OC_CRF_REVISIONN = "REVISION_NOTES";
    //OC CRF - Sections sheet
    public static final String OC_SECTIONS_SECTIONL = "SECTION_LABEL";	
    public static final String OC_SECTIONS_SECTIONT = "SECTION_TITLE";	
    public static final String OC_SECTIONS_SUBTITLE = "SUBTITLE";	
    public static final String OC_SECTIONS_INSTRUCTIONS = "INSTRUCTIONS";	
    public static final String OC_SECTIONS_PAGEN = "PAGE_NUMBER";	
    public static final String OC_SECTIONS_PARENT = "PARENT_SECTION";
    //OC CRF - Groups sheet.
    public static final String OC_GROUPS_GROUPLABEL = "GROUP_LABEL";
    public static final String OC_GROUPS_GROUPL = "GROUP_LAYOUT";
    public static final String OC_GROUPS_GROUPH = "GROUP_HEADER";
    public static final String OC_GROUPS_GROUPRN = "GROUP_REPEAT_NUMBER";	
    public static final String OC_GROUPS_GROUPRM = "GROUP_REPEAT_MAX";
    public static final String OC_GROUPS_GROUPDS = "GROUP_DISPLAY_STATUS";
    //OC CRF - Items sheet
    public static final String OC_ITEMS_ITEMN = "ITEM_NAME";
    public static final String OC_ITEMS_DESCRITIONL = "DESCRIPTION_LABEL";	
    public static final String OC_ITEMS_LEFTIT = "LEFT_ITEM_TEXT";	
    public static final String OC_ITEMS_UNITS = "UNITS";	
    public static final String OC_ITEMS_RIGHTIT = "RIGHT_ITEM_TEXT";	
    public static final String OC_ITEMS_SECTIONL = "SECTION_LABEL";
    public static final String OC_ITEMS_GROUPL = "GROUP_LABEL";	
    public static final String OC_ITEMS_HEADER = "HEADER";
    public static final String OC_ITEMS_SUBHEADER = "SUBHEADER";	
    public static final String OC_ITEMS_PARENTI = "PARENT_ITEM";
    public static final String OC_ITEMS_COLUMNN = "COLUMN_NUMBER";
    public static final String OC_ITEMS_PAGEN = "PAGE_NUMBER";
    public static final String OC_ITEMS_QUESTIONN = "QUESTION_NUMBER";	
    public static final String OC_ITEMS_RESPONSET = "RESPONSE_TYPE";
    public static final String OC_ITEMS_RESPONSEL = "RESPONSE_LABEL";
    public static final String OC_ITEMS_RESPONSEOT = "RESPONSE_OPTIONS_TEXT";
    public static final String OC_ITEMS_RESPONSEV = "RESPONSE_VALUES_OR_CALCULATIONS";	
    public static final String OC_ITEMS_RESPONSELAYOUT = "RESPONSE_LAYOUT";
    public static final String OC_ITEMS_DEFAULTV = "DEFAULT_VALUE";
    public static final String OC_ITEMS_DATAT = "DATA_TYPE";
    public static final String OC_ITEMS_DECWIDTH = "WIDTH_DECIMAL";
    public static final String OC_ITEMS_VALIDATION = "VALIDATION";	
    public static final String OC_ITEMS_VALIDATIONE = "VALIDATION_ERROR_MESSAGE";
    public static final String OC_ITEMS_PHI = "PHI";
    public static final String OC_ITEMS_REQUIRED = "REQUIRED";
    public static final String OC_ITEMS_ITEMD = "ITEM_DISPLAY_STATUS";
    public static final String OC_ITEMS_SIMPLEC = "SIMPLE_CONDITIONAL_DISPLAY";	
    //OC CRF - CV
    //Generic
    public static final String OC_CRF_CV_SHOW = "SHOW";
    public static final String OC_CRF_CV_HIDE = "HIDE";
    public static final String OC_CRF_0 = "0";
    public static final String OC_CRF_1 = "1";
    public static final String OC_CRF_DEF_VERSION = "1";
    public static final String OC_CRF_DEF_REV_NOTES = "Data Migration: Rave";
    public static final int OC_CRF_GROUP_RNUM_DEFAULT = 1;
    public static final int OC_CRF_GROUP_RMAX_DEFAULT = 40;
    public static final String OC_VALID_LT = "lt";
    public static final String OC_VALID_GT = "gt";
    public static final String OC_VALID_RANGE = "range";
    
    //Specific
    public static final String OC_CRF_CV_GROUP_LABEL_BASIC = "Basic"; //basic grouping
    public static final String OC_CRF_CV_GROUPL_GRID = "GRID";
    public static final String OC_CRF_CV_GROUPL_NONR = "NON-REPEATING";
    public static final String OC_CRF_CV_RESPONSET_TEXT = "text";
    public static final String OC_CRF_CV_RESPONSET_TEXTA = "textarea";
    public static final String OC_CRF_CV_RESPONSET_SINGLES = "single-select";
    public static final String OC_CRF_CV_RESPONSET_RADIO = "radio";
    public static final String OC_CRF_CV_RESPONSET_MULTIS = "multi-select";
    public static final String OC_CRF_CV_RESPONSET_CHECKBOX = "checkbox";
    public static final String OC_CRF_CV_RESPONSET_CALCULATION = "calculation";
    public static final String OC_CRF_CV_RESPONSET_GROUPC = "group-calculation";
    public static final String OC_CRF_CV_RESPONSET_FILE = "file";
    public static final String OC_CRF_CV_RESPONSET_INSTANTC = "instant-calculation";
    public static final String OC_CRF_CV_RESPONSELAYOUT_HORIZONTAL = "HORIZONTAL";
    public static final String OC_CRF_CV_RESPONSELAYOUT_VERTICAL = "VERTICAL";
    public static final String OC_CRF_CV_DATAT_ST = "ST";
    public static final String OC_CRF_CV_DATAT_INT = "INT";
    public static final String OC_CRF_CV_DATAT_REAL = "REAL";
    public static final String OC_CRF_CV_DATAT_DATE = "DATE";
    public static final String OC_CRF_CV_DATAT_PDATE = "PDATE";
    public static final String OC_CRF_CV_DATAT_FILE = "FILE";
    
    //Rave CVs. 
    //Rave CRF - CRFDraft sheet
    public static final String RAVE_PROJECT_NAME = "ProjectName";
    public static final String RAVE_DRAFT_NAME = "DraftName";
    //Rave CRF - Forms sheet.
    public static final String RAVE_OID = "OID";            //generic
    public static final String RAVE_DRAFT_FORM_NAME = "DraftFormName";
    public static final String RAVE_HELP_TEXT = "HelpText"; //used in fields, too
    public static final String RAVE_ORDINAL = "Ordinal";   //same
    public static final String RAVE_LINK_FORM_OID = "LinkFormOID";
    //Rave CRF - Fields sheet.
    public static final String RAVE_FIELD_OID = "FieldOID";
    public static final String RAVE_FORM_OID = "FormOID";
    public static final String RAVE_DRAFT_FIELD_NAME = "DraftFieldName";
    public static final String RAVE_PRE_TEXT = "PreText";
    public static final String RAVE_FIXED_UNIT = "FixedUnit";
    public static final String RAVE_HEADERTEXT = "HeaderText";
    public static final String RAVE_INDENT_LEVEL = "IndentLevel";
    public static final String RAVE_CONTROL_TYPE = "ControlType";
    public static final String RAVE_DATA_DICTIONARY_NAME = "DataDictionaryName";
    public static final String RAVE_DEFAULT_VALUE = "DefaultValue";
    public static final String RAVE_DATA_FORMAT = "DataFormat";
    public static final String RAVE_LOWER_RANGE = "LowerRange";
    public static final String RAVE_UPPER_RANGE = "UpperRange";
    public static final String RAVE_NCLOWER_RANGE = "NCLowerRange";
    public static final String RAVE_NCUPPER_RANGE = "NCUpperRange";
    public static final String RAVE_IS_REQUIRED = "IsRequired";
    public static final String RAVE_IS_VISIBLE = "IsVisible";
 
    static {  
        ModelCV.RaveColsList.add (ModelCV.RAVE_PROJECT_NAME); 
        ModelCV.RaveColsList.add (ModelCV.RAVE_DRAFT_NAME);
        ModelCV.RaveColsList.add (ModelCV.RAVE_OID);
        ModelCV.RaveColsList.add (ModelCV.RAVE_DRAFT_FORM_NAME);
        ModelCV.RaveColsList.add (ModelCV.RAVE_HELP_TEXT);
        ModelCV.RaveColsList.add (ModelCV.RAVE_ORDINAL);
        ModelCV.RaveColsList.add (ModelCV.RAVE_LINK_FORM_OID);
        ModelCV.RaveColsList.add (ModelCV.RAVE_FIELD_OID);
        ModelCV.RaveColsList.add (ModelCV.RAVE_DRAFT_FIELD_NAME);
        ModelCV.RaveColsList.add (ModelCV.RAVE_PRE_TEXT);
        ModelCV.RaveColsList.add (ModelCV.RAVE_FIXED_UNIT);
        ModelCV.RaveColsList.add (ModelCV.RAVE_HEADERTEXT);
        ModelCV.RaveColsList.add (ModelCV.RAVE_INDENT_LEVEL);
        ModelCV.RaveColsList.add (ModelCV.RAVE_CONTROL_TYPE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_DATA_DICTIONARY_NAME);
        ModelCV.RaveColsList.add (ModelCV.RAVE_DEFAULT_VALUE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_DATA_FORMAT);
        ModelCV.RaveColsList.add (ModelCV.RAVE_LOWER_RANGE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_UPPER_RANGE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_NCLOWER_RANGE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_NCUPPER_RANGE);
        ModelCV.RaveColsList.add (ModelCV.RAVE_IS_REQUIRED);
        ModelCV.RaveColsList.add (ModelCV.RAVE_IS_VISIBLE);
    
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_TEXT);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_TEXTA);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_SINGLES);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_RADIO);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_MULTIS);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_CHECKBOX);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_CALCULATION);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_GROUPC);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_FILE);
        ModelCV.OCResponseTList.add (ModelCV.OC_CRF_CV_RESPONSET_INSTANTC);
        
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_ST);
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_INT);
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_REAL);
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_DATE);
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_PDATE);
        ModelCV.OCDataTypeList.add (ModelCV.OC_CRF_CV_DATAT_FILE);
    }
    
    public static void initializeMappingFile (String mappingFileName) {
        
        GenUtil.validateString(mappingFileName);
        try {
            BufferedReader br = new BufferedReader (new FileReader (mappingFileName));
            String line;
            String [] headersArr = null;
            while ((line = br.readLine()) != null) {
                XOCMapper mapper;
                line = line.trim();
                String [] tempArr = line.split(GenUtil.TAB);
                if (tempArr.length != 6) {                 //i.e. no code/label
                    throw new IllegalStateException ("Invalid row in mapping file: "
                            + tempArr.length);
                }
                if (headersArr == null) {
                    headersArr = tempArr;  
                    continue;
                }
                //save entries. 
                mapper = new XOCMapper(); 
                mapper.setAttValOC (tempArr [0], tempArr [1]);
                //either both carry values or neither do.
                if (!GenUtil.NA.equals (tempArr [2]) &&    
                    !GenUtil.NA.equals (tempArr [3])) {
                    mapper.setAttValX (tempArr [2], tempArr [3]);
                }
                if (!GenUtil.NA.equals (tempArr [4])) {
                    mapper.setConstValue (tempArr [4]);
                }
                if (!GenUtil.NA.equals (tempArr [5])) {
                    mapper.setComment (tempArr [5]);
                } 
                ModelCV.mapperList.add(mapper);
            }
        } catch (IOException e) {
            throw new RuntimeException ("Unable to read mapping file."
                                        + mappingFileName);
        }
        //debugging
        
        System.out.println ("Listing all code/label pairs: ");
        for (int i = 0; i < ModelCV.mapperList.size (); i++) {
            System.out.println (ModelCV.mapperList.get (i));
        }
    }
    
    public static final ArrayList <XOCMapper> getMapping (String OCCategoryName,
                                                          String OCFieldName) {
    
        XOCMapper dummy = new XOCMapper();
        ArrayList <XOCMapper> tempList = new ArrayList ();
        dummy.setAttValOC(OCCategoryName, OCFieldName);
        for (int i = 0; i < ModelCV.mapperList.size (); i++) {
            if (ModelCV.mapperList.get (i).equals (dummy)) {
                tempList.add (ModelCV.mapperList.get(i));
            }
        }
        
        return tempList;
    }
    
    public static boolean isValidShowOrHide (String value) {
        
        return ModelCV.OC_CRF_CV_SHOW.equals (value) ||
               ModelCV.OC_CRF_CV_HIDE.equals (value);
    }
    
    public static boolean isValid0Or1 (String value) {
        
        return ModelCV.OC_CRF_0.equals (value) ||
               ModelCV.OC_CRF_1.equals (value);
    }
    
    public static boolean isValidRaveColName (String value) {
        
        return ModelCV.RaveColsList.contains(value);
    }
    
    public static boolean isValidOCResponseType (String value) {
        
        return ModelCV.OCResponseTList.contains (value);
    }
    
    public static boolean isValidOCDataType (String value) {
        
        return ModelCV.OCDataTypeList.contains (value);
    }
    
    public static boolean isValidOCResponseLayout (String value) {
        
        return ModelCV.OC_CRF_CV_RESPONSELAYOUT_HORIZONTAL.equals (value) ||
               ModelCV.OC_CRF_CV_RESPONSELAYOUT_VERTICAL.equals (value);
    }
}