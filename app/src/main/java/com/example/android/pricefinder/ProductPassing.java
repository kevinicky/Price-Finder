package com.example.android.pricefinder;

import com.example.android.pricefinder.model.Product;

import java.util.ArrayList;

public class ProductPassing {
    private static ProductPassing instance;
    private ArrayList<Product> passingProductsList;

    public static ProductPassing getInstance(){
        if (instance == null){
            instance = new ProductPassing();
        }
        return instance;
    }

    public ArrayList<Product> getPassingProductsList() {
        return passingProductsList;
    }

    public void setPassingProductsList(ArrayList<Product> passingProductsList) {
        this.passingProductsList = passingProductsList;
    }
}
