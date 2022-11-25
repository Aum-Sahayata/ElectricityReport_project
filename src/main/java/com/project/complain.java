package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class complain {

    static int ID;
    
    public static void action(int id){
        Scanner in = new Scanner(System.in);
        
        ID = id;
        System.out.println("---------------------------------------------------------------------\n");
        System.out.println("1.Register a new complaint");
        System.out.println("2.Check status of previous complaints");
        System.out.println("\n---------------------------------------------------------------------");
        
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();
        
        switch (choice) {
            case 1:
            newcomp();
            break;
            
            case 2:
            status();
            break;
        }
        in.close();
    }
    
    static void newcomp(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n------------------------------New Complaint------------------------------\n");
        System.out.println("\nNote:Your contact information will be take from your account.");
        System.out.println("\nSubject/Title:");
        String subject = in.nextLine();
        System.out.println("\nDescribe your problem:");
        String detail = in.nextLine();
        System.out.println("\nAddress/Location:");
        String addr = in.nextLine();
        System.out.println("\n---------------------------------------------------------------------");
        in.close();
        
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
            System.out.println("\n---------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void status(){
        System.out.println("Stauts of old complaints.");
    }
}