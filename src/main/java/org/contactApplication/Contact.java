package org.contactApplication;

public class Contact implements Comparable<Contact>{
    private Long contactId;
    private String fName;
    private String lName;
    private String mName;
    private String email;
    private String phone;

    public Contact(Long contactId, String fName, String lName, String mName, String email, String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.email = email;
        this.phone = phone;
    }

    public Contact(Long contactId, String fName, String lName, String email, String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }

    public Contact(Long contactId, String fName, String lName,  String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
    }

    public Contact(Long contactId, String fName,   String phone) {
        this.contactId = contactId;
        this.fName = fName;
        this.phone = phone;
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

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
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
        return "Contact {" +
                " contactId=" + Integer.parseInt( String.valueOf( contactId)) + "\n" +
                " First Name=" + fName + "\n" +
                " Middle Name=" + mName + "\n" +
                " Last Name=" + lName + "\n" +
                " e-mail=" + email + "\n" +
                " phone=" + phone + "\n" +
                '}';
    }
}
