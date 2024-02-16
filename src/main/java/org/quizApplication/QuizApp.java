package org.quizApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class QuizApp {
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    private static List<QuizTopic> quizTopicMap = new ArrayList<>();
    private static QuizTopic quizTopic = new QuizTopic();
    private static QuizRepo quizRepo = new QuizRepo();
    private static String name = "";

    private static Timer timer = new Timer();
    private static TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

        }
    };

    public static void main(String[] args) {
        System.out.println("Welcome to Quiz Application");

        while (name.length() <= 0) {
            System.out.println("Plese Enter your name");
            try {
                name = buf.readLine();
            } catch (IOException ex) {
            }
        }
        showOprions();

    }

    private static void showOprions() {
        boolean inpLoop = true;

        while (inpLoop) {
            int inp = 10;
            System.out.println("Select one of The Options Below");
            System.out.println("1. Add Quiz");
            System.out.println("2. Show Quizes");
            System.out.println("3. Export Quizes");
            System.out.println("4. Import Quizes");
            System.out.println("5. Participate into Quiz");
            System.out.println("0. Exit......");
            try {
                while (inpLoop) {
                    inp = Integer.parseInt(buf.readLine());
                    if (inp >= 0 || inp < 6) {
                        inpLoop = false;
                    }
                }
                inpLoop = true;
                switch (inp) {
                    case 1:
                        addQuiz();
                        break;
                    case 2:
                        showQuizList();
                        break;
                    case 3:
                        exportQuest();
                        break;
                    case 4:
                        importQuest();
                        break;
                    case 5:
                        showQuiz();
                        break;
                    case 0:

                        System.out.println("Exiting Application...");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addQuiz() {
        QuizTopic q = new QuizTopic();
        System.out.println("Add Topic name");
        String qTopicName = "";
        try {
            while ((qTopicName = buf.readLine()).trim().length() <= 0) {
            }
            q.setTopicName(qTopicName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean addQuistion = true;
        while (addQuistion) {
            System.out.println("To add quistion press 1 else 0");
            try {
                int inp = Integer.parseInt(buf.readLine());
                if (inp == 1) {
//                private String questionText;
//                private String[] answerChoices; // Multiple-choice
//                private int correctAnswerIndex; // Multiple-choice
//                private boolean isTrue; // True/False
//                private boolean correctAnswer; // True/False
//                private String questionType; // Type of Question M=Multiple Choice, T=True/False

                    System.out.println("Add quistion Type , M for Multiple Choice T for True False");
                    String quistionType = "";
                    while ((quistionType.isEmpty()) ||
                            (quistionType.trim().toUpperCase().replaceAll("[T|M]", "").length() > 0)) {
                        quistionType = buf.readLine();
                    }
                    System.out.println("Add quistion text");

                    String quistionText = "";
                    while ((quistionText.trim().isEmpty())) {
                        quistionText = buf.readLine();
                    }
                    String[] answerChoices = {" ", " ", " ", " "};
                    Long correctAnswerIndex = 5L;
                    String isTrue = "F";
                    if (quistionType.toUpperCase().equals("M")) {
                        for (int i = 0; i < 4; i++) {
                            System.out.println("Multiple Choice no: " + i);
                            answerChoices[i] = buf.readLine();
                        }
                        System.out.println("Correct Answer index");
                        correctAnswerIndex = Long.parseLong(buf.readLine());
                    } else {
                        System.out.println("\"T\" for true else false ");
                        isTrue = buf.readLine().toUpperCase().equals("T") == true ? "T" : "F";
                    }
                    q.addQuistion(new QuizQuestion(quistionText, answerChoices, correctAnswerIndex, isTrue, quistionType));
                } else {
                    addQuistion = false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!qTopicName.isEmpty() && qTopicName.trim().length() > 0) {
            quizRepo.addQuizToList(q);
        }
    }

    private static void showQuizList() {
//        quizRepo.importQuizData();
        quizTopicMap = quizRepo.getQuizList();

        for (QuizTopic e : quizTopicMap) {
            System.out.println("Topic Name = " + e.getTopicName());
        }
    }

    private static void showQuiz() {
//        quizRepo.importQuizData();
        quizTopicMap = quizRepo.getQuizList();

        for (QuizTopic e : quizTopicMap) {
            System.out.println("Topic Name = " + e.getTopicName());
        }
        System.out.println("Please Select Quiz Topic");
        String quiz = "";
        try {
            while ((quiz = buf.readLine()).trim().length() <= 0) {
                String finalQuiz = quiz;
                if (quizTopicMap.stream().filter(e -> e.getTopicName().toUpperCase().equals(finalQuiz.toUpperCase())
                ) == null) {
                    System.out.println("Please Select Correct Quiz");
                    quiz = "";
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String finalQuiz1 = quiz;
        quizTopic = quizTopicMap.stream().filter(e->
                e.getTopicName().toUpperCase().equals(finalQuiz1.toUpperCase()))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    if (list.size() != 1) {
                                        throw new IllegalStateException();
                                    }
                                    return list.get(0);
                                }
                        )
                );
                quizStart();
    }

    private static void quizStart() {
        System.out.println("Starting the quiz test");
//        timer = new Timer();
        quizTopic.getQuestions().forEach(q -> {

                    answerQuest(q);
//                    timerTask = new TimerTask() {
//                        @Override
//                        public void run() {
//                            answerQuest(q);
//                        }
//                    };
//                timer.schedule(timerTask, 1000);
                }
        );
//        timer.cancel();
        System.out.println(name+" you got "+quizTopic.getScore()+" in Quiz "+quizTopic.getTopicName());
//        quizTopic.getScore();
    }

    private static void answerQuest(QuizQuestion q) {
        System.out.println(q.getQuestionText());
        if (q.getQuestionType().equals("T")) {
            System.out.println("Enter \"T\" for True or \"F\" for False!!! ");
            String ans = "";

            try {
                while (!((ans = buf.readLine()).trim().contains("T") || (ans = buf.readLine()).trim().contains("F"))) {
                }
                q.setUserAnswer(ans);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (q.getQuestionType().equals("M")) {
            int ind = 0;
            for(String s:q.getAnswerChoices()){
                System.out.println(ind+". "+s);
                ind++;
            }
//            Arrays.stream(q.getAnswerChoices()).sequential().forEach(
//                    e->System.out.println(e)
//            );
            System.out.println("Enter Answer index for this question ");
            String ans = "";
            try {
                while ((ans = buf.readLine()).trim().length() <= 0) {
                }
                q.setUserAnswer(ans);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void exportQuest() {
        quizRepo.exportQuizData();
    }


    private static void importQuest() {
        quizRepo.importQuizData();
    }
}
