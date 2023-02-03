package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    Connection conn = new DbConnection().getConnection();
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    public Button btn;
    @FXML
    public Label error;
    @FXML
    public ComboBox<String> role;
    ObservableList<String> rolelist = FXCollections.observableArrayList();

    public Controller() throws SQLException, ClassNotFoundException {
    }
    @FXML
    public void initialize() throws SQLException {
        rolelist.add("Admin");
        rolelist.add("Teacher");
        role.setItems(rolelist);

        PreparedStatement preparedStatement = conn.prepareStatement("");
        try{
            switch (role.getValue().trim()) {
                case "Admin":
                    preparedStatement = conn.prepareStatement("SELECT * FROM Admin WHERE Логин = ? AND Пароль = ?");
                    break;
                case "Teacher":
                    preparedStatement = conn.prepareStatement("SELECT * FROM Teacher WHERE Логин = ? AND Пароль = ?");
                    break;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Данные введены неверное, проверьте и зайдите снова"));
        }

        try {
            preparedStatement.setString(1, email.getText().trim());
            preparedStatement.setString(2, password.getText().trim());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                try {
                     Parent root;
                     root =  FXMLLoader.load(getClass().getResource(role.getValue().trim() + ".fxml"));
                     Stage primaryStage = new Stage();
                     primaryStage.setScene(new Scene(root));
                     primaryStage.show();
                } catch (IOException e) {
                     e.printStackTrace();
                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Некорректно заполнены поля"));
            alert.showAndWait();
        }
        btn.setOnAction(event -> {
            btn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Admin.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
