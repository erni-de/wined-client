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
    @SerializedName("w.name")
    public String wine;
    @SerializedName("b.rating")
    public String rating;
    @SerializedName("b.text")
    public String text;
    @SerializedName("b.title")
    public String title;
    @SerializedName("u.username")
    public String user;

    @Override
    public String toString() {
        return "Review{" + "wine=" + wine + ", rating=" + rating + ", text=" + text + ", title=" + title + '}';
    }
    
    
    
}
