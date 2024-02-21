package org.quizApplication;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.dataExpImporter.FileManager;
import org.taskManager.TaskData;
import org.taskManager.TaskDeserializer;
import org.taskManager.TaskSerializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class QuizRepo {
//    private static List<QuizTopic> quizList = new ArrayList<>();
    private static QuizData quizList = new QuizData();

    public List<QuizTopic> getQuizList() {
        return this.quizList;
    }

    public void addQuizToList(QuizTopic qt) {
        this.quizList.add(qt);
    }

    public void importQuizData() {
        try {
            FileManager<QuizData,QuizSerializer,QuizDeserializer> fileManager = new FileManager();
            this.quizList = fileManager.importInformation("quizInformation.txt", new QuizDeserializer(QuizData.class),QuizData.class);


//            //WORKING
//            String rootPath = System.getProperty("user.dir");
//            System.out.println(rootPath);
//            File obj = new File(rootPath + "/quizInformation.txt");
//            InputStream in = new FileInputStream(obj);
//            String sData = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))
//                    .lines()
//                    .collect(Collectors.joining("\n"));
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<QuizTopic> data = objectMapper.readValue(sData, List.class);
//            this.quizList.clear();
//
//            for (int iData = 0; iData < data.size(); iData++) {
//                Map<String, QuizTopic> qt = (Map) data.get(iData);
//                QuizTopic quizTopic = new QuizTopic();
//
////              String topicName = String.valueOf( qt.get("topicName"));
//                quizTopic.setTopicName(String.valueOf(qt.get("topicName")));
//                List<LinkedHashMap<String, QuizQuestion>> questions = (List<LinkedHashMap<String, QuizQuestion>>) qt.get("questions");
//                questions.forEach(e -> {
//                    Object o = e.get("answerChoices");
//                    ArrayList<String> ara = (ArrayList<String>) o;
////                  String questionText = String.valueOf(e.get("questionText"));
////                  String[] answerChoices = ara.toArray(new String[0]);
////                  Long correctAnswerIndex = Long.parseLong(String.valueOf( e.get("correctAnswerIndex")));
////                  String correctAnswer = String.valueOf(e.get("correctAnswer"));
////                  String questionType = String.valueOf(e.get("questionType"));
//                    QuizQuestion inQ = new QuizQuestion(
//                            String.valueOf(e.get("questionText")),
//                            ara.toArray(new String[0]),
//                            Long.parseLong(String.valueOf(e.get("correctAnswerIndex"))),
//                            String.valueOf(e.get("isCorrectAnswer")),
//                            String.valueOf(e.get("questionType"))
//                    );
//                    quizTopic.addQuistion(inQ);
//                });
//                this.quizList.add(quizTopic);
//            }
//            System.out.println("File Successfully Imported from \"" + rootPath + "\\quizInformation.txt\" ");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void exportQuizData() {
        FileManager<QuizData, QuizSerializer, QuizDeserializer> fileManager = new FileManager();
//        fileManager.exportInformation((QuizData) new QuizData(this.quizList).getQt(),"quizInformation.txt", new QuizSerializer(QuizData.class));
        fileManager.exportInformation(this.quizList,"quizInformation.txt", new QuizSerializer(QuizData.class));


//        Working
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String rootPath = System.getProperty("user.dir");
//            File obj = new File(rootPath + "/quizInformation.txt");
////            Gson gson = new Gson();
//            if (obj.createNewFile()) {
//                FileWriter fr = new FileWriter(obj);
//                String jsonArray = objectMapper.writeValueAsString(this.quizList);
////                fr.write(gson.toJson(this.internalContact) );
//                fr.write(jsonArray);
//                fr.close();
//            } else {
//                FileWriter fr = new FileWriter(rootPath + "/quizInformation.txt");
//                String jsonArray = objectMapper.writeValueAsString(this.quizList);
//                fr.write(jsonArray);
//                fr.close();
//            }
//            System.out.println("File Exported to \"" + rootPath + "\" directory");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
