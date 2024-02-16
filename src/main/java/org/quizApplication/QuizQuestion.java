package org.quizApplication;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuizQuestion {
    //if Method does not Handle with Try catch JACSON Export Error Occures getResult Method
    //Null value of any Variable does the same like userAnswer variable i Had to initialize it

    public QuizQuestion() {

    }

    private String questionText = "";
    private String[] answerChoices = {"1", "2", "3", "4"}; // Multiple-choice
    private Long correctAnswerIndex = 5L; // Multiple-choice
    private String isTrue = "F"; // True/False
    private String correctAnswer = "F"; // True/False
    private String questionType; // Type of Question M=Multiple Choice, T=True/False
    private String userAnswer = "";

    public QuizQuestion(String questionText, String[] answerChoices, Long correctAnswerIndex, String correctAnswer, String questionType) {
        this.questionText = questionText;
        this.answerChoices = answerChoices;
//        this.isTrue = isTrue;
        this.correctAnswerIndex = correctAnswerIndex;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    public Object getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    public String isTrue() {
        return isTrue;
    }

    public void setTrue(String aTrue) {
        isTrue = aTrue.toUpperCase();
    }

    public Long getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(Long correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getIsCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer.toUpperCase();
    }

    public Long getResult() {
        //if Method does not Handle with Try catch JACSON Export Error Occures
        try {
            if (this.questionType.equals("M")) {
                if (Integer.parseInt(this.userAnswer) == this.correctAnswerIndex) {
                    return 1L;
                } else
                    return 0L;
            } else if (this.questionType.equals("T")) {
                if (this.userAnswer.toUpperCase().equals(this.correctAnswer.toUpperCase())) {
                    return 1L;
                } else
                    return 0L;
            }
            return 0L;
        } catch (Exception E) {
            return 0L;
        }
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "questionText='" + questionText + '\'' +
                ", answerChoices=" + Arrays.toString(answerChoices) +
                ", correctAnswerIndex=" + correctAnswerIndex +
                ", isTrue=" + isTrue +
                ", correctAnswer=" + correctAnswer +
                ", questionType='" + questionType + '\'' +
                '}';
    }
}