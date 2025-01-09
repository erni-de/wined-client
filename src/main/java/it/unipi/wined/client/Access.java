/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client;

import it.unipi.wined.client.objects.LoginRequest;
import com.google.gson.Gson;
import it.unipi.wined.client.objects.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author erni
 */
public class Access {

    public static boolean userExists(String ip, String username) throws ProtocolException, MalformedURLException, IOException {
        Gson gson = new Gson();
        URL url = new URL("http://" + ip + "/access/check-username");
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setRequestMethod("POST");
        urlCon.setRequestProperty("Content-Type", "application/json");
        String inputJs = gson.toJson(username);
        urlCon.setDoOutput(true);
        urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
        BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
        String retLine = buf.readLine();

        if (retLine.equals("1")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void logout() {
        WinedClient.currentUser = null;
        System.out.println("Bye!");
    }

    public static void login() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("username: ");
            String username = sc.nextLine();

            if (userExists(ip, username)) {

                System.out.print("password: ");
                String password = sc.nextLine();

                LoginRequest lr = new LoginRequest(username, password);

                URL url = new URL("http://" + ip + "/access/login");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");

                String inputJs = gson.toJson(lr);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String userjson = buf.readLine();
                System.out.println("Welcome back " + userjson + " !");
                WinedClient.currentUser.username = userjson;
            } else {
                System.err.println("User does not exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some error occured");
        }
    }

    public static void register() {
        String ip = WinedClient.ip;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Registering new user");
            boolean done = false;
            String username = null;

            while (!done) { //try to create non existing username
                System.out.print("New Username: ");
                username = sc.nextLine();

                if (!userExists(ip, username)) { //0 means Ok
                    done = true;
                } else {
                    System.out.println("Username already exists!");
                }
            }

            System.out.print("Firstname: ");
            String firstname = sc.nextLine();
            System.out.print("Lastname: ");
            String lastname = sc.nextLine();

            done = false;
            String password = null;
            while (!done) {
                System.out.print("New password: ");
                password = sc.nextLine();
                System.out.print("Repeat new password: ");
                if (!sc.nextLine().equals(password)) {
                    System.out.println("The password was not repeated correctly!");
                } else {
                    done = true;
                }
            }

            URL url = new URL("http://" + ip + "/access/register");
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setRequestProperty("Content-Type", "application/json");
            Gson gson = new Gson();
            String inputJs = gson.toJson(new User(username, firstname, lastname, password));
            urlCon.setDoOutput(true);
            urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
            BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String serverResponse = buf.readLine();

            if (serverResponse.equals("0")) {
                System.out.println("User created succesfully!");
                System.out.println("You can login using your credentials using the 'login' command");
            } else {
                System.out.println("Sorry, some server error occured while creating user");
            }

        } catch (Exception e) {
            System.out.println("App Client error occured");
        }
    }
}
