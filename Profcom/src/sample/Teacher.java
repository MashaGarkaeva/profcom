package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher {
    Connection conn = new DbConnection().getConnection();
    @FXML
    public Button exit;
    @FXML
    public Button allStudent;
    @FXML
    public TableView<ListGroup> tableGroup;
    @FXML
    public TableColumn<ListGroup, String> number;
    @FXML
    public TableColumn<ListGroup, String> people;
    @FXML
    public TableColumn<ListGroup, String> spec;
    @FXML
    public TableColumn<ListGroup, String> otdel;

    public final ObservableList<ListGroup> grouplist = FXCollections.observableArrayList();

    public Teacher() throws SQLException, ClassNotFoundException {
    }

    public void initialize() throws SQLException{

        number.setCellValueFactory(new PropertyValueFactory<>("number_group"));
        people.setCellValueFactory(new PropertyValueFactory<>("people_group"));
        spec.setCellValueFactory(new PropertyValueFactory<>("spec_group"));
        otdel.setCellValueFactory(new PropertyValueFactory<>("otdel_group"));
        setGrouplist();

        allStudent.setOnAction(event -> {
            allStudent.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addStudent.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("avtorization.fxml"));

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
    private void setGrouplist() throws SQLException {

        grouplist.clear();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Groups");
        while (resultSet.next()){
            grouplist.add(new ListGroup(resultSet.getString("КодГруппы"), resultSet.getString("КоличествоЧеловек"),
                    resultSet.getString("НазваниеСпециальности"), resultSet.getString("Отделение")));
        }

        tableGroup.setItems(grouplist);
    }
}
