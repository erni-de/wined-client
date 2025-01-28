package it.unipi.wined.client.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.unipi.wined.client.UserActions;
import it.unipi.wined.client.WinedClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wine_WineVivino extends AbstractWine {

    private String winery_id;
    private String winery_name;
    private Double acidity;
    private Double fizziness;
    private Double intensity;
    private Double sweetness;
    private Double tannin;
    private List<Flavor> flavorList;
    private Integer body;
    private String body_description;
    private List<Food> foodList;

    public Wine_WineVivino() {
        super();
        this.flavorList = new ArrayList<>();
        this.foodList = new ArrayList<>();
    }

    public Wine_WineVivino(String id, String name, int price, int alcohol_percentage,
            String description, String country, String region, String provenance,
            String variety, String winery_id, String winery_name, Double acidity,
            Double fizziness, Double intensity, Double sweetness, Double tannin,
            List<Flavor> flavorList, Integer body, String body_description,
            List<Food> foodList) {
        super(id, name, price, description, country, region, provenance, variety, alcohol_percentage);
        this.winery_id = winery_id;
        this.winery_name = winery_name;
        this.acidity = acidity;
        this.fizziness = fizziness;
        this.intensity = intensity;
        this.sweetness = sweetness;
        this.tannin = tannin;
        this.flavorList = flavorList;
        this.body = body;
        this.body_description = body_description;
        this.foodList = foodList;
    }

    public String getWinery_id() {
        return winery_id;
    }

    public String getWinery_name() {
        return winery_name;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("winery")
    private void unpackWinery(Map<String, Object> winery) {
        if (winery != null) {
            this.winery_id = (String) winery.get("id");
            this.winery_name = (String) winery.get("name");
        }
    }

    public Double getAcidity() {
        return acidity;
    }

    public Double getFizziness() {
        return fizziness;
    }

    public Double getIntensity() {
        return intensity;
    }

    public Double getSweetness() {
        return sweetness;
    }

    public Double getTannin() {
        return tannin;
    }

    public List<Flavor> getFlavorList() {
        return flavorList;
    }

    public void setWinery_id(String winery_id) {
        this.winery_id = winery_id;
    }

    public void setWinery_name(String winery_name) {
        this.winery_name = winery_name;
    }

    public void setAcidity(Double acidity) {
        this.acidity = acidity;
    }

    public void setFizziness(Double fizziness) {
        this.fizziness = fizziness;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    public void setSweetness(Double sweetness) {
        this.sweetness = sweetness;
    }

    public void setTannin(Double tannin) {
        this.tannin = tannin;
    }

    public void setFlavorList(List<Flavor> flavorList) {
        this.flavorList = flavorList;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public void setBody_description(String body_description) {
        this.body_description = body_description;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
    
    

    @SuppressWarnings("unchecked")
    @JsonProperty("taste")
    private void unpackTaste(Map<String, Object> taste) {
        if (taste == null) {
            return;
        }
        Map<String, Object> structure = (Map<String, Object>) taste.get("structure");
        if (structure != null) {
            this.acidity = toDouble(structure.get("acidity"));
            this.fizziness = toDouble(structure.get("fizziness"));
            this.intensity = toDouble(structure.get("intensity"));
            this.sweetness = toDouble(structure.get("sweetness"));
            this.tannin = toDouble(structure.get("tannin"));
        }
        List<Map<String, Object>> flavorArray = (List<Map<String, Object>>) taste.get("flavor");
        if (flavorArray == null) {
            return;
        }
        List<Flavor> tempFlavorList = new ArrayList<>();
        for (Map<String, Object> flavorObj : flavorArray) {
            Flavor fl = new Flavor();
            fl.setGroup((String) flavorObj.get("group"));
            fl.setMentions_count(toInteger(flavorObj.get("mentions_count")));
            tempFlavorList.add(fl);
        }
        this.flavorList = tempFlavorList;
    }

    public Integer getBody() {
        return body;
    }

    public String getBody_description() {
        return body_description;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("style")
    private void unpackStyle(Map<String, Object> style) {
        if (style == null) {
            return;
        }
        this.body = toInteger(style.get("body"));
        this.body_description = (String) style.get("body_description");
        List<Map<String, Object>> foodArray = (List<Map<String, Object>>) style.get("food");
        if (foodArray == null) {
            return;
        }
        List<Food> tempFoodList = new ArrayList<>();
        for (Map<String, Object> foodObj : foodArray) {
            Food f = new Food();
            f.setName((String) foodObj.get("name"));
            tempFoodList.add(f);
        }
        this.foodList = tempFoodList;
    }

    private Double toDouble(Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        }
        try {
            return Double.valueOf(val.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer toInteger(Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        try {
            return Integer.valueOf(val.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Wine_WineVivino{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", price=" + price
                + ", alcohol_percentage=" + alcohol_percentage
                + ", description='" + description + '\''
                + ", country='" + country + '\''
                + ", region='" + region + '\''
                + ", provenance='" + provenance + '\''
                + ", variety='" + variety + '\''
                + ", winery_id='" + winery_id + '\''
                + ", winery_name='" + winery_name + '\''
                + ", acidity=" + acidity
                + ", fizziness=" + fizziness
                + ", intensity=" + intensity
                + ", sweetness=" + sweetness
                + ", tannin=" + tannin
                + ", flavorList=" + flavorList
                + ", body=" + body
                + ", body_description='" + body_description + '\''
                + ", foodList=" + foodList
                + '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flavor {

        private String group;
        private Integer mentions_count;

        public Flavor() {
        }

        public Flavor(String group, Integer mentions_count) {
            this.group = group;
            this.mentions_count = mentions_count;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public Integer getMentions_count() {
            return mentions_count;
        }

        public void setMentions_count(Integer mentions_count) {
            this.mentions_count = mentions_count;
        }

        @Override
        public String toString() {
            return "Flavor{"
                    + "group='" + group + '\''
                    + ", mentions_count=" + mentions_count
                    + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Food {

        private String name;

        public Food() {
        }

        public Food(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Food{" + "name='" + name + '\'' + '}';
        }
    }

    public void printWine() {
        System.out.println("\n----------------------------------------");
        System.out.println(" " + name + "  |  " + variety + "  |  Vol." + alcohol_percentage + "%  |  " + price + "$ ");
        System.out.println("\n" + winery_name);
        System.out.println("\n     " + country + "  |  " + region + "  |  " + provenance);
        System.out.println("----------------------------------------");
        System.out.println(" Description : \n    " + description);
        System.out.println("----------------------------------------");
        System.out.println("\n\n Taste Info : \n     Acidity : " + acidity + "\n     Fizziness : " + fizziness + "\n     Intensity : " + intensity + "\n     Sweetness : " + sweetness + "\n     Tannin : " + tannin);
        System.out.println("\n Flavors : ");
        for (Flavor flavor : flavorList) {
            System.out.println("     " + flavor.getGroup() + " : " + flavor.getMentions_count());
        }
        System.out.println("\n Body : " + body_description);
        System.out.println("\n Suggested Pairings : ");
        for (Food food : foodList) {
            System.out.println("    " + food.getName());
        }
    }
    
    
    public static Wine_WineVivino createCLI() {
        Wine_WineVivino wine = new Wine_WineVivino();
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
        wine.setProvenance("V");
        System.out.print("Variety : ");
        wine.setVariety(sc.nextLine());
        System.out.print("Alcohol vol. % : ");
        wine.setAlcohol_percentage(sc.nextInt());
        System.out.print("Wine price[$] (int value) : ");
        wine.setPrice(sc.nextInt());
        System.out.print("Acidity : ");
        wine.setAcidity(sc.nextDouble());
        System.out.print("Fizziness : ");
        wine.setFizziness(sc.nextDouble());
        System.out.print("Intensity : ");
        wine.setIntensity(sc.nextDouble());
        System.out.print("Sweetness : ");
        wine.setSweetness(sc.nextDouble());
        System.out.print("Tannin : ");
        wine.setTannin(sc.nextDouble());
        
        System.out.print("Set new flavors [Issue \"stop\" to stop] : ");
        while(true){
            System.out.print("Enter flavor : ");
            String fl = sc.nextLine();
            if (fl.equals("stop")){
                break;
            }
            System.out.print("Enter value : ");
            int value = sc.nextInt();
            wine.flavorList.add(new Flavor(fl, value));
        }
        System.out.print("Body (int value) : ");
        wine.setBody(sc.nextInt());
        System.out.print("Body Description : ");
        wine.setBody_description(sc.nextLine());
        System.out.print("Set food pairings [Issue \"stop\" to stop] : ");
        while(true){
            System.out.print("Enter food : ");
            String fl = sc.nextLine();
            if (fl.equals("stop")){
                break;
            }
            wine.foodList.add(new Food(fl));
        }
        System.out.println("Registration completed!");
        return wine;
    }
}
