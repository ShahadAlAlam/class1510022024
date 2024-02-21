package org.quizApplication;

import java.util.ArrayList;
import java.util.List;

public class QuizData extends ArrayList<QuizTopic> {
    public ArrayList<QuizTopic> getQt() {
        return qt;
    }

    public void setQt(ArrayList<QuizTopic> qt) {
        this.qt = qt;
    }

    private ArrayList<QuizTopic> qt;
    QuizData(){}

    QuizData(ArrayList<QuizTopic> qt){ this.qt = qt;}
    QuizData(List<QuizTopic> qt){
        this.qt =(ArrayList<QuizTopic>) qt;
    }

//    QuizData(ArrayList<QuizTopic> qt){
//    }
//
//    QuizData(ArrayList<QuizTopic> qt){
//        this.addAll(qt);
//    }

}
