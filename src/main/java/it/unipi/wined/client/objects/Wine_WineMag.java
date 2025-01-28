package it.unipi.wined.client.objects;

/**
 *
 * @author nicol
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.unipi.wined.client.UserActions;
import it.unipi.wined.client.WinedClient;
import java.util.Map;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wine_WineMag extends AbstractWine {

    private String winery_id;
    private String winery_name;

    public Wine_WineMag() {
        super();
    }

    public Wine_WineMag(String id, String name, int price, String variety, 
                        String region, String description, String country, 
                        int alcohol_percentage, String provenance, 
                        String winery_id, String winery_name) {
        super(id, name, price, description, country, region, provenance, variety, alcohol_percentage);
        this.winery_id = winery_id;
        this.winery_name = winery_name;
    }

   

    public String getWinery_id() {
        return winery_id;
    }

    public String getWinery_name() {
        return winery_name;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("winery")
    private void unpackNestedWinery(Map<String, Object> winery) {
        if (winery != null) {
            Object wId = winery.get("id");
            if (wId != null) {
                this.winery_id = String.valueOf(wId);
            }
            this.winery_name = (String) winery.get("name");
        }
    }

    public void setWinery_name(String winery_name) {
        this.winery_name = winery_name;
    }

    
    
    @Override
    public String toString() {
        return "Wine_WineMag{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", variety='" + variety + '\'' +
                ", region='" + region + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                ", alcohol_percentage=" + alcohol_percentage +
                ", provenance='" + provenance + '\'' +
                ", winery_id='" + winery_id + '\'' +
                ", winery_name='" + winery_name + '\'' +
                '}';
    }
    
    public void printWine(){
        System.out.println("\n----------------------------------------");
        System.out.println(" " + name + "  |  " + variety + "  |  Vol." + alcohol_percentage + "%  |  " + price + "$ ");
        System.out.println("\n" + winery_name);
        System.out.println("\n     " + country + "  |  " + region + "  |  " + provenance);
        System.out.println("----------------------------------------");
        System.out.println(" Description : \n    " + description);
        System.out.println("----------------------------------------");
    }
    
    public static Wine_WineMag createCLI() {
        Wine_WineMag wine = new Wine_WineMag();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Wine name : ");
            String name = sc.nextLine();
            wine.setName(name);
            try {
            if(UserActions.wineExists(WinedClient.ip, name)){
                System.out.println("Wine name already exists!");
            } else {
                break;
            }
                } catch (Exception e) {
                    System.out.println("Server error");
                }
        }
        System.out.print("Description : ");
        wine.setDescription(sc.nextLine());
        System.out.print("Winery Name : ");
        wine.setWinery_name(sc.nextLine());
        System.out.print("Country : ");
        wine.setCountry(sc.nextLine());
        System.out.print("Region : ");
        wine.setRegion(sc.nextLine());
        wine.setProvenance("W");
        System.out.print("Variety : ");
        wine.setVariety(sc.nextLine());
        System.out.print("Alcohol vol. % : ");
        wine.setAlcohol_percentage(sc.nextInt());
        System.out.print("Wine price[$] (int value) : ");
        wine.setPrice(sc.nextInt());
        System.out.println("Registration completed!");
        return wine;
    }
}
