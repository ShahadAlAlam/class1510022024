package org.taskManager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class TaskSerializer extends StdSerializer<TaskData> {

//    public TaskSerializer() {
//        this(null);
//    }
    public TaskSerializer(Class<TaskData> t) {
        super(t);
    }


    public TaskSerializer(JavaType type){
        super(type);
    }

    @Override
    public void serialize(
            TaskData value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        jgen.writeStartArray();
        for(Map.Entry<String, Tasks> m : value.entrySet()) {
            try {
//                Long taskId, String taskName, String taskDetails, Date createdDate, Date targetDate, String status
                jgen.writeStartObject();
                jgen.writeStringField("pkId", m.getKey().toString());
                jgen.writeFieldName("taskInfo");
                jgen.writeStartObject();
                jgen.writeNumberField("taskId", m.getValue().getTaskId());
                jgen.writeStringField("taskName", m.getValue().getTaskName());
                jgen.writeStringField("taskDetails", m.getValue().getTaskDetails());
                jgen.writeStringField("createdDate",df.format( m.getValue().getCreatedDate()));
                jgen.writeStringField("targetDate", df.format(m.getValue().getTargetDate()));
                jgen.writeStringField("status", m.getValue().getStatus());
                jgen.writeEndObject();
                jgen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        jgen.writeEndArray();
    }

}