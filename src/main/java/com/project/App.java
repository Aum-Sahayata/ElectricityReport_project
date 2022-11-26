package com.project;
public class App {

    public static void main(String[] args){
        login.loginChoice();
        if(login.loginType=='u'){
            complain.action(login.ID);
        }
        else if(login.loginType=='a'){
            
        }
    }
}