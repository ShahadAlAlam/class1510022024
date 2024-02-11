package org.contactApplication;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ContactRepo {
    private static TreeMap<Long,Contact> internalContact = new TreeMap<>();

    public TreeMap<Long, Contact> getInternalContact() {
        return internalContact;
    }

    public boolean addContact(String fName, String lName, String mName, String email, String phone){
        try {
            TreeMap<Long, Contact> e = this.internalContact;
            Long id = 0L;
            if(e.size()>0)
                id = 1L + (e.lastKey()!=null?e.lastKey():0L);
            else{
                id = 1L;
            }
            Contact con = new Contact(id, fName, lName, mName, email, phone);
            this.internalContact.put(id, con);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateContact(Contact con){
        try{
        this.internalContact.put(con.getContactId(),con);
        return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean removeContact(Contact con){
        try {
            this.internalContact.remove(con.getContactId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void seeAllContact(){
        System.out.println(this.internalContact.toString());
    }

    public TreeMap<Long,Contact> searchContact(String fname){
        return (TreeMap<Long, Contact>) this.internalContact.values().stream()
                .filter(e->e.getfName().toUpperCase().contains(fname.toUpperCase()));
    }
}
