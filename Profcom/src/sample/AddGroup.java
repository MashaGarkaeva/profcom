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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddGroup {

    Connection conn = new DbConnection().getConnection();

    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public Button exit;
    @FXML
    public TextField numberAdd;
    @FXML
    public TextField peopleAdd;
    @FXML
    public TextField specAdd;
    @FXML
    public TextField otdelAdd;
    @FXML
    public TableView<ListGroup> tableAddGroup;
    @FXML
    public TableColumn<ListGroup, String> number;
    @FXML
    public TableColumn<ListGroup, String> people;
    @FXML
    public TableColumn<ListGroup, String> spec;
    @FXML
    public TableColumn<ListGroup, String> otdel;

    public final ObservableList<ListGroup> grouplist = FXCollections.observableArrayList();

    public AddGroup() throws SQLException, ClassNotFoundException {
    }


    public void initialize() throws SQLException {
        number.setCellValueFactory(new PropertyValueFactory<>("number_group"));
        people.setCellValueFactory(new PropertyValueFactory<>("people_group"));
        spec.setCellValueFactory(new PropertyValueFactory<>("spec_group"));
        otdel.setCellValueFactory(new PropertyValueFactory<>("otdel_group"));

        tableTresh();

        add.setOnAction(event -> {
            if((!numberAdd.getText().isEmpty() || !peopleAdd.getText().isEmpty() ||
                    !specAdd.getText().isEmpty() || !otdelAdd.getText().isEmpty())) {
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement("INSERT Groups (КодГруппы, КоличествоЧеловек, " +
                            "НазваниеСпециальности, Отделение) VALUES (?,?,?,?)");

                    preparedStatement.setString(1, numberAdd.getText());
                    preparedStatement.setString(2, peopleAdd.getText());
                    preparedStatement.setString(3, specAdd.getText());
                    preparedStatement.setString(4, otdelAdd.getText());

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            tableTresh();
        });

        delete.setOnAction(event -> {
            if(!(numberAdd.getText().isEmpty())){
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Groups КодГруппы AND " +
                            "КоличествоЧеловек AND НазваниеСпециальности AND Отделение");

                    preparedStatement.setString(1, numberAdd.getText());
                    preparedStatement.setString(2, peopleAdd.getText());
                    preparedStatement.setString(3, specAdd.getText());
                    preparedStatement.setString(4, otdelAdd.getText());

                } catch (Exception e) {
                System.out.println("Ошибка удаления");
            }
            }
            tableTresh();
        });

        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();

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


        number.setCellValueFactory(new PropertyValueFactory<>("number_group"));
        people.setCellValueFactory(new PropertyValueFactory<>("people_group"));
        spec.setCellValueFactory(new PropertyValueFactory<>("spec_group"));
        otdel.setCellValueFactory(new PropertyValueFactory<>("otdel_group"));
        setGrouplist();

    }

    private void setGrouplist() throws SQLException {

        grouplist.clear();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Groups");
        while (resultSet.next()){
            grouplist.add(new ListGroup(resultSet.getString("КодГруппы"), resultSet.getString("КоличествоЧеловек"),
                    resultSet.getString("НазваниеСпециальности"), resultSet.getString("Отделение")));
        }

        tableAddGroup.setItems(grouplist);

    }

    private void tableTresh() {
        grouplist.clear();
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Groups");
            if (!grouplist.isEmpty()){
                tableAddGroup.setItems(grouplist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
