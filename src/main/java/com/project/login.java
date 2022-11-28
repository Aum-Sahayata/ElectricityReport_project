package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import  java.sql.ResultSet;
import java.util.Scanner;

public class login {

    public static final String RESET = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String GREEN = "\u001B[32m";
    public static final String WHITE_BG = "\u001B[47m";
    public static final String CYAN_BG = "\033[46m";

    static int ID;
    static char loginType;
    static Boolean stopper = true;
    
    static void loginChoice(){
        Scanner in = new Scanner(System.in);

        while(stopper){

            System.out.println("\n"+CYAN_BG+"---------------------------------------------------------------------"+RESET+"\n");
            System.out.println(RED+"1"+RESET+".User Login");
            System.out.println(RED+"2"+RESET+".User Register");
            System.out.println("--------------------------------------");
            System.out.println(RED+"3"+RESET+".Admin Login");
            System.out.println(RED+"4"+RESET+".Admin Register\n");
            System.out.println(RED+"5"+RESET+".Exit\n");
            System.out.println(CYAN_BG+"---------------------------------------------------------------------"+RESET);
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

                case 5:
                stopper=false;
                break;
            }
        }
    }
    
    private static void userLogin(){
        Scanner in = new Scanner(System.in);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            System.out.println("\n"+WHITE_BG+"------------------------------User Login------------------------------"+RESET+"\n");
            System.out.print("\nEnter your phone number: ");
            Long phoneNo = in.nextLong();
            System.out.print("\nEnter your Password: ");
            String passwd = in.next();
            ResultSet rs = stm.executeQuery("select id from user where phone_no="+phoneNo+" and password='"+passwd+"'");
            if(rs.next()){
                System.out.println(GREEN+"\n===========Logged in===========\n"+RESET);
                ID=rs.getInt(1);
                loginType='u';
                stopper=false;
            }
            else{
                System.out.println("\n"+RED+"======Incorrect Phone number or password======\n"+RESET+"(Register first if haven't already registered)\n");
            }
            System.out.println(WHITE_BG+"---------------------------------------------------------------------"+RESET+"\n\n");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    private static void adminLogin(){
        Scanner in = new Scanner(System.in);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "test","JDBC@test1908");
            Statement stm = con.createStatement();
            System.out.println("\n"+WHITE_BG+"-----------------------------Admin Login-----------------------------"+RESET);
            System.out.println("\n");
            System.out.println("Enter your phone number:");
            Long phoneNo = in.nextLong();
            System.out.println("\nEnter your Password:");
            String passwd = in.next();
            ResultSet rs = stm.executeQuery("select id from admin where phone_no="+phoneNo+" and password='"+passwd+"'");
            if(rs.next()){
                System.out.println("\n"+GREEN+"===========Logged in===========\n"+RESET);
                ID=rs.getInt(1);
                loginType='a';
                stopper=false;
            }
            else{
                System.out.println(RED+"======Incorrect Phone number or password======\n"+RESET);
            }
            System.out.println(WHITE_BG+"---------------------------------------------------------------------"+RESET);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    private static void userReg(){
        Scanner in = new Scanner(System.in);

        System.out.println("\n"+GREEN_BG+"----------------------------User Registration----------------------------"+RESET+"\n");
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
        System.out.println("\n"+GREEN_BG+"---------------------------------------------------------------------"+RESET);
        
        

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

    private static void adminReg(){
        Scanner in = new Scanner(System.in);

        System.out.println("\n"+GREEN_BG+"----------------------------Admin Registration----------------------------"+RESET+"\n");
        System.out.println("\nEnter your name:");
        String name = in.nextLine();
        System.out.println("\nCreate your Password:");
        String passwd = in.nextLine();
        System.out.println("\nEnter your phone number:");
        long phoneno = in.nextLong();
        System.out.println("\n==========You are now registered.==========");
        System.out.println("\n"+GREEN_BG+"---------------------------------------------------------------------"+RESET);
        


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