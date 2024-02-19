package org.dataExpImporter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.contactApplication.Contact;
import org.taskManager.Tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileManager <T> {
    public void exportInformation(T dataToStore,String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath );
            File obj = new File(rootPath+"/"+fileName );
//            Gson gson = new Gson();
            if (obj.createNewFile()) {
                FileWriter fr = new FileWriter(obj);
                String jsonArray = objectMapper.writeValueAsString(dataToStore);
//                fr.write(gson.toJson(this.internalContact) );
                fr.write(jsonArray);
                fr.close();
            } else {
                FileWriter fr = new FileWriter(rootPath+"/"+fileName);
                String jsonArray = objectMapper.writeValueAsString(dataToStore);
//                fr.write(gson.toJson(this.internalContact) );
                fr.write(jsonArray);
                fr.close();
            }
            System.out.println("File Exported to \""+rootPath+"\" directory");
        } catch (IOException e){

        }
    }

    public T importInformation(T dataToStore,String fileName) {
        try {

            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );

            File obj = new File(rootPath+"/"+fileName );
            InputStream in = new FileInputStream(obj);

            String sData = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            ObjectMapper objectMapper = new ObjectMapper();
//            Gson gson = new Gson();
//            TreeMap<Long,Contact> data = gson.fromJson(sData,TreeMap.class);
            Object o = dataToStore.getClass();
            T data = objectMapper.readValue(sData,new TypeReference<>() {});

//            for(Map.Entry<String,Contact> c : data.entrySet()){
//                Contact d=new Contact((Map<String, Object>) c.getValue());
//                this.internalContact.put(d.getContactId(),d);
//            }
            System.out.println("File Successfully Imported from \""+rootPath+"\\contactInformation.txt\" ");
            return data;
        } catch (IOException e){

        }
        return null;
    }

}
