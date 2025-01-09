/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client.objects;

import java.util.Arrays;

/**
 *
 * @author erni
 */
public class User {

    public enum level {
        REGULAR, PREMIUM, ADMIN
    };
    //Admin have admin, premium and regular user privileges
    //Premium have premium and regular user privileges
    //Regular have just regular user privileges

    public long id;
    private level user_level;
    public String username;
    public String firstname;
    public String lastname;
    public String password;

    public User(String username, String firstname, String lastname, String password) {
        user_level = User.level.REGULAR;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }
    
    
    public User(){
        
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", user_level=" + user_level + ", firstname=" + firstname + ", lastname=" + lastname + '}';
    }

}
