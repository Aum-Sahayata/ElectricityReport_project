package com.project;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class manage {

    public static final String RESET = "\033[0m";
    public static final String BLUE= "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED_BG = "\u001B[41m";
    public static final String WHITE_BG = "\u001B[47m";

    static void menu(){

        Scanner in = new Scanner(System.in);

        System.out.println("\n"+RED_BG+"---------------------------------------------------------------------\n"+RESET);
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
            break;
        }
    }


    private static void showall() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,details,status from complaint;");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(3));
                    if(rs.getString(4).equals("N")){
                        System.out.println(BLUE+"Status: "+RED+rs.getString(4));
                    }
                    else if(rs.getString(4).equals("U")){
                        System.out.println(BLUE+"Status: "+YELLOW+rs.getString(4));
                    }
                    if(rs.getString(4).equals("D")){
                        System.out.println(BLUE+"Status: "+GREEN+rs.getString(4));
                    }
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    private static void showN() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,details,status from complaint where status='N';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Status: "+RED+rs.getString(4));
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    private static void showU() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,details,status from complaint where status='U';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Status: "+YELLOW+rs.getString(4));
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    private static void showD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id,subject,details,status from complaint where status='D';");
            if (rs.next() == false) {
                System.out.println("There are no complaints");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                do {
                    System.out.println(BLUE+"ID: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(2));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(3));
                    System.out.println(BLUE+"Status: "+GREEN+rs.getString(4));
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}