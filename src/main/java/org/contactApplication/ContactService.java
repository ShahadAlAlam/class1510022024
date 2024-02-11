package org.contactApplication;

import java.util.Scanner;
import java.util.TreeMap;

public class ContactService {
    private static ContactRepo contactRepo = new ContactRepo();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Contact Manager Application");
        // Main menu loop with Scanner for user input
        while (true) {
            printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1: // Add contact
                    addContact(sc);
                    break;
                case 2: // View all contacts
                    seeAllContact();
                    break;
                case 3: // Search contact
                    searchContact(sc);
                    break;
                case 4: // Remove contact
                    // ...
                    break;
                // ... other options
                case 0: // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static void printMenu() {
        System.out.println("What do you want?");
        System.out.println("1: Add Contact");
        System.out.println("2: View All Contacts");
        System.out.println("3: Search Contact");
        System.out.println("4: Remove Contact");
        System.out.println("0: Exit Application");
    }

    private static void addContact(Scanner sc) {
        System.out.println("Enter First name?");
        String fname = sc.next();
        sc.nextLine();
        System.out.println("Enter Middle name?");
        String mname = sc.next();
        sc.nextLine();
        System.out.println("Enter Last name?");
        String lname = sc.next();
        sc.nextLine();
        System.out.println("Enter email?");
        String email = sc.next();
        sc.nextLine();
        System.out.println("Enter Phone Number?");
        String phone = sc.next();
        sc.nextLine();
        boolean b = contactRepo.addContact( fname, lname, mname, email, phone);
        if(b){
            System.out.println("Contact Added to Adderss Book");
        }
    }

    private static void seeAllContact() {
        contactRepo.seeAllContact();
    }

    private static void searchContact(Scanner sc){
        System.out.println("Enter First Name");
        String fname = sc.next();
        TreeMap<Long,Contact> cons = contactRepo.searchContact(fname);
        System.out.println("Found this/these contact informations");
        System.out.println(cons.toString());
        if(cons.size()>0){
            System.out.println("Do you want to Update Contact? 1: update, 2: remove 0: no");
            int a = sc.nextInt();
            switch (a){
                case 1: {
                    boolean updateCons = true;
                    while(updateCons){
                        System.out.println("Select ID for below contacts");
                        cons.values().forEach(e->{
                            System.out.println(e.toString());
                        });
                        Long id = sc.nextLong();
                        if(cons.get(id)!=null){
                            Contact con = cons.get(id);
                            System.out.println("Enter First name?");
                            String fnameVal = sc.nextLine();
                            if(!fname.isEmpty()){
                                con.setfName(fnameVal);
                            }
                            System.out.println("Enter Middle name?");
                            String mname = sc.nextLine();
                            if(!mname.isEmpty()){
                                con.setmName(mname);
                            }
                            System.out.println("Enter Last name?");
                            String lname = sc.nextLine();
                            if(!lname.isEmpty()){
                                con.setlName(lname);
                            }
                            System.out.println("Enter email?");
                            String email = sc.nextLine();
                            if(!email.isEmpty()){
                                con.setEmail(email);
                            }
                            System.out.println("Enter Phone Number?");
                            String phone = sc.nextLine();
                            if(!phone.isEmpty()){
                                con.setPhone(phone);
                            }
                            contactRepo.updateContact(con);
                            updateCons=false;
                        }
                    }
                    break;
                }
                case 2:{
                    boolean updateCons = true;
                    while(updateCons){
                        System.out.println("Select ID for below contacts");
                        cons.values().forEach(e->{
                            System.out.println(e.toString());
                        });
                        Long id = sc.nextLong();
                        if(cons.get(id)!=null){
                            Contact con = cons.get(id);
                            contactRepo.removeContact(con);
                            updateCons=false;
                        }
                    }
                    break;
                }
                default:
                    return;
            }
        }
    }
}
