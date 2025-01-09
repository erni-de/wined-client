/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author erni
 */
public class Commands {

    Map<String, IService> services = new TreeMap<>();
    
    public static void printh(String in){
        System.out.println(in);
    }

    public Commands() {
        //services.put(/*command name here*/, new IService() {@Override public void call(Object param) {/*command here*/}});
        services.put("login", new IService() {@Override public void call(String[] param) {Access.login();}});
        services.put("register", new IService() {@Override public void call(String[] param) {Access.register();}});
        services.put("print", new IService() {@Override public void call(String[] param) {printh(param[0]);}});
        services.put("logout", new IService() {@Override public void call(String[] param) {Access.logout();}});
        services.put("follow", new IService() {@Override public void call(String[] param) {UserActions.followUser(param[0]);}});
        services.put("review", new IService() {@Override public void call(String[] param) {UserActions.reviewWine();}});

    }
}

