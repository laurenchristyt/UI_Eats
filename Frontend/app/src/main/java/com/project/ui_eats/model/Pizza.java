package com.project.ui_eats.model;

public class Pizza{
    @Override
    public String toString() {
        return "Pizza{" +
                "topping=" + topping +
                "crust=" + crust +
                '}';
    }

    public Topping topping;
    public Crust crust;

}
