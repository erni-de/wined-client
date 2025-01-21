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

    public Commands() {
        //services.put(/*command name here*/, new IService() {@Override public void call(Object param) {/*command here*/}});
        services.put("login", new IService() {@Override public void call(String[] param) {Access.login();}});
        services.put("register", new IService() {@Override public void call(String[] param) {Access.register();}});
        services.put("logout", new IService() {@Override public void call(String[] param) {Access.logout();}});
        services.put("follow", new IService() {@Override public void call(String[] param) {UserActions.followUser();}});
        services.put("review", new IService() {@Override public void call(String[] param) {UserActions.reviewWine();}});
        services.put("urevw", new IService() {@Override public void call(String[] param) {UserActions.viewUsersReviewedWines();}});
        services.put("urev", new IService() {@Override public void call(String[] param) {UserActions.viewUsersReviews();}});
        services.put("wrev", new IService() {@Override public void call(String[] param) {UserActions.viewWineReviews();}});
        services.put("like", new IService() {@Override public void call(String[] param) {UserActions.likeWine();}});
        services.put("suggestme", new IService() {@Override public void call(String[] param) {UserActions.getSuggestedWines();}});
        services.put("resume", new IService() {@Override public void call(String[] param) {UserActions.getWineResume();}});

        //ADMIN
        services.put("deluser", new IService() {@Override public void call(String[] param) {HighActions.deleteUser();}});
        services.put("makeadmin", new IService() {@Override public void call(String[] param) {HighActions.updateUserToAdmin();}});
        services.put("makepremium", new IService() {@Override public void call(String[] param) {HighActions.updateUserToPremium();}});
        services.put("systats", new IService() {@Override public void call(String[] param) {HighActions.getStats();}});
        services.put("regstats", new IService() {@Override public void call(String[] param) {HighActions.consultRegion();}});

                
        

        
//wv - wineview -> output una scheda per il vino (maggiori info se user è premium/admin)
    }
}

