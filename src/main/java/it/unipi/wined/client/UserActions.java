/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client;

import com.google.gson.Gson;
import it.unipi.wined.client.objects.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author erni
 */
public class UserActions {

    public static boolean isUserLogged() {
        if (WinedClient.currentUser != null) {
            return true;
        } else {
            System.out.println("You have to be logged in to perform this action!");
            System.out.println("To login run the 'login' command");
            return false;
        }
    }

    public static boolean wineExists(String ip, String winename) throws ProtocolException, MalformedURLException, IOException {
        Gson gson = new Gson();
        URL url = new URL("http://" + ip + "/regular-user-act/check-wine");
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setRequestMethod("POST");
        urlCon.setRequestProperty("Content-Type", "application/json");
        String inputJs = gson.toJson(winename);
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

    public static void followUser(String targetUsername) {

        String ip = WinedClient.ip;
        Gson gson = new Gson();

        if (isUserLogged()) { //check user is logged
            ArrayList<String> relation = new ArrayList<>();
            relation.add(WinedClient.currentUser.username);
            relation.add(targetUsername);

            try {

                if (Access.userExists(ip, targetUsername)) {
                    //perform follow
                    URL url = new URL("http://" + ip + "/regular-user-act/follow");
                    HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                    urlCon.setRequestMethod("POST");
                    urlCon.setRequestProperty("Content-Type", "application/json");
                    String inputJs = gson.toJson(relation);
                    urlCon.setDoOutput(true);
                    urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                    BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    String retLine = buf.readLine();

                    if (retLine.equals("0")) {
                        System.out.println("You now follow " + targetUsername + " !");
                    } else {
                        System.out.println("Server error occured!");
                    }
                } else {
                    System.out.println("The user you want to follow doesn't exist!");
                    System.out.println("Try to check for eventual misspellings");
                }
            } catch (Exception e) {
            }
        }
    }

    public static void reviewWine() {

        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);

        if (isUserLogged()) {

            ArrayList<String> review = new ArrayList<>();

            review.add(WinedClient.currentUser.username);

            System.out.print("Wine to review: ");
            String wine = sc.nextLine();

            try {

                if (wineExists(ip, wine)) {
                    review.add(wine);

                    System.out.print("Rating: ");
                    String rating = sc.nextLine();
                    review.add(rating);
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    review.add(title);
                    System.out.print("Body: ");
                    String body = sc.nextLine();
                    review.add(body);

                    URL url = new URL("http://" + ip + "/regular-user-act/review");
                    HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                    urlCon.setRequestMethod("POST");
                    urlCon.setRequestProperty("Content-Type", "application/json");
                    String inputJs = gson.toJson(review);
                    urlCon.setDoOutput(true);
                    urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                    BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    String retLine = buf.readLine();
                    if (retLine.equals("0")) {
                        System.out.println("Thank you for sharing your opinion!");
                    } else {
                        System.out.println("Server error occured -- return code 1");
                    }
                } else {
                    System.out.println("Selected wine does not exist!");
                }
            } catch (Exception e) {
                System.out.println("Server error occured");

            }

        } else {
            System.out.println("You have to be logged in to review a wine!");
        }
    }

    public static void viewUsersReviews(String username) {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        try {
            if (Access.userExists(ip, username)) {
                URL url = new URL("http://" + ip + "/access/check-username");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(username);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
            } else {
                System.out.println("User does not exist");
            }
        } catch (Exception e) {

        }
    }
}
