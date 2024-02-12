package org.quizApplication;

public class QuizQuestion {

    private String questionText;
    private String[] answerChoices; // Multiple-choice
    private int correctAnswerIndex; // Multiple-choice
    private boolean isTrue; // True/False
    private boolean correctAnswer; // True/False

    private String questionType; // Type of Question M=Multiple Choice, T=True/False

    private Object userAnswer;

    public Object getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Object userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public QuizQuestion(String questionText, String[] answerChoices, boolean isTrue, int correctAnswerIndex, boolean correctAnswer, String questionType) {
        this.questionText = questionText;
        this.answerChoices = answerChoices;
        this.isTrue = isTrue;
        this.correctAnswerIndex = correctAnswerIndex;
        this.correctAnswer = correctAnswer;
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

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public boolean getIsCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getResult() {
        if(this.questionType.equals("M")){
            if(Integer.parseInt(this.userAnswer.toString())==this.correctAnswerIndex){
                return 1;
            } else
                return 0;
        } else if(this.questionType.equals("T")){
            if(Boolean.parseBoolean(this.userAnswer.toString()) == this.correctAnswer){
                return 1;
            } else
                return 0;
        }
        return 0;
    }
}