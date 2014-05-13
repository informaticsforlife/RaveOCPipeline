package org.ocbn.octools.model;

import java.util.Objects;
import org.ocbn.octools.util.GenUtil;

/**
 * Encapsulation of a CRF section in OC. 
 * 
 * @author Rashad Badrawi
 */

public class Section extends CRFEntity {
    
    private String subTitle;
    private String instruct;
    private String pageNum;                               //letters or numbers
    
    public void setLabel (String nLabel) { this.setIDShort(nLabel); }
    
    public void setTitle (String nTitle) { this.setIDLong(nTitle); }
    
    public void setParentSection (Section nParentSection) { 
        
        this.setPointer(nParentSection);
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
    
    public String getLabel () { return this.getIDShort(); }
    
    public String getTitle () { return this.getIDLong(); }
    
    public Section getParentSection () { return (Section)this.getPointer(); }

    public String getSubTitle () { return this.subTitle; }
    
    public String getInstruct () { return this.instruct; }
    
    public String getPageNum () { return this.pageNum; }
    
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
        hash = 23 * hash + Objects.hashCode(this.getLabel());
        
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
