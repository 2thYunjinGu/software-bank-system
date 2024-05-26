package Model;

// Model.java
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String name;
    private double price;
    private String description;
    private List<Shop> modelList;

    public Shop() {
        this.modelList = new ArrayList<>();
    }

    // Getter and setter methods for name, price, and description
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Method to add a model to the list
    public void addModel(Shop model) {
        modelList.add(model);
    }

    // Method to get the list of models
    public List<Shop> getModelList() {
        return modelList;
    }

    // Override toString() method to return model details
    @Override
    public String toString() {
        return name + ", Price: " + price + ", Description: " + description;
    }
}











