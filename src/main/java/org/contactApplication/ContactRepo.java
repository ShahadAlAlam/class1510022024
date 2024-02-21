package org.contactApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.dataExpImporter.FileManager;
import org.taskManager.TaskData;
import org.taskManager.TaskDeserializer;
import org.taskManager.TaskSerializer;

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
        System.out.println("Found this/these contact informations");
        System.out.println(toString(results));
        return results;
    }

    public void exportInformation() {
        FileManager<ContactData, ContactSerializer, ContactDeserializer> fileManager = new FileManager();
        fileManager.exportInformation(new ContactData(this.internalContact),"contactInformation.txt", new ContactSerializer(ContactData.class));


//        ObjectMapper objectMapper = new ObjectMapper();
//
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(ContactData.class, new ContactSerializer(ContactData.class));
//        objectMapper.registerModule(module);
//        ContactData conData = new ContactData(this.internalContact) ;
//
//        try {
//            String rootPath = System.getProperty("user.dir");
////            System.out.println(rootPath );
//            File obj = new File(rootPath+"/contactInformation.txt" );
////            Gson gson = new Gson();
//            if (obj.createNewFile()) {
//                FileWriter fr = new FileWriter(obj);
////                String jsonArray = objectMapper.writeValueAsString (this.internalContact);
//                String jsonArray = objectMapper.writeValueAsString (conData);
////                fr.write(gson.toJson(this.internalContact) );
//                fr.write(jsonArray);
//                fr.close();
//            } else {
//                FileWriter fr = new FileWriter(rootPath+"/contactInformation.txt");
////                String jsonArray = objectMapper.writeValueAsString(this.internalContact);
//                String jsonArray = objectMapper.writeValueAsString (conData);
////                fr.write(gson.toJson(this.internalContact) );
//                fr.write(jsonArray);
//                fr.close();
//            }
//            System.out.println("File Exported to \""+rootPath+"\" directory");
//        } catch (IOException e){
//
//        }
    }

    public void importInformation() {
        FileManager<ContactData,ContactSerializer,ContactDeserializer> fileManager = new FileManager();
        this.internalContact.clear();
        this.internalContact = fileManager.importInformation("contactInformation.txt", new ContactDeserializer(ContactData.class),ContactData.class);


//        //TESTING SERILIZER Working
//        try {
//            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath );
//
//            File obj = new File(rootPath+"/contactInformation.txt" );
//            InputStream in = new FileInputStream(obj);
//
//            String sData = new BufferedReader(
//                    new InputStreamReader(in, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.joining("\n"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            SimpleModule module = new SimpleModule();
//            module.addDeserializer(ContactData.class, new ContactDeserializer(ContactData.class));
//            objectMapper.registerModule(module);
//            TreeMap<String,Contact> data = objectMapper.readValue(sData,ContactData.class);
////            TreeMap<String,Contact> data = objectMapper.readValue(sData,TreeMap.class);
//            this.internalContact = data;
//            System.out.println("File Successfully Imported from \""+rootPath+"\\contactInformation.txt\" ");
//        } catch (IOException e){}

//        //Working Import for TreeMap<String,Contact>
//        try {
//            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath );
//
//            File obj = new File(rootPath+"/contactInformation.txt" );
//            InputStream in = new FileInputStream(obj);
//
//            String sData = new BufferedReader(
//                    new InputStreamReader(in, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.joining("\n"));
//            ObjectMapper objectMapper = new ObjectMapper();
////            Gson gson = new Gson();
////            TreeMap<Long,Contact> data = gson.fromJson(sData,TreeMap.class);
//            TreeMap<String,Contact> data = objectMapper.readValue(sData,TreeMap.class);
//
//            this.internalContact.clear();
//
//            for(Map.Entry<String,Contact> c : data.entrySet()){
//                Contact d=new Contact((Map<String, Object>) c.getValue());
//                this.internalContact.put(d.getContactId(),d);
//            }
//            System.out.println("File Successfully Imported from \""+rootPath+"\\contactInformation.txt\" ");
//        } catch (IOException e){}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String,Contact> c : this.internalContact.entrySet()){
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
