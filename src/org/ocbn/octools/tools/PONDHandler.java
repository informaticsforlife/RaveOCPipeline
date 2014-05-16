package org.ocbn.octools.tools;

import org.ocbn.octools.model.Group;
import org.ocbn.octools.model.Item;
import org.ocbn.octools.model.ModelCV;
import org.ocbn.octools.model.ModelContainer;

/**
 * A utility class that encapsulates the POND study specific RAVE-OC conversions. 
 * It provides post-processing for OC Items after they have been processing via
 * the generic conversion process. 
 * Some of the parsing included is not fail proof and is obviously data and 
 * study dependent. 
 * 
 * @author Rashad Badrawi
 */

public class PONDHandler {

    private static final String RAVE_STUDY_POND = "POND";
    private static final String BLANK_LINE_ITEM = "BLANK_LINE";
    private static final String HEADING_ITEM = "_HEADING";
    private static final String SUBHEADING_ITEM = "_SUBHEADING";
    private static final String INSTRUCT_ITEM = "_INSTRUCTIONS"; 
    private static final String YES_NO_DD = "No_0_Yes_1";
    private static String nHeaderStr = "";
    private static String nSubHeaderStr = "";
    
    protected static boolean processStudyInstruct (ModelContainer OCContainer, 
                                                    Item item) {
        
        if (!OCContainer.getOCCRF().getName().equals (PONDHandler.RAVE_STUDY_POND)) {
            System.out.println ("Warning: Not a POND study.");
            return false;
        }
        //To begin with, highlight POND CRF blank lines.
        if (item.getName().startsWith(PONDHandler.BLANK_LINE_ITEM)) {
            System.out.println ("Warning: Blank line item: " + 
                       item.getName() + " " + item.getSectionRef().getLabel());
            return false;
        }
        //add the heading item's text to the following item, then drop it. 
        if (item.getName().contains(PONDHandler.HEADING_ITEM) ||
            item.getName().contains (PONDHandler.SUBHEADING_ITEM)) {
            System.out.println ("Warning: heading item: " +
                        item.getName() + " " + item.getSectionRef().getLabel());
            PONDHandler.nHeaderStr += item.getLText();   //assumes no rText
            return false;
        }
        //add the instruct item's text to the following item, then drop it.
        if (item.getName ().contains(PONDHandler.INSTRUCT_ITEM)) {
            System.out.println ("Warning: instructions item: " +
                        item.getName() + " " + item.getSectionRef().getLabel());
            PONDHandler.nSubHeaderStr += item.getLText();
            return false;
        }
        //add default group for POND 
        Group defGroup = OCContainer.getGroups().get (0);
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
            RaveDDMapper.processDD (item.getItemResponse(), PONDHandler.YES_NO_DD);
        }
        //search lists with no DD. May also add the multiple select case.
        if (item.getItemResponse().getResponseType()
                .equals (ModelCV.OC_CRF_CV_RESPONSET_SINGLES) &&
            item.getItemResponse().getResponseLabel() == null) {
            item.getItemResponse().setResponseType(ModelCV.OC_CRF_CV_RESPONSET_TEXT);
        }
        //previous item was an instruct or header item.
        if (PONDHandler.nHeaderStr.length () > 0) {
            if (item.getHeader() != null) {
                item.setHeader(item.getHeader() + nHeaderStr);
            } else {
                item.setHeader(nHeaderStr);
            }
            PONDHandler.nHeaderStr = "";
        }
        if (PONDHandler.nSubHeaderStr.length () > 0) {
            if (item.getSubHeader() != null) {
                item.setSubHeader (item.getSubHeader() + nSubHeaderStr);
            } else {
                item.setSubHeader(nSubHeaderStr);
            }
            PONDHandler.nSubHeaderStr = "";
        }
        
        return true;
    } 
}
