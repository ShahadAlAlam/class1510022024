package org.contactApplication;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
//import java.util.TreeMap;

//public class ContactDeserializer extends StdDeserializer<TreeMap<String,Contact>> {
public class ContactDeserializer extends StdDeserializer<ContactData> {

//    public ContactDeserializer() {
//        this(null);
//    }

    public ContactDeserializer(Class<?> vc) {
        super(vc);
    }

    public ContactDeserializer(JavaType vc) {
        super(vc);
    }


    @Override
//    public TreeMap<String,Contact> deserialize(JsonParser jp, DeserializationContext deserializationContext)
    public ContactData deserialize(JsonParser jp, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ContactData m = new ContactData();
        JsonNode node = jp.getCodec().readTree(jp);
        node.forEach(e->{
//        "contactId":"1","fName":"a","lName":"a","mName":"a","email":"a","phone":"a"
            String pkId = e.get("pkId").textValue();
            String contactId = e.get("contactInfo").get("contactId").textValue();
            String fName = e.get("contactInfo").get("fName").textValue();
            String lName = e.get("contactInfo").get("lName").textValue();
            String mName = e.get("contactInfo").get("mName").textValue();
            String email = e.get("contactInfo").get("email").textValue();
            String phone = e.get("contactInfo").get("phone").textValue();
            m.put(pkId,new Contact(contactId, fName, lName, mName, email, phone));

        });

        return m;
    }
}
