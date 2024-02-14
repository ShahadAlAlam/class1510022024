package org.quizApplication;

import java.util.ArrayList;

public class QuizTopic {
    private String topicName;
    private ArrayList<QuizQuestion> questions;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuizQuestion> questions) {
        this.questions = questions;
    }


    public double getScore(QuizTopic answerSheet) {
        Double correctAnswers = 0D;
        correctAnswers = answerSheet.getQuestions().stream().mapToDouble(QuizQuestion::getResult).sum();
        return (double) correctAnswers / questions.size() * 100;
    }

    public void addQuistion(QuizQuestion q){
        questions.add(q);
    }
}