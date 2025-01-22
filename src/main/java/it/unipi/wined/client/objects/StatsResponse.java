/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author erni
 */
public class StatsResponse implements Serializable{
    
    public int males;
    public int females;
    public HashMap<String, Integer> regions = new HashMap<String, Integer>();
    public HashMap<String, Integer> priceCategories = new HashMap<String, Integer>();
    public long uniqueWines;
    public double avgCost;
    public ArrayList<String> bestSelling = new ArrayList<>();

    
    public StatsResponse() {
        
    }

    public void setMales(int males) {
        this.males = males;
    }

    public void setFemales(int females) {
        this.females = females;
    }

    public void setUniqueWines(long uniqueWines) {
        this.uniqueWines = uniqueWines;
    }

    public void setAvgCost(double avgCost) {
        this.avgCost = avgCost;
    }

    @Override
    public String toString() {
        return "StatsResponse{" + "males=" + males + ", females=" + females + ", regions=" + regions + ", priceCategories=" + priceCategories + ", uniqueWines=" + uniqueWines + ", avgCost=" + avgCost + '}';
    }
    
    public void printStats() {
        System.out.println("\n ------------------ [ SYSTEM STATISTICS ] ---------------");
        System.out.println("\n Males : " + males + " | Females : " + females);
        System.out.println("\n Unique Wines : " + uniqueWines );
        System.out.println("\n Average order cost : " + avgCost );
        System.out.println("\n Price Categories : \n");
        for (String s : priceCategories.keySet()){
            System.out.println("   " + s + " : " + priceCategories.get(s));
        }
        System.out.println("\n Best selling last month : ");
        for (String s : bestSelling){
            System.out.println("      " + s);
        }
    }
    
    public void printRegionStats(String key) {
        try {
            System.out.println("   " + key + " : " + regions.get(key));
        } catch (Exception e){
            System.out.println("Invalid Region");
        }
    }
    
    
    
}

