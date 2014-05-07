package org.ocbn.octools.ocmodel;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * Encapsulation of an item in a CRF form in OC. 
 * 
 * @author Rashad Badrawi
 */

public class Item extends CRFEntity {

    private String name; 
    private String desc; 
    private String lText;
    private String rText;
    private String units; 
    private String header;
    private String subHeader;
    private String pageNum;            
    private String displayStatus;
    private String conditionalDisplay;   
    private int colNum;
    private int questNum;
    private int isPHI = -1;
    private int isRequired = -1;
    
    private Group groupRef;
    private Section sectionRef;
    private Item parent;
    
    private ItemResponse itemResponse; 

    public void setName (String nName) {
        
        GenUtil.validateString(nName);
        this.name = nName;
    }
    
    public void setDesc (String nDesc) {
        
        GenUtil.validateString(nDesc);
        this.desc = nDesc;
    }
        
    public void setLText (String nLText) {
        
        GenUtil.validateString(nLText);
        this.lText = nLText;
    }
            
    public void setRText (String nRText) {
        
        GenUtil.validateString(nRText);
        this.rText = nRText;
    }
                
    public void setUnits (String nUnits) {
        
        GenUtil.validateString(nUnits);
        this.units = nUnits;
    }
                    
    public void setHeader (String nHeader) {
        
        GenUtil.validateString(nHeader);
        this.header = nHeader;
    }
                        
    public void setSubHeader (String nSubHeader) {
        
        GenUtil.validateString(nSubHeader);
        this.subHeader = nSubHeader;
    }
                            
    public void setPageNum (String nPageNum) {
        
        GenUtil.validateString(nPageNum);
        this.pageNum = nPageNum;
    }
                                
    public void setDisStatus (String nDisplayStatus) {
        
        GenUtil.validateString(nDisplayStatus);
        this.displayStatus = nDisplayStatus;
    }
                                    
    public void setConDisplay (String nConditionalDisplay) {
        
        GenUtil.validateString(nConditionalDisplay);
        this.conditionalDisplay = nConditionalDisplay;
    }
    
    public void setColNum (int nColNum) {
        
        GenUtil.validateNonNegativeInt(nColNum);
        this.colNum = nColNum;
    }
    
    public void setQuestNum (int nQuestNum) {
        
        GenUtil.validateNonNegativeInt(nQuestNum);
        this.questNum = nQuestNum;
    }
    
    public void setIsRequired (int nIsRequired) {
        
        if (!ModelCV.isValid0Or1(String.valueOf(nIsRequired))) {
            throw new IllegalArgumentException ("Value out of acceptable range: " 
                                                + nIsRequired);
        }
        this.isRequired = nIsRequired;
    }
    
    public void setIsPHI (int nIsPHI) {
        
        if (!ModelCV.isValid0Or1(String.valueOf(nIsPHI))) {
            throw new IllegalArgumentException ("Value out of acceptable range: " 
                                                + nIsPHI);
        }
        this.isPHI = nIsPHI;
    }
    
    public void setGroupRef (Group nGroupRef) {
        
        GenUtil.validateNotNull(nGroupRef);
        this.groupRef = nGroupRef;
    }
    public void setSectionRef (Section nSectionRef) {
        
        GenUtil.validateNotNull(nSectionRef);
        this.sectionRef = nSectionRef;
    }
    
    public void setItem (Item nParent) {
        
        GenUtil.validateNotNull(nParent);
        this.parent = nParent;
    }
    
    public void setItemResponse (ItemResponse nItemResponse) {
        
        GenUtil.validateNotNull(nItemResponse);
        this.itemResponse = nItemResponse;
    }
    
    public String getName () { return this.name; }
    
    public String getDesc () { return this.desc; }
    
    public String getLText () { return this.lText; }
    
    public String getRText () { return this.rText; }
    
    public String getUnits () { return this.units; }
    
    public String getHeader () { return this.header; }
    
    public String getSubHeader () { return this.subHeader; }
    
    public String getPageNum () { return this.pageNum; }
    
    public String getDisStatus () { return this.displayStatus; }
    
    public String getConDisplay () { return this.conditionalDisplay; }  
    
    public int getColNum () { return this.colNum; }
    
    public int getQuestNum () { return this.questNum; }
    
    public int getIsRequired () { return this.isRequired; }
    
    public int getIsPHI () { return this.isPHI; }
    
    public Group getGroupRef () { return this.groupRef; }
    
    public Section getSectionRef () { return this.sectionRef; }
    
    public Item getParent () { return this.parent; }
    
    public ItemResponse getItemResponse () { return this.itemResponse; }
    
    @Override
    public boolean equals (Object o) {
        
        if (!(o instanceof Item)) {
            throw new IllegalArgumentException ("Invalid instance type: " +
                                                 o.getClass().getName());
        }
        return this.getName().equals (((Item)o).getName ());
    }

    @Override
    public int hashCode() {
        
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.desc);
        hash = 47 * hash + Objects.hashCode(this.lText);
        hash = 47 * hash + Objects.hashCode(this.rText);
        hash = 47 * hash + Objects.hashCode(this.units);
        hash = 47 * hash + Objects.hashCode(this.header);
        hash = 47 * hash + Objects.hashCode(this.subHeader);
        hash = 47 * hash + Objects.hashCode(this.pageNum);
        hash = 47 * hash + Objects.hashCode(this.displayStatus);
        hash = 47 * hash + Objects.hashCode(this.conditionalDisplay);
        hash = 47 * hash + this.colNum;
        hash = 47 * hash + this.questNum;
        hash = 47 * hash + this.isPHI;
        hash = 47 * hash + this.isRequired;
        hash = 47 * hash + Objects.hashCode(this.groupRef);
        hash = 47 * hash + Objects.hashCode(this.sectionRef);
        hash = 47 * hash + Objects.hashCode(this.parent);
        hash = 47 * hash + Objects.hashCode(this.itemResponse);
        
        return hash;
    }
    
    public static final String toStringHeaders () {
        
        return ModelCV.OC_ITEMS_ITEMN + GenUtil.TAB +
               ModelCV.OC_ITEMS_DESCRITIONL + GenUtil.TAB + 
               ModelCV.OC_ITEMS_LEFTIT + GenUtil.TAB +
               ModelCV.OC_ITEMS_UNITS + GenUtil.TAB +
               ModelCV.OC_ITEMS_RIGHTIT + GenUtil.TAB +
               ModelCV.OC_ITEMS_SECTIONL + GenUtil.TAB +
               ModelCV.OC_ITEMS_GROUPL + GenUtil.TAB +
               ModelCV.OC_ITEMS_HEADER + GenUtil.TAB +
               ModelCV.OC_ITEMS_SUBHEADER + GenUtil.TAB +
               ModelCV.OC_ITEMS_PARENTI + GenUtil.TAB +
               ModelCV.OC_ITEMS_COLUMNN + GenUtil.TAB +
               ModelCV.OC_ITEMS_PAGEN + GenUtil.TAB +
               ModelCV.OC_ITEMS_QUESTIONN + GenUtil.TAB +
               ItemResponse.toStringHeaders () + GenUtil.TAB +
               ModelCV.OC_ITEMS_PHI + GenUtil.TAB +
               ModelCV.OC_ITEMS_REQUIRED + GenUtil.TAB +
               ModelCV.OC_ITEMS_SIMPLEC;
    }
    
    @Override
    public String toString () {
        
        String str = this.getName() + GenUtil.TAB + 
                     this.getDesc() + GenUtil.TAB +
                     GenUtil.getStrOrDef (this.getLText()) + GenUtil.TAB +
                     GenUtil.getStrOrDef (this.getUnits()) + GenUtil.TAB +
                     GenUtil.getStrOrDef (this.getRText()) + GenUtil.TAB +
                     getSectionRef().getLabel() + GenUtil.TAB;
        if (getGroupRef() != null) {
            str += getGroupRef() + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        str += GenUtil.getStrOrDef (this.getHeader()) + GenUtil.TAB + 
               GenUtil.getStrOrDef (this.getSubHeader()) + GenUtil.TAB;  
        if (getParent()!= null) {
            str += this.getParent().getName() + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        
        if (this.getColNum() > 0) {
            str += this.getColNum() + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        str += GenUtil.getStrOrDef (this.getPageNum()) + GenUtil.TAB;
        if (this.getQuestNum() > 0) {
            str += this.getQuestNum () + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        
        str += getItemResponse().toString() + GenUtil.TAB;
        
        if (this.getIsPHI() >= 0) {
            str += this.getIsPHI () + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        if (this.getIsRequired() >= 0) {
            str += this.getIsRequired() + GenUtil.TAB;
        } else {
            str += GenUtil.NOVAL + GenUtil.TAB;
        }
        
        str += GenUtil.getStrOrDef (this.getDisStatus()) + GenUtil.TAB + 
               GenUtil.getStrOrDef (this.getConDisplay());
       
        return str;
    }
}
