package org.ocbn.octools.ocmodel;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * Encapsulation of a CRF section in OC. 
 * 
 * @author Rashad Badrawi
 */

public class Section extends CRFEntity {
    
    private String label;
    private String title;
    private String subTitle;
    private String instruct;
    private String pageNum;                               //letters or numbers
    private Section pSection; 
    
    public void setLabel (String nLabel) {
        
        GenUtil.validateString (nLabel);
        this.label = nLabel;
    }
    
    public void setTitle (String nTitle) {
        
        GenUtil.validateString (nTitle);
        this.title = nTitle;
    }
    
    public void setSubTitle (String nSubTitle) {
        
        GenUtil.validateString (nSubTitle);
        this.subTitle = nSubTitle;
    }
    
    public void setInstruct (String nInstruct) {
        
        GenUtil.validateString (nInstruct);
        this.instruct = nInstruct;
    }
    
    public void setPageNum (String nPageNum) {
        
        GenUtil.validateString(nPageNum);
        this.pageNum = nPageNum;
    }
    
    public void setParentSection (Section nParentSection) {
        
        GenUtil.validateNotNull(nParentSection);
        this.pSection = nParentSection;
    }
    
    public String getLabel () { return this.label; }
    
    public String getTitle () { return this.title; }

    public String getSubTitle () { return this.subTitle; }
    
    public String getInstruct () { return this.instruct; }
    
    public String getPageNum () { return this.pageNum; }
    
    public Section getParentSection () { return this.pSection; }
    
    @Override
    public boolean equals (Object o) {
        
        if (!(o instanceof Section)) {
            throw new IllegalArgumentException ("Invalid instance type: " +
                                                 o.getClass ().getName());
        }
        return this.getLabel().equals (((Section)o).getLabel ());
    }

    @Override
    public int hashCode() {
        
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.label);
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.subTitle);
        hash = 29 * hash + Objects.hashCode(this.instruct);
        hash = 29 * hash + Objects.hashCode(this.pageNum);
        hash = 29 * hash + Objects.hashCode(this.pSection);
        
        return hash;
    }
    
    public static final String toStringHeaders () {
        
        return ModelCV.OC_SECTIONS_SECTIONL + GenUtil.TAB + 
               ModelCV.OC_SECTIONS_SECTIONT + GenUtil.TAB +
               ModelCV.OC_SECTIONS_SUBTITLE + GenUtil.TAB +
               ModelCV.OC_SECTIONS_INSTRUCTIONS + GenUtil.TAB + 
               ModelCV.OC_SECTIONS_PAGEN + GenUtil.TAB + 
               ModelCV.OC_SECTIONS_PARENT;
    }
    
    @Override
    public String toString () {
        
        String str = this.getLabel() + GenUtil.TAB + 
                     GenUtil.getStrOrDef (this.getTitle()) + GenUtil.TAB +
                     GenUtil.getStrOrDef (this.getSubTitle()) + GenUtil.TAB +
                     GenUtil.getStrOrDef (this.getInstruct()) + GenUtil.TAB +
                     GenUtil.getStrOrDef(this.getPageNum()) + GenUtil.TAB;
        if (this.getParentSection() != null) {
            str += this.getParentSection().getLabel();
        } else {
            str += GenUtil.NOVAL;
        }
        str += GenUtil.TAB;
        
        return str;
    }
}
