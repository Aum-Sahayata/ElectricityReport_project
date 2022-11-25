package com.project;

import java.sql.*;
import java.util.Scanner;

public class login {

    public static int ID;
    
    public static void loginChoice(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        System.out.println("1.User Login");
        System.out.println("2.User Register");
        System.out.println("--------------------------------------");
        System.out.println("3.Admin Login");
        System.out.println("4.Admin Register");
        System.out.println("");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("");
        
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();
        
        switch (choice) {
            case 1:
            userLogin();
            break;
            
            case 2:
            userReg();
            break;
            
            case 3:
            adminLogin();
            break;
            
            case 4:
            adminReg();
            break;
        }
        in.close();
    }
    
    static void userLogin(){
        Scanner in = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            System.out.println("\n------------------------------User Login------------------------------\n");
            System.out.println("\nEnter your phone number:");
            Long phoneNo = in.nextLong();
            System.out.println("\nEnter your Password:");
            String passwd = in.next();
            ResultSet rs = stm.executeQuery("select id from user where phone_no="+phoneNo+" and password='"+passwd+"'");
            if(rs.next()){
                System.out.println("==Loged in!!==\n");
                ID=rs.getInt(1);
            }
            else{
                System.out.println("==Incorrect Phone number or password==\n (Register first if haven't already registered)\n");
            }
            System.out.println("---------------------------------------------------------------------");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        in.close();
    }

    static void adminLogin(){
        Scanner in = new Scanner(System.in);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            System.out.println("\n-----------------------------Admin Login-----------------------------");
            System.out.println("\n");
            System.out.println("Enter your phone number:");
            Long phoneNo = in.nextLong();
            System.out.println("\nEnter your Password:");
            String passwd = in.next();
            ResultSet rs = stm.executeQuery("select id from admin where phone_no="+phoneNo+" and password='"+passwd+"'");
            if(rs.next()){
                System.out.println("==Loged in!!==\n");
                ID=rs.getInt(1);
            }
            else{
                System.out.println("==Incorrect Phone number or password==\n");
            }
            System.out.println("---------------------------------------------------------------------");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        in.close();
    }

    static void userReg(){
        Scanner in = new Scanner(System.in);

        System.out.println("\n----------------------------User Registration----------------------------\n");
        System.out.println("\nEnter your name:");
        String name = in.nextLine();
        System.out.println("\nCreate your Password:");
        String passwd = in.nextLine();
        System.out.println("\nEnter your phone number:");
        long phoneno = in.nextLong();
        System.out.println("\nEnter your address:");
        in.nextLine();
        String addr = in.nextLine();
        System.out.println("\n==========You are now registered.==========");
        System.out.println("\n---------------------------------------------------------------------");
        in.close();
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            PreparedStatement pstm = con.prepareStatement("insert into user (name,password,phone_no,address)"+"values(?,?,?,?)");
            
            pstm.setString(1, name);
            pstm.setString(2, passwd);
            pstm.setLong(3, phoneno);
            pstm.setString(4, addr);
            pstm.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void adminReg(){
        Scanner in = new Scanner(System.in);

        System.out.println("\n----------------------------Admin Registration----------------------------\n");
        System.out.println("\nEnter your name:");
        String name = in.nextLine();
        System.out.println("\nCreate your Password:");
        String passwd = in.nextLine();
        System.out.println("\nEnter your phone number:");
        long phoneno = in.nextLong();
        System.out.println("\n==========You are now registered.==========");
        System.out.println("\n---------------------------------------------------------------------");
        in.close();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            PreparedStatement pstm = con.prepareStatement("insert into admin (name,password,phone_no)"+"values(?,?,?)");

            pstm.setString(1, name);
            pstm.setString(2, passwd);
            pstm.setLong(3, phoneno);
            pstm.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}