package org.ocbn.octools.model;

import java.util.ArrayList;
import org.ocbn.octools.util.GenUtil;

/**
 * Controlled vocabularies for all the variables. Dispenses the appropriate mapping. 
 * Could optionally move some of its CV to a prop file or the likes.
 * 
 * @author Rashad Badrawi
 */

public class ModelCV {
    
    private static ArrayList <String> RaveColsList = new ArrayList <String>();
    private static ArrayList <String> OCResponseTList = new ArrayList <String>();
    private static ArrayList <String> OCDataTypeList = new ArrayList <String>();
    
    //Supported EDC CRF types
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
    public static final String OC_ITEMS_DEC_WID = "WIDTH_DECIMAL";
    public static final String OC_ITEMS_VALID = "VALIDATION";	
    public static final String OC_ITEMS_VALID_ERR = "VALIDATION_ERROR_MESSAGE";
    public static final String OC_ITEMS_PHI = "PHI";
    public static final String OC_ITEMS_REQUIRED = "REQUIRED";
    public static final String OC_ITEMS_ITEMD = "ITEM_DISPLAY_STATUS";
    public static final String OC_ITEMS_SIMPLEC = "SIMPLE_CONDITIONAL_DISPLAY";	
    //OC CRF - CV
    //Generic
    public static final String OC_CRF_CV_SHOW = "SHOW";
    public static final String OC_CRF_CV_HIDE = "HIDE";
    public static final int OC_CRF_0 = 0;
    public static final int OC_CRF_1 = 1;
    public static final String OC_CRF_DEF_VERSION = "1";
    public static final String OC_CRF_DEF_REV_NOTES = "Data Migration: Rave";
    //public static final int OC_CRF_GROUP_RNUM_DEFAULT = 1;
    //public static final int OC_CRF_GROUP_RMAX_DEFAULT = 40;
    public static final String OC_VALID_LT = "func: lt";
    public static final String OC_VALID_GT = "func: gt";
    public static final String OC_VALID_RANGE = "func: range";
    
    //Specific
    public static final String OC_CRF_CV_GROUP_LABEL_BASIC = "Basic"; //basic grouping
    public static final String OC_CRF_CV_GROUP_LAYOUT_GRID = "GRID";
    public static final String OC_CRF_CV_GROUP_LAYOUT_NONR = "NON-REPEATING";
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
    public static final String OC_CRF_CV_RESPONSELAYOUT_HOR = "HORIZONTAL";
    public static final String OC_CRF_CV_RESPONSELAYOUT_VER = "VERTICAL";
    public static final String OC_CRF_CV_DATAT_ST = "ST";
    public static final String OC_CRF_CV_DATAT_INT = "INT";
    public static final String OC_CRF_CV_DATAT_REAL = "REAL";
    public static final String OC_CRF_CV_DATAT_DATE = "DATE";
    public static final String OC_CRF_CV_DATAT_PDATE = "PDATE";
    public static final String OC_CRF_CV_DATAT_FILE = "FILE";
    
    //Rave CVs. 
    //Control types - incomplete list (?)
    public static final String RAVE_CT_CHECKBOX = "CheckBox";
    public static final String RAVE_CT_RADIO = "RadioButton";
    public static final String RAVE_CT_RADIOV = "RadioButton (Vertical)";
    public static final String RAVE_CT_SEARCHL = "SearchList";
    public static final String RAVE_CT_SEARCHDL = "Dynamic SearchList";
    public static final String RAVE_CT_LONGTEXT = "LongText";
    public static final String RAVE_CT_DATETIME = "DateTime";
    public static final String RAVE_CT_TEXT = "Text";
    //Generic
    public static final String RAVE_FALSE = "FALSE";
    public static final String RAVE_TRUE = "TRUE";
    
    
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
    public static final String RAVE_HEADER_TEXT = "HeaderText";
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
    //Rave - DD sheets
    public static final String RAVE_DD_NAME = "DataDictionaryName";
    public static final String RAVE_DD_CODE_DATA = "CodedData";
    public static final String RAVE_DD_USER_DATA = "UserDataString";
    public static final String RAVE_DD_SPECIFY = "Specify";
            
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
        ModelCV.RaveColsList.add (ModelCV.RAVE_HEADER_TEXT);
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
        
    public static boolean isValidShowOrHide (String value) {
        
        return ModelCV.OC_CRF_CV_SHOW.equals (value) ||
               ModelCV.OC_CRF_CV_HIDE.equals (value);
    }
    
    public static boolean isValid0Or1 (int value) {
        
        return (ModelCV.OC_CRF_0 == value) || (ModelCV.OC_CRF_1 == value);
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
        
        return ModelCV.OC_CRF_CV_RESPONSELAYOUT_HOR.equals (value) ||
               ModelCV.OC_CRF_CV_RESPONSELAYOUT_VER.equals (value);
    }
    
    //incomplete list
    public static String getOCResponseType (String RaveResponseType) {
        
        GenUtil.validateString(RaveResponseType);
        switch (RaveResponseType) {
            case ModelCV.RAVE_CT_CHECKBOX: 
                return ModelCV.OC_CRF_CV_RESPONSET_CHECKBOX;  
            case ModelCV.RAVE_CT_DATETIME: 
                return ModelCV.OC_CRF_CV_RESPONSET_TEXT;  
            case ModelCV.RAVE_CT_LONGTEXT: 
                return ModelCV.OC_CRF_CV_RESPONSET_TEXTA;  
            case ModelCV.RAVE_CT_RADIO: 
                return ModelCV.OC_CRF_CV_RESPONSET_RADIO;  
            case ModelCV.RAVE_CT_RADIOV: 
                return ModelCV.OC_CRF_CV_RESPONSET_RADIO;
            case ModelCV.RAVE_CT_SEARCHDL:
                return ModelCV.OC_CRF_CV_RESPONSET_SINGLES;
            case ModelCV.RAVE_CT_SEARCHL: 
                return ModelCV.OC_CRF_CV_RESPONSET_SINGLES;
            case ModelCV.RAVE_CT_TEXT: 
                return ModelCV.OC_CRF_CV_RESPONSET_TEXT;
        }
        throw new IllegalArgumentException ("Invalid RAVE control type: " +
                                            RaveResponseType);
    }
}