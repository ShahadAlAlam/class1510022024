package org.contactApplication;

import java.util.Map;
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
                case 4: // Search contact
                    exportInfo();
                    break;
                case 5: // Search contact
                    importInformation();
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
        System.out.println("4: Export Information");
        System.out.println("5: Import Information");
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
        Scanner sc = new Scanner(System.in);
        updateContact(sc,contactRepo.getInternalContact());
    }

    private static void searchContact(Scanner sc){
        System.out.println("Enter First Name");
        String fname = sc.next();
        TreeMap<String,Contact> cons = contactRepo.searchContact(fname);
        updateContact(sc,cons);
    }

    private static void updateContact(Scanner sc,TreeMap<String, Contact> cons) {
        if(cons.size()>0){
            System.out.println("Do you want to Update Contact? 1: update, 2: remove 0: no");
            int a = sc.nextInt();
            switch (a){
                case 1: {
                    boolean updateCons = true;
                    while(updateCons){
                        System.out.println(contactRepo.toString(cons));
                        System.out.println("Enter Id to Update:");
                        Long id = sc.nextLong();
                        if(cons.get(String.valueOf(id))!=null){
                            Contact con = cons.get(String.valueOf(id));
                            System.out.println("Previous First name is "+con.getfName()+" , Enter First name or Leave Empty?");
                            String fnameVal = sc.next();
                            sc.nextLine();
                            if(!fnameVal.isEmpty()){
                                con.setfName(fnameVal);
                            }
                            System.out.println("Previous Middle name is "+con.getmName()+" , Enter Middle name?");
                            String mname = sc.next();
                            sc.nextLine();
                            if(!mname.isEmpty()){
                                con.setmName(mname);
                            }
                            System.out.println("Previous Last name is "+con.getlName()+" , Enter Last name?");
                            String lname = sc.next();
                            sc.nextLine();
                            if(!lname.isEmpty()){
                                con.setlName(lname);
                            }
                            System.out.println("Previous email is "+con.getEmail()+" , Enter email?");
                            String email = sc.next();
                            sc.nextLine();
                            if(!email.isEmpty()){
                                con.setEmail(email);
                            }
                            System.out.println("Previous Phone Number is "+con.getPhone()+" , Enter Phone Number?");
                            String phone = sc.next();
                            sc.nextLine();
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
                        if(cons.get(String.valueOf(id))!=null){
                            try {
                                Contact con = cons.get(String.valueOf(id));
                                contactRepo.removeContact(con);
                            } catch(Exception e){

                            } finally {
                                updateCons=false;
                            }
                        }
                    }
                    break;
                }
                default:
                    return;
            }
        }
    }

    private static void exportInfo(){
        contactRepo.exportInformation();
    }

    private static void importInformation(){
        contactRepo.importInformation();
    }
}
