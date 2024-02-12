package org.contactApplication;

import java.util.HashMap;
import java.util.Map;

public class Contact implements Comparable<Contact> {
    private String contactId;
    private String fName;
    private String lName;
    private String mName;
    private String email;
    private String phone;

    public Contact(String contactId, String fName, String lName, String mName, String email, String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.email = email;
        this.phone = phone;
    }

    public Contact(String contactId, String fName, String lName, String email, String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }

    public Contact(String contactId, String fName, String lName,  String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Contact(String contactId, String fName,   String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.phone = phone;
    }

    public Contact Contact(Contact value) {
        return value;
    }

    @Override
    public int compareTo(Contact o) {
        if(getfName().equals(o.getfName())){
            return 0;
        } else if (getfName().hashCode() > o.getfName().hashCode()) {
            return 1;
        } else
            return -1;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Integer.parseInt( String.valueOf( contactId)) +"={" +
                " contactId=" + Integer.parseInt( String.valueOf( contactId)) + "," +
                " First Name=" + fName + "," +
                " Middle Name=" + mName + "," +
                " Last Name=" + lName + "," +
                " e-mail=" + email + "," +
                " phone=" + phone +
                " }";
//        return "{" +
//                " contactId=" + Integer.parseInt( String.valueOf( contactId)) + "," +
//                " First Name=" + fName + "," +
//                " Middle Name=" + mName + "," +
//                " Last Name=" + lName + "," +
//                " e-mail=" + email + "," +
//                " phone=" + phone +
//                " }";
    }

    public HashMap<String,Object> toHashMap(){
        HashMap<String,Object> val = new HashMap<>();
        val.put("contactId",this.contactId);
        val.put("fName",this.fName);
        val.put("mName",this.mName);
        val.put("lName",this.lName);
        val.put("email",this.email);
        val.put("phone",this.phone);
        return val;
    }


    public Contact(Map<String,Object> val){
        this.contactId = val.get("contactId").toString();
        this.fName = val.get("fName").toString();
        this.mName = val.get("mName").toString();
        this.lName = val.get("lName").toString();
        this.email = val.get("email").toString();
        this.phone = val.get("phone").toString();
    }
}
