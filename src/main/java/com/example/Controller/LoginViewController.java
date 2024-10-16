package com.example.Controller;

import com.example.Database.DatabaseLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginViewController {

    @FXML
    private Label thongBaoDangNhap;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    public void loginButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() && !passwordField.getText().isBlank()) {
            validateLogin();
        } else {
            thongBaoDangNhap.setText("Vui lòng nhập tài khoản và mật khẩu");
        }
    }

    public void validateLogin() {
        DatabaseLogin connectNow = new DatabaseLogin();
        Connection connectDB = connectNow.getConnection(); // Lấy kết nối từ DatabaseConnection

        String verifyLogin = "SELECT count(1) FROM userAccounts WHERE username = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordField.getText());

            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next() && queryResult.getInt(1) == 1) {
                switchTodatHang();
            } else {
                thongBaoDangNhap.setText("Đăng nhập không hợp lệ. Hãy đăng nhập lại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void switchTodatHang() {
        try {
            // Tải file FXML cho danh sách sản phẩm
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/DatHang.fxml"));
            Parent root = loader.load();

            // Lấy Stage hiện tại (cửa sổ hiện tại)
            Stage stage = (Stage) thongBaoDangNhap.getScene().getWindow();

            // Tạo Scene mới từ FXML và thiết lập nó
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



