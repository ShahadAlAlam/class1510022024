package org.quizApplication;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;

public class QuizSerializer extends StdSerializer<QuizData> {

    public QuizSerializer(Class<QuizData> t) {
        super(t);
    }


    public QuizSerializer(JavaType type){
        super(type);
    }

    @Override
    public void serialize(
            QuizData value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {

        Iterator<QuizTopic> itr = value.iterator();
        jgen.writeStartArray();
        while(itr.hasNext()) {
            QuizTopic qt = itr.next();
            try {
                jgen.writeStartObject();
                jgen.writeStringField("topicName", qt.getTopicName());
                jgen.writeFieldName("quistionInfo");
                jgen.writeStartArray();
                qt.getQuestions().forEach(q-> {
                    try {
                        jgen.writeStartObject();
//                        String questionText, String[] answerChoices, Long correctAnswerIndex, String correctAnswer, String questionType
                        jgen.writeStringField("questionText", q.getQuestionText());
                        jgen.writeFieldName("answerChoices");
                        jgen.writeStartArray();
                        Arrays.stream(q.getAnswerChoices()).forEach(s->{
                            try {
                                jgen.writeString(s);
                            } catch (IOException e) {
                            }
                        });
                        jgen.writeEndArray();
                        jgen.writeNumberField("correctAnswerIndex", q.getCorrectAnswerIndex());
                        jgen.writeStringField("correctAnswer", q.getIsCorrectAnswer());
                        jgen.writeStringField("questionType",q.getQuestionType());
                        jgen.writeEndObject();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
                jgen.writeEndArray();
                jgen.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        jgen.writeEndArray();
    }

}