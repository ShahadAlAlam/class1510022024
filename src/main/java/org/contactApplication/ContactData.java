package org.contactApplication;

import java.util.TreeMap;

public class ContactData extends TreeMap<String,Contact> {
//    private String s;
//
    ContactData(){

    }
    ContactData(TreeMap<String,Contact> con){
        super(con);
    }

    ContactData(String s, Contact c){
        super.put(s,c);
    }

    public void addSerializer(Class<ContactData> contactDataClass, ContactSerializer contactSerializer) {

    }
}
