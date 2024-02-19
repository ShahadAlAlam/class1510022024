package org.contactApplication;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//public class ContactSerializer extends StdSerializer<TreeMap<String,Contact>> {
public class ContactSerializer extends StdSerializer<ContactData> {
    
    public ContactSerializer() {
        this(null);
    }

//    public ContactSerializer(Class<TreeMap<String,Contact>> t) {
    public ContactSerializer(Class<ContactData> t) {
        super(t);
    }

//    public void serialize(
//            TreeMap<String, Contact> value, JsonGenerator jgen, SerializerProvider provider)

    @Override
//    public void serialize(
//            TreeMap<String,Contact> value, JsonGenerator jgen, SerializerProvider provider)
    public void serialize(
            ContactData value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
        jgen.writeStartArray();
        for(Map.Entry<String,Contact> m : value.entrySet()) {
            try {
                jgen.writeStartObject();
                jgen.writeStringField("pkId", m.getKey().toString());
                jgen.writeFieldName("contactInfo");
                jgen.writeStartObject();

                jgen.writeStringField("contactId", m.getValue().getContactId());
                jgen.writeStringField("fName", m.getValue().getfName());
                jgen.writeStringField("lName", m.getValue().getlName());
                jgen.writeStringField("mName", m.getValue().getmName());
                jgen.writeStringField("email", m.getValue().getEmail());
                jgen.writeStringField("phone", m.getValue().getPhone());
                jgen.writeEndObject();
                jgen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        jgen.writeEndArray();
    }

}