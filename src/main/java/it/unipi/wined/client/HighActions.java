/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client;

import com.google.gson.Gson;
import it.unipi.wined.client.objects.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author erni
 */
public class HighActions {

    public static void deleteUser() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        if (UserActions.isUserLogged()) {
            ArrayList<User> input = new ArrayList<>();
            input.add(WinedClient.currentUser);
            System.out.print("User to delete : ");
            String username = sc.nextLine();
            User adduser = new User();
            adduser.setNickname(username);
            input.add(adduser);
            try {
                URL url = new URL("http://" + ip + "/admin-act/delete");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(input);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                
                if (retLine.equals("200")){
                    System.out.println("User deleted succesfully!");
                } 
                if (retLine.equals("503")){
                    System.out.println("Attempting unauthorized action!");
                } 
                if (retLine.equals("500") || retLine.equals("400")){
                    System.out.println("Server error");
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("you have to login!");
        }

    }
    
    public static void updateUserToAdmin() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        if (UserActions.isUserLogged()) {
            ArrayList<User> input = new ArrayList<>();
            input.add(WinedClient.currentUser);
            System.out.print("User to update : ");
            String username = sc.nextLine();
            User adduser = new User();
            adduser.setNickname(username);
            input.add(adduser);
            try {
                URL url = new URL("http://" + ip + "/admin-act/update-to-admin");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(input);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                
                if (retLine.equals("200")){
                    System.out.println("User updated succesfully!");
                } 
                if (retLine.equals("503")){
                    System.out.println("Attempting unauthorized action!");
                } 
                if (retLine.equals("500") || retLine.equals("400")){
                    System.out.println("Server error");
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("you have to login!");
        }

    }
    
    public static void updateUserToPremium() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        if (UserActions.isUserLogged()) {
            ArrayList<User> input = new ArrayList<>();
            input.add(WinedClient.currentUser);
            System.out.print("User to update : ");
            String username = sc.nextLine();
            User adduser = new User();
            adduser.setNickname(username);
            input.add(adduser);
            try {
                URL url = new URL("http://" + ip + "/admin-act/update-to.premium");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(input);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                
                if (retLine.equals("200")){
                    System.out.println("User deleted succesfully!");
                } 
                if (retLine.equals("503")){
                    System.out.println("Attempting unauthorized action!");
                } 
                if (retLine.equals("500") || retLine.equals("400")){
                    System.out.println("Server error");
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("you have to login!");
        }

    }

}
