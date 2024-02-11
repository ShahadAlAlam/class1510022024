package org.contactApplication;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
                id = 1L + (e.lastKey()!=null?Long.parseLong(String.valueOf(e.lastKey())):0L);
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
        System.out.println(this.toString());
    }

    public TreeMap<Long,Contact> searchContact(String fname){
        TreeMap<Long,Contact> results = new TreeMap<>();
        for(Map.Entry<Long,Contact> c:this.getInternalContact().entrySet()){
            if(c.getValue().getfName().toUpperCase().contains(fname.toUpperCase())){
                results.put(c.getValue().getContactId(),c.getValue());
            }
        }
        return results;
    }

    public void exportInformation() {
        try {
            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );
            File obj = new File(rootPath+"/contactInformation.txt" );
            Gson gson = new Gson();
            if (obj.createNewFile()) {
                FileWriter fr = new FileWriter(obj);
                fr.write(gson.toJson(this.internalContact) );
                fr.close();
            } else {
                FileWriter fr = new FileWriter(rootPath+"/contactInformation.txt");
                fr.write(gson.toJson(this.internalContact) );
                fr.close();

            }
        } catch (IOException e){

        }
    }

    public void importInformation() {
        try {
            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );

            File obj = new File(rootPath+"/contactInformation.txt" );
            InputStream in = new FileInputStream(obj);

            String sData = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            Gson gson = new Gson();
            TreeMap<Long,Contact> data = gson.fromJson(sData,TreeMap.class);
           this.internalContact = data;
//            this.internalContact = JsonLoader.readFile(in);
        } catch (IOException e){

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Long,Contact> c : this.internalContact.entrySet()){
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
}
