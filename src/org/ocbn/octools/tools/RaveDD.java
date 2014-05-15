package org.ocbn.octools.tools;

import java.util.ArrayList;
import org.ocbn.octools.util.AttValLabel;
import org.ocbn.octools.util.GenUtil;

/**
 * Represents data dictionaries in a RAVE CRF. A utility class needed by the 
 reader and the RaveDD mapper. 
 Format: Attribute: RaveDD Name_Order
         Value: code
         Label: display label
 * 
 * @author Rashad Badrawi
 */

public class RaveDD {
   
    private String name;
    private ArrayList <AttValLabel> attValList;
    private ArrayList <String> specifyFlagList;     //placeholder, if needed
    
    public RaveDD () {
    
        this.attValList = new ArrayList <AttValLabel> ();
        this.specifyFlagList = new ArrayList <> ();
    }
  
    public void setName (String nName) {
        
        GenUtil.validateString (nName);
        this.name = nName;
    }
    
    public void addDDEntry (String code, int ordinal, String label, String specifyFlag) {
        
        GenUtil.validatePositiveInt(ordinal);
        GenUtil.validateString (label);
        GenUtil.validateBool(specifyFlag);
        
        AttValLabel avl = new AttValLabel(); 
        //avl.setName(name + GenUtil.UNDERSCORE + ordinal);
        avl.setName (String.valueOf (ordinal));
        avl.setVal(code);
        avl.setValLabel(label);
        this.attValList.add (avl);
        this.specifyFlagList.add (specifyFlag);
    }
    
    public String getName () { return this.name; }
    
    public ArrayList <AttValLabel> getDDEntries () { return this.attValList; } 
    
    @Override
    //a table
    public String toString () {
        
        String str = "";
        for (int i = 0; i < this.getDDEntries().size(); i++) {
            str += this.getName() + GenUtil.TAB + this.getDDEntries().get (i).toString ();
            str += GenUtil.NEWLINE;
        }
        
        return str;
    }
}
