package org.quizApplication;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuizDeserializer extends StdDeserializer<QuizData> {


    public QuizDeserializer(Class<?> vc) {
        super(vc);
    }

    public QuizDeserializer(JavaType vc) {
        super(vc);
    }


    @Override
    public QuizData deserialize(JsonParser jp, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        QuizData m = new QuizData();
        JsonNode node = jp.getCodec().readTree(jp);
        node.forEach(e-> {
//            Long taskId, String taskName, String taskDetails, Date createdDate, Date targetDate, String status
            String topicName = e.get("topicName").textValue();
            List<QuizQuestion> qq = new ArrayList<>();
            JsonNode qQJsonNode = e.get("quistionInfo");
            qQJsonNode.forEach(qJN->{
//                        String questionText, String[] answerChoices, Long correctAnswerIndex,
//                        String correctAnswer, String questionType
                String questionText = qJN.get("questionText").asText();
                String[] answerChoices = qJN.get("answerChoices").toString().replaceAll("[\\[|\\]|\"]","").split(",");
                Long correctAnswerIndex = qJN.get("correctAnswerIndex").asLong();
                String correctAnswer = qJN.get("correctAnswer").asText();
                String questionType = qJN.get("questionType").asText();
                qq.add(new QuizQuestion(questionText, answerChoices, correctAnswerIndex,correctAnswer, questionType));
            });
            m.add(new QuizTopic(topicName,qq));
        });

        return m;
    }
}
