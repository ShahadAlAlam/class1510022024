package org.taskManager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//public class ContactDeserializer extends StdDeserializer<TreeMap<String,Contact>> {
public class TaskDeserializer extends StdDeserializer<TaskData> {

//    public TaskDeserializer() {
//        this(null);
//    }

    public TaskDeserializer(Class<?> vc) {
        super(vc);
    }

    public TaskDeserializer(JavaType vc) {
        super(vc);
    }


    @Override
    public TaskData deserialize(JsonParser jp, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        TaskData m = new TaskData();
        JsonNode node = jp.getCodec().readTree(jp);
        node.forEach(e-> {
//                Long taskId, String taskName, String taskDetails, Date createdDate, Date targetDate, String status
            String pkId = e.get("pkId").textValue();
            Long taskId = e.get("taskInfo").get("taskId").asLong();
            String taskName = e.get("taskInfo").get("taskName").textValue();
            String taskDetails = e.get("taskInfo").get("taskDetails").textValue();
            Date createdDate = null;
            try {
                createdDate = df.parse(e.get("taskInfo").get("createdDate").textValue());
            } catch (ParseException ex) {
                ;
            }
            Date targetDate;
            try {
                targetDate = df.parse(e.get("taskInfo").get("targetDate").textValue());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            String status = e.get("taskInfo").get("status").textValue();
            m.put(pkId, new Tasks(taskId, taskName, taskDetails, createdDate, targetDate, status));

        });

        return m;
    }
}
