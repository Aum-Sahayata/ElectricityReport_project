package com.project;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class manage {

    public static final String RESET = "\033[0m";
    public static final String BLUE= "\u001B[34m";
    public static final String BLUE_BG= "\u001B[44m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED_BG = "\u001B[41m";
    public static final String WHITE_BG = "\u001B[47m";

    static void menu(){

        Scanner in = new Scanner(System.in);

        Boolean stopper = true;

        while(stopper){

                System.out.println("\n"+RED_BG+"---------------------------------------------------------------------"+RESET+"\n");
                System.out.println(BLUE+"1"+RESET+".Show all complaints");
                System.out.println(BLUE+"2"+RESET+".Show all unassigned complaints");
                System.out.println(BLUE+"3"+RESET+".Show all complaints being resolved");
                System.out.println(BLUE+"4"+RESET+".Show all resolved complaints");
                System.out.println(BLUE+"5"+RESET+".Exit");
                System.out.println("\n"+RED_BG+"---------------------------------------------------------------------"+RESET);

                System.out.print("\nEnter your choice: ");
                int choice = in.nextInt();
                switch (choice) {
                case 1:
                showall();
                break;
                
                case 2:
                showN();
                break;

                case 3:
                showU();
                break;

                case 4:
                showD();
                break;

                case 5:
                stopper=false;
                break;
            }
    }
    }


    private static void showall() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,report_date,details,status from complaint;");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Date: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(4));
                    if(rs.getString(5).equals("N")){
                        System.out.println(BLUE+"Status: "+RED+rs.getString(5)+RESET);
                    }
                    else if(rs.getString(5).equals("U")){
                        System.out.println(BLUE+"Status: "+YELLOW+rs.getString(5)+RESET);
                    }
                    if(rs.getString(5).equals("D")){
                        System.out.println(BLUE+"Status: "+GREEN+rs.getString(5)+RESET);
                    }
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    private static void showN() {
        Scanner in = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,report_date,details,status from complaint where status='N';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Date: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(4));
                    System.out.println(BLUE+"Status: "+RED+rs.getString(5)+RESET);
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println(BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.println(YELLOW+"1"+RESET+".Edit status");
        System.out.println(YELLOW+"2"+RESET+".Exit");
        System.out.println("\n"+BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.print("\nEnter your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
            editstatus();
            break;
            
            case 2:
            break;
        }
    }

    
    
    private static void showU() {
    Scanner in = new Scanner(System.in);    

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,report_date,details,status from complaint where status='U';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Date: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(4));
                    System.out.println(BLUE+"Status: "+YELLOW+rs.getString(5)+RESET);
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println(BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.println(YELLOW+"1"+RESET+".Edit status");
        System.out.println(YELLOW+"2"+RESET+".Exit");
        System.out.println("\n"+BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.print("\nEnter your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
            editstatus();
            break;
            
            case 2:
            break;
        }
    }
    
    private static void showD() {
        Scanner in = new Scanner(System.in); 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,report_date,details,status from complaint where status='D';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Date: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(4));
                    System.out.println(BLUE+"Status: "+GREEN+rs.getString(5)+RESET);
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        System.out.println(BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.println(YELLOW+"1"+RESET+".Edit status");
        System.out.println(YELLOW+"2"+RESET+".Exit");
        System.out.println("\n"+BLUE_BG+"---------------------------------------------------------------------"+RESET+"\n");
        System.out.print("\nEnter your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
            editstatus();
            break;
            
            case 2:
            break;
        }
    }

    private static void editstatus() {try {
        int id;
        Scanner in = new Scanner(System.in);
        System.out.println(YELLOW_BG+"\n---------------------------------------------------------------------"+RESET+"\n");
        System.out.print("\nEnter ID of complaint: ");
        id = in.nextInt();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select subject,details,status from complaint where id="+id);
        if (rs.next() == false) {
            System.out.println("You entered incorrect ID.");
        } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
                System.out.println(BLUE+"Subject: "+RESET+rs.getString(1));
                System.out.println(BLUE+"Details: "+RESET+rs.getString(2));
                if(rs.getString(3).equals("N")){
                    System.out.println(BLUE+"Status: "+RED+rs.getString(3)+RESET);
                }
                else if(rs.getString(3).equals("U")){
                    System.out.println(BLUE+"Status: "+YELLOW+rs.getString(3)+RESET);
                }
                else if(rs.getString(3).equals("D")){
                    System.out.println(BLUE+"Status: "+GREEN+rs.getString(3)+RESET);
                }
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n");
            }
            
            System.out.println(RED+"N"+RESET+" - means your complaint is not yet assigned to anyone (Please be patient)");
            System.out.println(YELLOW+"U"+RESET+" - means your complaint is being resloved");
            System.out.println(GREEN+"D"+RESET+" - means your complaint resloved\n\n");
            System.out.print("Change status to: ");
            String stat = in.next();
            String update = "update complaint set status='"+stat+"' where id="+id;

            stm.executeUpdate(update);
        con.close();
        System.out.println("\n"+YELLOW_BG+"---------------------------------------------------------------------"+RESET+"\n");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}