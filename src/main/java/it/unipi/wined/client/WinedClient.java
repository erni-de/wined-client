/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.unipi.wined.client;

import it.unipi.wined.client.objects.User;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author erni
 */
public class WinedClient {
    
    public static String ip;
    public static User currentUser;  
    
    public static void main(String[] args) {
        System.out.println("Welcome to Wined - The online wine shop!");
        ip = "127.0.0.1:8080"; //default
        String connection_status = CheckConnection.checkConnection(ip);
        Scanner input = new Scanner(System.in);
        String usernameString;
        if (connection_status.equals("FAILED")){
            System.out.println("Connection to " + ip + " failed -> Exiting");
            System.exit(0);
        } else {
            Commands commands = new Commands();
            while(true){
                if(currentUser == null){
                    usernameString = "";
                } else {
                    usernameString = currentUser.getNickname();
                }
                System.out.print(usernameString + ">>>");
                String[] com = input.nextLine().split(" ");
                try {commands.services.get(com[0]).call(Arrays.copyOfRange(com, 1, com.length));} catch (NullPointerException npe){System.out.println(com[0] + " : Command not Found");}
            }
        }
                
    }
}
