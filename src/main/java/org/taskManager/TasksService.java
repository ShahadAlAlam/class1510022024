package org.taskManager;

import org.contactApplication.Contact;
import org.contactApplication.ContactRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class TasksService {
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    private static TaskRepo taskRepo = new TaskRepo();

    protected static void printMenu() {
            System.out.println("What do you want?");
            System.out.println("1: Add Task");
            System.out.println("2: View All Tasks");
            System.out.println("3: Search Tasks");
            System.out.println("4: Export Information");
            System.out.println("5: Import Information");
            System.out.println("0: Exit Application");
        }

    protected static void addTask() {
        try {
//            this.taskName = taskName;
//            this.taskDetails = taskDetails;
//            this.createdDate = createdDate;
//            this.targetDate = targetDate;
//            this.status = status;
            System.out.println("Enter Task Name?");
            String taskName = "";
            while ((taskName.isEmpty()) ||
                    (taskName.trim().length() < 5)) {
                taskName = buf.readLine();
            }
            System.out.println("Enter Task Details?");
            String taskDetails = "";
            while ((taskDetails.isEmpty()) ||
                    (taskDetails.trim().length() <10)) {
                taskDetails = buf.readLine();
                if (taskDetails.length() < 10) {
                    System.out.println("Minimum 10 charecers");
                    taskDetails = "";
                }
            }
            System.out.println("Enter Target Date?");
            String targetDate = "";
            Date chekDate = null;
            while ((targetDate.isEmpty()) ||
                    (targetDate.trim().length() < 10)) {
                targetDate = buf.readLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (targetDate.length() != 10) {
                    System.out.println("Date Format not OK, Format is \"dd-MM-yyyy\"");
                    targetDate = "";
                } else {
                    try {
                        chekDate = sdf.parse(targetDate);
                    } catch (Exception e) {
                        targetDate = "";
                    }
                }
            }
            System.out.println("Enter Status? \"O\" open \"C\" for Closed");
            String status = "";
            while ((status.isEmpty()) ||
                    (status.toUpperCase().trim().replaceAll("[^O|^C]", "").length() != 1)) {
                status = buf.readLine();

            }
            taskRepo.addTask(new Tasks(taskName, taskDetails, chekDate, status));

        } catch (Exception e){

        }
    }

    protected static void seeAllTasks() {
       HashMap<String,Tasks> d = taskRepo.getTaskList();
        System.out.println(taskRepo.toString());
//        Scanner sc = new Scanner(System.in);
        updateTasks(d);
    }

    protected static void searchTask(){
        System.out.println("Enter First Name");
        try {
            String fname = buf.readLine();
            HashMap<String, Tasks> cons = taskRepo.searchTask(fname);
            updateTasks(cons);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected static void updateTasks(HashMap<String, Tasks> cons) {
        if(cons.size()>0){
            System.out.println("Do you want to Update Task? 1: update, 2: remove 0: no");
            try {
                int a =Integer.parseInt(String.valueOf(buf.readLine())) ;
                switch (a) {
                    case 1: {
                        boolean updateCons = true;
                        while (updateCons) {
                            taskRepo.toString(cons);
                            System.out.println("Enter Id to Update:");
                            String id = buf.readLine();
                            if (cons.get(id) != null) {
                                try {
                                    Tasks con = cons.get(String.valueOf(id));
                                    System.out.println("Previous Task name is " + con.getTaskName() + " , Enter Task name or Leave Empty?");
                                    String taskName = "";
                                    while ((taskName.isEmpty()) ||
                                            (taskName.trim().length() <= 10)) {
                                        taskName = buf.readLine();
                                        if (taskName.length() < 10) {
                                            taskName = con.getTaskName();
                                        }
                                    }
                                    con.setTaskName(taskName);
                                    System.out.println("Previous Task Details is " + con.getTaskDetails() + " , Enter Task Details?");
                                    String taskDetails = "";
                                    while ((taskDetails.isEmpty()) ||
                                            (taskDetails.trim().length() <10)) {
                                        taskDetails = buf.readLine();
                                        if (taskDetails.length() < 10) {
                                            System.out.println("Minimum 10 charecers");
                                            taskDetails = con.getTaskDetails();
                                        }
                                    }
                                    con.setTaskDetails(taskDetails);

                                    System.out.println("Previous Target date was "+con.getTargetDate().toString()+", Enter Target Date?");
                                    String targetDate = "";
                                    Date chekDate = null;
                                    while ((targetDate.isEmpty()) ||
                                            (targetDate.trim().length() != 10)) {
                                        targetDate = buf.readLine();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                        if (targetDate.length() != 10) {
                                            System.out.println("Date Format not OK, Format is \"dd-MM-yyyy\"");
                                            targetDate = "..........";
                                            chekDate = con.getTargetDate();
                                        } else {
                                            try {
                                                chekDate = sdf.parse(targetDate);
                                            } catch (Exception e) {
                                                targetDate = "";
                                            }
                                        }
                                    }
                                    con.setTargetDate(chekDate);

                                    System.out.println("Previous statuc was "+(con.getStatus().toUpperCase().equals("C")==true?"\"Closed\"":"\"Open\"")+" Enter Status? \"O\" open \"C\" for Closed");
                                    String status = "";
                                    while ((status.isEmpty()) ||
                                            (status.toUpperCase().trim().replaceAll("[^O|^C]", "").length() != 1)) {
                                        status = buf.readLine();
                                        if(status.toUpperCase().replaceAll("[^C|^O]","").length()>0){

                                        } else
                                            status = con.getStatus();
                                    }
                                    con.setStatus(status);

                                    taskRepo.updateTasks(con);
                                } catch (IOException ioE) {

                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                updateCons = false;
                            }
                        }
                        break;
                    }
                    case 2: {
                        boolean updateCons = true;
                        while (updateCons) {
                            System.out.println("Select ID for below contacts");
                            cons.values().forEach(e -> {
                                System.out.println(e.toString());
                            });
                            String id = buf.readLine();
                            if (cons.get(String.valueOf(id)) != null) {
                                try {
                                    Tasks con = cons.get(String.valueOf(id));
                                    taskRepo.removeTasks(con.getTaskId());
                                } catch (Exception e) {

                                } finally {
                                    updateCons = false;
                                }
                            }
                        }
                        break;
                    }
                    default:
                        return;
                }
            } catch(IOException eIOE){
                System.out.println(eIOE.getMessage());
            }
        }

    }

    protected static void exportInfo(){
        taskRepo.exportTasks();
    }

    protected static void importInformation(){
        taskRepo.importInformation();
    }
}
