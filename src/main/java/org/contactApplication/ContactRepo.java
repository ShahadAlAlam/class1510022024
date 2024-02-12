package org.contactApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ContactRepo {
    private static TreeMap<String,Contact> internalContact = new TreeMap<>();

    public TreeMap<String, Contact> getInternalContact() {
        return internalContact;
    }

    public boolean addContact(String fName, String lName, String mName, String email, String phone){
        try {
            TreeMap<String, Contact> e = this.internalContact;
            Long id = 0L;
            if(e.size()>0)
                id = 1L + (e.lastKey()!=null?Long.parseLong(String.valueOf(e.lastKey())):0L);
            else{
                id = 1L;
            }
            Contact con = new Contact(String.valueOf(id), fName, lName, mName, email, phone);
            this.internalContact.put(String.valueOf(id), con);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateContact(Contact con){
        try{
            this.internalContact.remove( String.valueOf(con.getContactId()));
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
        System.out.println(toString(this.internalContact));
    }

    public TreeMap<String,Contact> searchContact(String fname){
        TreeMap<String,Contact> results = new TreeMap<>();
        Iterator itr = this.getInternalContact().entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<String,Contact> t = (Map.Entry<String, Contact>) itr.next();
            Contact c = t.getValue();
//            System.out.println(d.getValue());
            if(c.getfName().toUpperCase().contains(fname.toUpperCase())){
                results.put(c.getContactId(),c);
            }
        }

////        this.getInternalContact().entrySet().iterator();
//        Iterator itr = this.getInternalContact().entrySet().iterator();
//        while(itr.hasNext()){
//            Map.Entry<String,Object> d= (Map.Entry<String,Object>) itr.next();
//            Contact c =new Contact((Map<String, Object>) d.getValue());
////            System.out.println(d.getValue());
//            if(c.getfName().toUpperCase().contains(fname.toUpperCase())){
//                results.put(c.getContactId(),c);
//            }
//        }
        System.out.println("Found this/these contact informations");
        System.out.println(toString(results));

//        for(HashMap.Entry<Long,HashMap<String,Object>> c:this.getInternalContact().entrySet()){
//            HashMap<String,Object> d =c.getValue();
//            Contact value = new Contact(d);
////            if(value.getfName().toUpperCase().contains(fname.toUpperCase())){
////                results.put(c.getValue().getContactId(),c.getValue());
////            }
//        }
        return results;
    }

    public void exportInformation() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );
            File obj = new File(rootPath+"/contactInformation.txt" );
//            Gson gson = new Gson();
            if (obj.createNewFile()) {
                FileWriter fr = new FileWriter(obj);
                String jsonArray = objectMapper.writeValueAsString(this.internalContact);
//                fr.write(gson.toJson(this.internalContact) );
                fr.write(jsonArray);
                fr.close();
            } else {
                FileWriter fr = new FileWriter(rootPath+"/contactInformation.txt");
                String jsonArray = objectMapper.writeValueAsString(this.internalContact);
//                fr.write(gson.toJson(this.internalContact) );
                fr.write(jsonArray);
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
            ObjectMapper objectMapper = new ObjectMapper();
//            Gson gson = new Gson();
//            TreeMap<Long,Contact> data = gson.fromJson(sData,TreeMap.class);
            TreeMap<String,Contact> data = objectMapper.readValue(sData,TreeMap.class);

            this.internalContact.clear();

            for(Map.Entry<String,Contact> c : data.entrySet()){
                Contact d=new Contact((Map<String, Object>) c.getValue());
                this.internalContact.put(d.getContactId(),d);
            }

//            data.stream().forEach(e->{
//
//                Contact c = e.get(e.lastKey());
//                this.internalContact.put(e.lastKey(),c);
//            });

//            this.internalContact  = data.entrySet().stream().collect(
//                    Collectors.toMap(
//                            Map.Entry::getKey,
//                           Map.Entry::getValue,
//                    (oldValue,newValue)->newValue,TreeMap::new)
//            );
//           this.internalContact = data;
//            this.internalContact = JsonLoader.readFile(in);
        } catch (IOException e){

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String,Contact> c : this.internalContact.entrySet()){
//            Contact d=new Contact((Map<String, Object>) c.getValue());
//            Contact d=new Contact((Map<String, Object>) c.getValue());
            sb.append(c.toString()+"\n");
        }
        return sb.toString();
    }
    public String toString(TreeMap<String,Contact> con) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Contact> c : con.entrySet()){
            Contact d=new Contact(c.getValue().toHashMap());
            sb.append(d.toString()+"\n");
        }
        return sb.toString();
    }
}
