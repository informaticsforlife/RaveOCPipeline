package org.ocbn.octools.model;

import java.util.ArrayList;
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
    private ArrayList <Section> sectionsList = new ArrayList <> ();;
    private ArrayList <Group> groupsList = new ArrayList <> ();; 
    private ArrayList <Item> itemsList = new ArrayList <> ();;
    
    public void addCRF (CRF nCRF) {
    
        GenUtil.validateNotNull(nCRF);
        this.OCCRF = nCRF;
    }
    
    public void addSection (Section nSection) {
        
        GenUtil.validateNotNull(nSection);
        if (this.sectionsList.contains (nSection)) {
            System.out.println ("Overwriting existing Section: " + 
                                nSection.getLabel());
        }
        this.sectionsList.add (nSection);
    }
    
    public void addGroup (Group nGroup) {
        
        GenUtil.validateNotNull(nGroup);
        if (this.groupsList.contains (nGroup)) {
            System.out.println ("Overwriting existing Group: " + 
                                nGroup.getLabel());
        }
        this.groupsList.add (nGroup);
    }
        
     public void addItem (Item nItem) {
        
        GenUtil.validateNotNull(nItem);
        if (this.itemsList.contains (nItem)) {
            System.out.println ("Overwriting existing Item: " + nItem.getName());
        }
        //eliminate duplicate names. The first duplicate will not be modified.
        for (int i = 0; i < this.itemsList.size (); i++) {
            if (this.itemsList.get (i).getName().equals (nItem.getName())) {
                System.out.println ("Warning: Duplicate item names. "
                                    + "Renaming the latter: " + nItem.getName() 
                                    + " " + nItem.getSectionRef().getLabel());
                nItem.setName(nItem.getName () + GenUtil.UNDERSCORE + 
                              nItem.getSectionRef().getLabel());    
            }
        }
        this.itemsList.add (nItem);
    }

    public CRF getOCCRF () { return this.OCCRF; }
    
    public ArrayList <Section> getSections () { 
        
        return this.sectionsList; 
    }
    
    public ArrayList <Group> getGroups () {
        
        return this.groupsList;
    }
    
    public ArrayList <Item> getItems () {
        
        return this.itemsList;
    }
}
