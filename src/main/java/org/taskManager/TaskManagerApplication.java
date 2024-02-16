package org.taskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskManagerApplication {
    private static TasksService tasksService = new TasksService();
    private static BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        System.out.println("Welcome to Task Management CLI Application");
        while (true) {

            tasksService.printMenu();
            try {
                int choice =Integer.parseInt(buf.readLine()) ;
                switch (choice) {
                    case 1: // Add contact
                        tasksService.addTask();
                        break;
                    case 2: // View all contacts
                        tasksService.seeAllTasks();
                        break;
                    case 3: // Search contact
                        tasksService.searchTask();
                        break;
                    case 4: // Search contact
                        tasksService.exportInfo();
                        break;
                    case 5: // Search contact
                        tasksService.importInformation();
                        break;
                    // ... other options
                    case 0: // Exit
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch(IOException eIOE){

            }
        }
    }
}
