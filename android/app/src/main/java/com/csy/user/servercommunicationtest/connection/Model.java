package com.csy.user.servercommunicationtest.connection;

public class Model {

    private String menu;
    private String category;
    private String restaurant;

    public Model(String menu, String category, String restaurant) {
        this.menu = menu;
        this.category = category;
        this.restaurant = restaurant;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}

