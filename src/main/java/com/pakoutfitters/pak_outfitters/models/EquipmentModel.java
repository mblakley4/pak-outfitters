package com.pakoutfitters.pak_outfitters.models;

public class EquipmentModel {

    int equipment_id;
    String title;
    String description;
    int price;
    Boolean available;
    String type;

    public EquipmentModel(int equipment_id, String title, String description, int price, Boolean available, String type) {
        this.equipment_id = equipment_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.available = available;
        this.type = type;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
