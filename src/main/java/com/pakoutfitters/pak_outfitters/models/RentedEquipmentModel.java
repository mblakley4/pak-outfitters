package com.pakoutfitters.pak_outfitters.models;

public class RentedEquipmentModel {

    int rental_id;
    int equipment_id;
    String date_rented;
    String days_rented;
    String date_returned;
    Boolean returned;

    public RentedEquipmentModel(
            int rental_id,
            int equipment_id,
            String date_rented,
            String days_rented,
            String date_returned,
            Boolean returned)
    {
        this.rental_id = rental_id;
        this.equipment_id = equipment_id;
        this.date_rented = date_rented;
        this.days_rented = days_rented;
        this.date_returned = date_returned;
        this.returned = returned;
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

    public String getDate_rented() {
        return date_rented;
    }

    public void setDate_rented(String date_rented) {
        this.date_rented = date_rented;
    }

    public String getDays_rented() {
        return days_rented;
    }

    public void setDays_rented(String days_rented) {
        this.days_rented = days_rented;
    }

    public String getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(String date_returned) {
        this.date_returned = date_returned;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}
