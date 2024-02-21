package org.taskManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.contactApplication.Contact;
import org.contactApplication.ContactData;
import org.contactApplication.ContactSerializer;
import org.dataExpImporter.FileManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TaskRepo {
    private  HashMap<String,Tasks> taskList = new HashMap<>();

    public  void addTask(Tasks tasks){
        Calendar cal =new GregorianCalendar();
        tasks.setCreatedDate(cal.getTime());
        Long id;
        if(this.taskList.size()>0)
            id = Collections.max(this.taskList.entrySet(), Map.Entry.comparingByValue()).getValue().getTaskId()+1;
        else
            id = 1L;
        tasks.setTaskId(id);
        this.taskList.put(String.valueOf(id),tasks);
    }

    public  void updateTasks(Tasks tasks){
        this.taskList.put(String.valueOf(tasks.getTaskId()),tasks);
    }

    public  void removeTasks(Long id){
        this.taskList.remove(String.valueOf(id));
    }

    public  HashMap<String,Tasks> getTaskList(){
        return this.taskList;
    }

    public  Tasks getTask(Long id){
        return this.taskList.get(String.valueOf(id));
    }
    public  HashMap<String,Tasks> searchTask(String serchString){
        Map<String,Tasks> result = new HashMap<>();
        result = this.taskList.entrySet().stream().filter(e->e.getValue().getTaskName().toUpperCase().contains(serchString.toUpperCase()))
                .collect(Collectors.toMap (a->a.getKey(),a->(Tasks) a.getValue()));
        this.toString((HashMap<String, Tasks>) result);
        return (HashMap<String, Tasks>) result;
    }

    public void exportTasks(){
        FileManager<TaskData,TaskSerializer,TaskDeserializer> fileManager = new FileManager();
        fileManager.exportInformation(new TaskData(this.taskList),"taskInformation.txt", new TaskSerializer(TaskData.class));
//        //Working
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(TaskData.class, new TaskSerializer());
//        objectMapper.registerModule(module);
//        TaskData conData = new TaskData(this.taskList) ;
//
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
//        objectMapper.setDateFormat(df);
//        try {
//            String rootPath = System.getProperty("user.dir");
//            File obj = new File(rootPath + "/taskInformation.txt");
//            if (obj.createNewFile()) {
//                FileWriter fr = new FileWriter(obj);
////                String jsonArray = objectMapper.writeValueAsString(this.taskList);
//                String jsonArray = objectMapper.writeValueAsString(conData);
//                fr.write(jsonArray);
//                fr.close();
//            } else {
//                FileWriter fr = new FileWriter(rootPath + "/taskInformation.txt");
////                String jsonArray = objectMapper.writeValueAsString(this.taskList);
//                String jsonArray = objectMapper.writeValueAsString(conData);
//                fr.write(jsonArray);
//                fr.close();
//            }
//            System.out.println("File Exported to \"" + rootPath + "\" directory");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void importInformation() {
        try {

            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );

            File obj = new File(rootPath+"/taskInformation.txt" );
            InputStream in = new FileInputStream(obj);

            String sData = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            FileManager<TaskData,TaskSerializer,TaskDeserializer> fileManager = new FileManager();
            this.taskList = fileManager.importInformation("taskInformation.txt", new TaskDeserializer(TaskData.class),TaskData.class);


//            ObjectMapper objectMapper = new ObjectMapper();
//            SimpleModule module = new SimpleModule();
//            module.addDeserializer(TaskData.class,new TaskDeserializer(TaskData.class));
//            objectMapper.registerModule(module);
//            HashMap<String, Tasks> data = objectMapper.readValue(sData,TaskData.class);
//            this.taskList = data;
//            System.out.println("File Successfully Imported from \""+rootPath+"\\taskInformation.txt\" ");
        } catch (Exception e) {
            System.out.println("File Failed to Import due to "+e.getMessage());
        }
//        //Working
//        try {
//            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath );
//
//            File obj = new File(rootPath+"/taskInformation.txt" );
//            InputStream in = new FileInputStream(obj);
//
//            String sData = new BufferedReader(
//                    new InputStreamReader(in, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.joining("\n"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
//            objectMapper.setDateFormat(df);
//
//
//            LinkedHashMap<String, Tasks> data = objectMapper.readValue(sData,LinkedHashMap.class);
//
//            this.taskList.clear();
//
//            for(Map.Entry<String,Tasks> c : data.entrySet()){
//                Object o =  c.getValue();
//                LinkedHashMap<String,Object> ts = (LinkedHashMap<String, Object>) o;
////                Long taskId, String taskName, String taskDetails, Date createdDate, Date targetDate, String status
//
//
//                Tasks d=new Tasks(
//                        Long.parseLong(String.valueOf(ts.get("taskId"))),
//                        String.valueOf(ts.get("taskName")),
//                        String.valueOf(ts.get("taskDetails")),
//                        df.parse( String.valueOf( ts.get("createdDate"))),
//                        df.parse( String.valueOf(ts.get("targetDate"))),
//                        String.valueOf(ts.get("status"))
//                );
//                this.taskList.put(String.valueOf( d.getTaskId()),d);
//            }
//            System.out.println("File Successfully Imported from \""+rootPath+"\\taskInformation.txt\" ");
//        } catch (IOException e){
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public String toString() {
        return
                "Task List= " + taskList.values().stream().map(e -> e.toString()).reduce("\n", String::concat);
    }

    public void toString(HashMap<String, Tasks> cons) {
        System.out.println("Searched Task List = " + cons.values().stream().map(e -> e.toString()).reduce("\n", String::concat));
    }
}
