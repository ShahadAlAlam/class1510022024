package org.quizApplication;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.contactApplication.Contact;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class QuizRepo {
    private static Map<String,QuizTopic> quizList = new LinkedHashMap<>();
    public static Map<String, QuizTopic> getQuizList() {
        return quizList;
    }

    public void addQuizToList(QuizTopic qt){
        quizList.put(qt.getTopicName(),qt);
    }
    
    public void  importQuizData(){
        try {
            String rootPath = System.getProperty("user.dir");
            System.out.println(rootPath );
            File obj = new File(rootPath+"/quizInformation.txt" );
            InputStream in = new FileInputStream(obj);
            String sData = new BufferedReader( new InputStreamReader(in, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,QuizTopic> data = objectMapper.readValue(sData,Map.class);
            this.quizList.clear();
            for(Map.Entry<String,QuizTopic> c : data.entrySet()){
                QuizTopic d= c.getValue();
                this.quizList.put(c.getKey(),d);
            }
            System.out.println("File Successfully Imported from \""+rootPath+"\\quizInformation.txt\" ");
        } catch (IOException e){

        }
    }
}
