package com.example.DAO;

import com.example.Database.DatabaseListSP;
import com.example.Model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final String url = "jdbc:mysql://localhost:3306/listSanPham";
    private final String user = "root";  // Đặt tên đăng nhập MySQL của bạn
    private final String password = "123456a@";  // Đặt mật khẩu MySQL của bạn

    public List<Products> getAllProducts() {
        List<Products> productList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                Products product = new Products(id, name, price, description);
                productList.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Products getProductById(int productId) throws SQLException {
        Products product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DatabaseListSP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                product = new Products(productId, name, price, description);
            }
        }

        return product;
    }
}
