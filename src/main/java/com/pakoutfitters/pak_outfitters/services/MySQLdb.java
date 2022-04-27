package com.pakoutfitters.pak_outfitters.services;

import com.pakoutfitters.pak_outfitters.models.EquipmentModel;

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

    public List<EquipmentModel> getEquipment() throws SQLException{
        List<EquipmentModel> equipmentList = new ArrayList<>();

        String query = "SELECT E.id, E.title, E.description, E.price, E.type FROM equipment as E WHERE E.available = true";

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
}
