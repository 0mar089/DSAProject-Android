package com.example.loginregister.Swagger;

import java.io.Serializable;

public class ShopItem implements Serializable {
    private String name;
    private String description;
    private String url_icon;

    private int price;

    public ShopItem(String name, String description, String url_icon, int price) {
        this.name = name;
        this.description = description;
        this.url_icon = url_icon;
        this.price = price;
    }

    public ShopItem() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl_icon() { return url_icon; }
    public void setUrl_icon(String url_icon) { this.url_icon = url_icon; }
    public void setPrice(int price) {this.price = price;}
    public int getPrice() {return price;}
}
