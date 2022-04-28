package com.pakoutfitters.pak_outfitters.services;

import com.pakoutfitters.pak_outfitters.models.AdminModel;
import com.pakoutfitters.pak_outfitters.models.EquipmentModel;
import com.pakoutfitters.pak_outfitters.models.RentedEquipmentModel;

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

    public AdminModel login (String userName, String password) throws SQLException {
        AdminModel adminModel = null;

        String query = "SELECT user_name FROM admin WHERE user_name = '"+ userName +"' AND password = '"+ password +"'";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery(query);

        if(rs.next()) {
            String username = rs.getString("user_name");
            adminModel = new AdminModel(username);
        }
        rs.close();
        preparedStatement.close();
        return adminModel;
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

            EquipmentModel equipmentModel = new EquipmentModel(equipment_id, title, description, price, type, null);

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

    public EquipmentModel getItem(int equipmentId) throws SQLException {
        EquipmentModel item = null;

        String query = "SELECT * FROM equipment WHERE id = '"+equipmentId+"'";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            int equipment_id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            int price = rs.getInt("price");
            String type = rs.getString("type");
            Boolean available = rs.getBoolean("available");

            item = new EquipmentModel(equipment_id, title, description, price, type, available);
        }
        rs.close();
        preparedStatement.close();

        return item;
    }

    public boolean rentItem(int equipmentId, int rentalDays, String dateRented) throws SQLException {
        boolean result = false;

        String updateQuery = "UPDATE equipment e SET e.available = 0 WHERE id = '"+equipmentId+"'";

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        int rows_update = preparedStatement.executeUpdate();

        if (rows_update == 1) {
            result = true;
        }
        preparedStatement.close();

        String qRentItem = "INSERT INTO rented_equipment (equipment_id, date_rented, days_rented, returned) VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement1 = connection.prepareStatement(qRentItem);
        preparedStatement1.setInt(1, equipmentId);
        preparedStatement1.setString(2, dateRented);
        preparedStatement1.setInt(3, rentalDays);
        preparedStatement1.setBoolean(4, false);

        int rows_update1 = preparedStatement1.executeUpdate();

        if (rows_update1 != 1) {
            result = false;
        }
        preparedStatement.close();

        return result;
    }

    public boolean returnItem(int rentalId, String dateReturned) throws SQLException {
        boolean result = false;
        int equipmentId = 0;

        String query = "SELECT r.equipment_id FROM rented_equipment r WHERE id = '"+rentalId+"'";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery(query);

        if(rs.next()) {
            equipmentId = rs.getInt("equipment_id");
        }
        rs.close();
        preparedStatement.close();

        String updateEquipment = "UPDATE equipment e SET e.available = 1 WHERE id = '"+equipmentId+"'";

        PreparedStatement preparedStatement1 = connection.prepareStatement(updateEquipment);
        int rows_update1 = preparedStatement1.executeUpdate();

        if (rows_update1 == 1) {
            result = true;
        }
        preparedStatement1.close();

        String updateRentals = "UPDATE rented_equipment r SET r.date_returned = '"+dateReturned+"', r.returned = true WHERE id = '"+rentalId+"'";

        PreparedStatement preparedStatement2 = connection.prepareStatement(updateRentals);
        int rows_update2 = preparedStatement2.executeUpdate();

        if (rows_update2 != 1) {
            result = false;
        }
        preparedStatement2.close();

        return result;
    }
}
