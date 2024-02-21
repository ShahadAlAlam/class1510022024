package org.dataExpImporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileManager <T,S extends StdSerializer<T>,D extends StdDeserializer<T>> {
    public void exportInformation(T dataToStore, String fileName, S std){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer((S)std);
        objectMapper.registerModule(module);
        try {
            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath );
            File obj = new File(rootPath+"/"+fileName );
            if (obj.createNewFile()) {
                FileWriter fr = new FileWriter(obj);
                String jsonArray = objectMapper.writeValueAsString(dataToStore);
                fr.write(jsonArray);
                fr.close();
            } else {
                FileWriter fr = new FileWriter(rootPath+"/"+fileName);
                String jsonArray = objectMapper.writeValueAsString(dataToStore);
                fr.write(jsonArray);
                fr.close();
            }
            System.out.println("File Exported to \""+rootPath+"\" directory");
        } catch (IOException e){

        }
    }

    public T importInformation(String fileName, D dstd, Class<T> type) {
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
            SimpleModule module = new SimpleModule();
            module.addDeserializer( type, (D) dstd);
            objectMapper.registerModule(module);
            T data = objectMapper.readValue(sData,type);

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
