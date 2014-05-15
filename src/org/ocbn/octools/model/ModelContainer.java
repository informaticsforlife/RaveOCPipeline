package org.ocbn.octools.model;

import java.util.Iterator;
import java.util.TreeMap;
import org.ocbn.octools.util.GenUtil;

/**
 * Container class for all entities. Works hand in hand with the reader/writer.
 * It can hold one CRF and its associated sections, items, ...etc.
 * 
 * @author Rashad Badrawi
 */

public class ModelContainer {
   
    //Entities
    private CRF OCCRF;
    private TreeMap <String, Section> sectionMap = new TreeMap <> ();;
    private TreeMap <String, Group> groupMap = new TreeMap <> ();; 
    private TreeMap <String, Item> itemMap = new TreeMap <> ();;
    
    public void addCRF (CRF nCRF) {
    
        GenUtil.validateNotNull(nCRF);
        this.OCCRF = nCRF;
    }
    
    public void addSection (Section nSection) {
        
        GenUtil.validateNotNull(nSection);
        if (this.sectionMap.containsKey(nSection.getLabel())) {
            System.out.println ("Overwriting existing Section: " + 
                                nSection.getLabel());
        }
        this.sectionMap.put (nSection.getLabel(), nSection);
    }
    
    public void addGroup (Group nGroup) {
        
        GenUtil.validateNotNull(nGroup);
        if (this.groupMap.containsKey(nGroup.getLabel())) {
            System.out.println ("Overwriting existing Group: " + 
                                nGroup.getLabel());
        }
        this.groupMap.put (nGroup.getLabel(), nGroup);
    }
        
     public void addItem (Item nItem) {
        
        GenUtil.validateNotNull(nItem);
        if (this.itemMap.containsKey(nItem.getSectionRef().getLabel() + 
                                     GenUtil.AT + nItem.getName())) {
            System.out.println ("Overwriting existing Item: " + 
                                nItem.getName());
        }
        //eliminate duplicate names. The first duplicate will not be modified.
        Iterator iterator = this.itemMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            Item pItem = this.itemMap.get(key);
            if (pItem.getName().equals(nItem.getName())) {
                nItem.setName(nItem.getName () + GenUtil.UNDERSCORE + 
                              nItem.getSectionRef().getLabel());
            }
        }
        this.itemMap.put (nItem.getSectionRef().getLabel() + GenUtil.AT 
                          + nItem.getName(), nItem);
    }

    public CRF getOCCRF () { return this.OCCRF; }
    
    public TreeMap <String, Section> getSections () { 
        
        return this.sectionMap; 
    }
    
    public TreeMap <String, Group> getGroups () {
        
        return this.groupMap;
    }
    
    public TreeMap <String, Item> getItems () {
        
        return this.itemMap;
    }
}
