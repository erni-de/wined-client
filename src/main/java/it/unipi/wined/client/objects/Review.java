/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.wined.client.objects;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author erni
 */
public class Review implements Serializable{
    public String wine;
    public String rating;
    public String body;
    public String title;
    public String user;

    @Override
    public String toString() {
        return "Review{" + "wine=" + wine + ", rating=" + rating + ", text=" + body + ", title=" + title + '}';
    }
    
    
    
}
