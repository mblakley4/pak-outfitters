package com.pakoutfitters.pak_outfitters.services;

import com.pakoutfitters.pak_outfitters.models.EquipmentModel;
import com.pakoutfitters.pak_outfitters.models.RentedEquipmentModel;
import org.xml.sax.SAXException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/pak_outfitters";
    String username = "root";
    String password = "auburn4!";
    Connection connection = null;
    static MySQLdb instance = null;

    public MySQLdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static synchronized MySQLdb getInstance() {
        if(instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }

    public List<EquipmentModel> getEquipment() throws SQLException {
        List<EquipmentModel> equipmentList = new ArrayList<>();

        String query = "SELECT e.id, e.title, e.description, e.price, e.type FROM equipment as e WHERE e.available = true";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            int equipment_id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            int price = rs.getInt("price");
            String type = rs.getString("type");

            EquipmentModel equipmentModel = new EquipmentModel(equipment_id, title, description, price, type);

            equipmentList.add(equipmentModel);
        }
        rs.close();
        preparedStatement.close();

        return equipmentList;
    }

    public List<RentedEquipmentModel> getRentedEquipment() throws SQLException {
        List<RentedEquipmentModel> rentedEquipmentList = new ArrayList<>();

        String query = "SELECT r.id as rental_id, r.equipment_id, e.title, e.description, e.type, (SELECT DATE_ADD(r.date_rented, INTERVAL r.days_rented DAY)) as return_date FROM rented_equipment r JOIN equipment e on r.equipment_id = e.id WHERE r.returned = false";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            int rental_id = rs.getInt("rental_id");
            int equipment_id = rs.getInt("equipment_id");
            String return_date = rs.getString("return_date");
            String title = rs.getString("title");
            String description = rs.getString("description");
            String type = rs.getString("type");

            RentedEquipmentModel rentedEquipmentModel = new RentedEquipmentModel(rental_id, equipment_id, return_date, title, description, type);

            rentedEquipmentList.add(rentedEquipmentModel);
        }
        rs.close();
        preparedStatement.close();

        return rentedEquipmentList;
    }
}
