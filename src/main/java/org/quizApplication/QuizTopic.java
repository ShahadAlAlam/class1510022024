package org.quizApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class QuizTopic {
    private String topicName;
    private List<QuizQuestion> questions = new ArrayList<>();

    public QuizTopic(String topicName, List<QuizQuestion> questions) {
        this.topicName = topicName;
        this.questions = questions;
    }

    public QuizTopic() {
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {

        this.topicName = topicName;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }


    public double getScore() {
        Double correctAnswers = 0D;
        correctAnswers = this.getQuestions().stream().mapToDouble(QuizQuestion::getResult).sum();
        return (double) correctAnswers / questions.size() * 100;
    }

    public void addQuistion(QuizQuestion q) {
        questions.add(q);
    }

    @Override
    public String toString() {
        return "QuizTopic{" +
                "topicName='" + topicName + '\'' +
                ", questions=" + questions.toString() +
                '}';
    }
}