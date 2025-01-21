/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client;

import com.google.gson.Gson;
import it.unipi.wined.client.objects.Review;
import it.unipi.wined.client.objects.User;
import it.unipi.wined.client.objects.Wine_WineMag;
import it.unipi.wined.client.objects.Wine_WineVivino;
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

    public static void followUser() {

        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);

        if (isUserLogged()) { //check user is logged
            ArrayList<String> relation = new ArrayList<>();
            relation.add(WinedClient.currentUser.getNickname());
            System.out.print("User to follow : ");
            String targetUsername = sc.nextLine();
            try {
                if (Access.userExists(ip, targetUsername)) {
                    relation.add(targetUsername);
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

            review.add(WinedClient.currentUser.getNickname());

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

    public static void viewUsersReviewedWines() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("User to check: ");
            String username = sc.nextLine();
            if (Access.userExists(ip, username)) {
                URL url = new URL("http://" + ip + "/regular-user-act/user-reviewed-wines");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(username);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                String[] rets = retLine.replace("Record<{w.name: \"", "").replace("\"}>", "").replace("[", "").replace("]", "").split(",");
                for (String s : rets) {
                    System.out.println(s);
                }
            } else {
                System.out.println("User does not exist");
            }
        } catch (Exception e) {

        }
    }

    public static void viewUsersReviews() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("User to check: ");
            String username = sc.nextLine();
            if (Access.userExists(ip, username)) {
                URL url = new URL("http://" + ip + "/regular-user-act/user-reviews");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(username);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                Review[] revs = gson.fromJson(retLine, Review[].class);
                System.out.println("\n--------" + username + "'s reviews ---------\n");
                for (Review r : revs) {
                    System.out.println("+++" + r.wine + "+++");
                    System.out.println("      Rating : " + r.rating);
                    System.out.println("      Title : " + r.title);
                    System.out.println("      Text : " + r.body);
                    System.out.println("----------------------\n");
                }

            } else {
                System.out.println("User does not exist");
            }
        } catch (Exception e) {

        }
    }

    public static void viewWineReviews() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Wine to check: ");
            String wine = sc.nextLine();
            if (UserActions.wineExists(ip, wine)) {
                URL url = new URL("http://" + ip + "/regular-user-act/wine-reviews");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(wine);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                Review[] revs = gson.fromJson(retLine, Review[].class);
                System.out.println("\n--------" + wine + "'s reviews ---------\n");
                for (Review r : revs) {
                    System.out.println("--- Review by : " + r.user + " ---");
                    System.out.println("      Rating : " + r.rating);
                    System.out.println("      Title : " + r.title);
                    System.out.println("      Text : " + r.body);
                    System.out.println("----------------------\n");
                }

            } else {
                System.out.println("Wine does not exist");
            }
        } catch (Exception e) {

        }
    }

    public static void likeWine() {
        String ip = WinedClient.ip;
        Gson gson = new Gson();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        input.add(WinedClient.currentUser.getNickname());
        try {
            if (isUserLogged()) {
                System.out.print("Wine to like: ");
                String wine = sc.nextLine();
                if (wineExists(ip, wine)) {
                    input.add(wine);
                    URL url = new URL("http://" + ip + "/regular-user-act/like-wine");
                    HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                    urlCon.setRequestMethod("POST");
                    urlCon.setRequestProperty("Content-Type", "application/json");
                    String inputJs = gson.toJson(input);
                    urlCon.setDoOutput(true);
                    urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                    BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                    String retLine = buf.readLine();

                    if (retLine.equals("0")) {
                        System.out.println(wine + " liked!");
                    } else {
                        System.out.println("An error occured!");
                    }
                } else {
                    System.out.println("Wine doesn't exist!");

                }
            } else {
                System.out.println("You are not logged in!");
            }
        } catch (Exception e) {

        }
    }

    public static void getSuggestedWines() {
        Gson gson = new Gson();
        String ip = WinedClient.ip;
        try {
            if (isUserLogged()) {
                URL url = new URL("http://" + ip + "/regular-user-act/get-suggested-by-followers");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(WinedClient.currentUser);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                if (!retLine.equals("500")) {
                    String[] results = gson.fromJson(retLine, String[].class);
                    for (String s : results) {
                        System.out.println("---------------");
                        System.out.println("   " + s);
                        System.out.println("---------------");
                    }
                } else {
                    System.out.println("Server error! ");
                }
            } else {
                System.out.println("You have to login!");
            }
        } catch (Exception e) {
            System.out.println("Some error occurred! ");
        }

    }

    public static void getWineResume() {
        Gson gson = new Gson();
        String ip = WinedClient.ip;
        Scanner sc = new Scanner(System.in);
        try {
            if (isUserLogged()) {
                System.out.print("Wine to check: ");
                String wine = sc.nextLine();
                ArrayList<Object> input = new ArrayList<>();
                input.add(WinedClient.currentUser);
                input.add(wine);
                
                URL url = new URL("http://" + ip + "/regular-user-act/get-wine");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("POST");
                urlCon.setRequestProperty("Content-Type", "application/json");
                String inputJs = gson.toJson(input);
                urlCon.setDoOutput(true);
                urlCon.getOutputStream().write(inputJs.getBytes("UTF-8"));
                BufferedReader buf = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                String retLine = buf.readLine();
                System.out.println(retLine);
                
                if(WinedClient.currentUser.getUser_level() == User.Level.PREMIUM || WinedClient.currentUser.getUser_level() == User.Level.ADMIN){
                    Wine_WineVivino[] wines = gson.fromJson(retLine, Wine_WineVivino[].class);
                    for (Wine_WineVivino w : wines){
                        w.printWine();
                    }
                } else {
                    Wine_WineMag[] wines = gson.fromJson(retLine, Wine_WineMag[].class);
                    for (Wine_WineMag w : wines){
                        w.printWine();
                    }
                }
                
            } else {
                System.out.println("You have to login!");
            }
        } catch (Exception e) {

        }
    }
}
