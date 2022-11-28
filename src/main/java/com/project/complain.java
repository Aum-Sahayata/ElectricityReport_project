package com.project;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class complain {
    
    public static final String RESET = "\033[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String BLUE= "\u001B[34m";
    public static final String BLUE_BG= "\u001B[44m";
    public static final String WHITE_BG = "\u001B[47m";

    static int ID;
    
    static void action(int id){
        Scanner in = new Scanner(System.in);
        ID = id;
        
        Boolean stopper = true;
        
        while(stopper){

            System.out.println(BLUE_BG+"---------------------------------------------------------------------\n"+RESET);
            System.out.println(YELLOW+"1"+RESET+".Register a new complaint");
            System.out.println(YELLOW+"2"+RESET+".Check status of previous complaints");
            System.out.println(YELLOW+"3"+RESET+".Exit");
            System.out.println("\n"+BLUE_BG+"---------------------------------------------------------------------"+RESET);
            
            System.out.print("\nEnter your choice: ");
            int choice = in.nextInt();
            switch (choice) {
            case 1:
            newcomp();
            break;
            
            case 2:
            status();
            break;

            case 3:
            stopper=false;
            break;
            }
        }
    }
    
    private static void newcomp(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n"+GREEN_BG+"------------------------------New Complaint------------------------------\n"+RESET);
        System.out.println("\nNote:Your contact information will be take from your account.");
        System.out.println("\n"+RED+"Subject/Title:"+RESET);
        String subject = in.nextLine();
        System.out.println("\n"+RED+"Describe your problem:"+RESET);
        String detail = in.nextLine();
        System.out.println("\n"+RED+"Address/Location:"+RESET);
        String addr = in.nextLine();
        System.out.println("\n"+GREEN_BG+"---------------------------------------------------------------------"+RESET);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            PreparedStatement pstm = con.prepareStatement("insert into complaint (uid,subject,details,location)"+"values(?,?,?,?)");
            
            pstm.setInt(1, ID);
            pstm.setString(2, subject);
            pstm.setString(3, detail);
            pstm.setString(4, addr);
            pstm.executeUpdate();
            con.close();
            System.out.println("\nNew complaint registered you can check status from your account.");
            System.out.println("\n---------------------------------------------------------------------\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private static void status(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select subject,details,status from complaint where uid="+ID);
            if (rs.next() == false) {
                System.out.println("You do not have any complaints.");
            } else {
                System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                System.out.println(RED+"N"+RESET+" - means your complaint is not yet assigned to anyone (Please be patient)");
                System.out.println(YELLOW+"U"+RESET+" - means your complaint is being resloved");
                System.out.println(GREEN+"D"+RESET+" - means your complaint resloved\n\n");
                do {
                    System.out.println(BLUE+"Subject: "+RESET+rs.getString(1));
                    System.out.println(BLUE+"Details: "+RESET+rs.getString(2));
                    if(rs.getString(3).equals("N")){
                        System.out.println(BLUE+"Status: "+RED+rs.getString(3));
                    }
                    else if(rs.getString(3).equals("U")){
                        System.out.println(BLUE+"Status: "+YELLOW+rs.getString(3));
                    }
                    else if(rs.getString(3).equals("D")){
                        System.out.println(BLUE+"Status: "+GREEN+rs.getString(3));
                    }
                    System.out.println("\n"+WHITE_BG+"---------------------------------------------------------------------\n"+RESET);
                } while (rs.next());
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}