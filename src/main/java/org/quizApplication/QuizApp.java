package org.quizApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String,QuizTopic> quizTopicMap = new LinkedHashMap<>();

    private static QuizTopic quizTopic = new QuizTopic();
    private static QuizRepo quizRepo = new QuizRepo();
    private static String name="";
    public static void main(String[] args) {
        System.out.println("Welcome to Quiz Application");
        while(name.length()<=0){
            System.out.println("Plese Enter your name");
            try {
                name = buf.readLine();
            }catch(IOException ex){

            }
        }
    }

    private static void showQuiz(){
        quizRepo.importQuizData();
        quizTopicMap = quizRepo.getQuizList();
        for(Map.Entry<String,QuizTopic> e : quizTopicMap.entrySet()){
            System.out.println("Topic Name"+e.getKey());
        }
        System.out.println("Please Select Quiz Topic");
        String quiz ="";
        try {
            while ((quiz= buf.readLine()).trim().length()<=0){
                if(quizTopicMap.get(quiz)==null){
                    System.out.println("Please Select Correct Quiz");
                    quiz="";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        quizTopic = quizTopicMap.get(quiz);
    }

    private static void quizStart(){
        System.out.println("Starting the quiz test");
        Timer timer = new Timer();
        quizTopic.getQuestions().forEach( q->{
            TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            answerQuest(q);
                        }
                    };
            timer.schedule(timerTask,10000);
            }
        );

    }

    private static void answerQuest(QuizQuestion q){
        System.out.println(q.getQuestionText());
        if(q.getQuestionType().equals("T")){
            System.out.println("Enter \"T\" for true or \"F\" for False ");
            String ans = "";
            try {
                while (!((ans= buf.readLine()).trim().contains("T") || (ans= buf.readLine()).trim().contains("F") )){

                }
                q.setUserAnswer(ans);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (q.getQuestionType().equals("M")){
            System.out.println("Enter Answer index for this question ");
            String ans = "";
            try {
                while ((ans= buf.readLine()).trim().length()<=0 ){}
                q.setUserAnswer(ans);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
