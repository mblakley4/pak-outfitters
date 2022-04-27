package com.pakoutfitters.pak_outfitters.models;

public class RentedEquipmentModel {

    int rental_id;
    int equipment_id;
    String return_date;
    String title;
    String description;
    String type;

    public RentedEquipmentModel(int rental_id, int equipment_id, String return_date, String title, String description, String type) {
        this.rental_id = rental_id;
        this.equipment_id = equipment_id;
        this.return_date = return_date;
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String date_rented) {
        this.return_date = date_rented;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
