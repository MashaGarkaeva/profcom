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

public class List_Teacher {
    Connection conn = new DbConnection().getConnection();

    @FXML
    public Button exit;
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public TextField FIOAdd;
    @FXML
    public TextField loginAdd;
    @FXML
    public TextField passwordAdd;
    @FXML
    public TextField birthdayAdd;
    @FXML
    public TextField phoneAdd;
    @FXML
    public TextField groupsAdd;
    @FXML
    public TableView<ListTeacher> tableTeacher;
    @FXML
    public TableColumn<ListTeacher, String> FIO;
    @FXML
    public TableColumn<ListTeacher, String> login;
    @FXML
    public TableColumn<ListTeacher, String> pass;
    @FXML
    public TableColumn<ListTeacher, Date> birthday;
    @FXML
    public TableColumn<ListTeacher, String> phone;
    @FXML
    public TableColumn<ListTeacher, String> groups;

    public final ObservableList<ListTeacher> teacherlist = FXCollections.observableArrayList();

    public List_Teacher() throws SQLException, ClassNotFoundException {
    }

    public void initialize() throws SQLException {

        tableTresh();

        add.setOnAction(event -> {
            if((!FIOAdd.getText().isEmpty() || !loginAdd.getText().isEmpty() || !passwordAdd.getText().isEmpty()
            || !birthdayAdd.getText().isEmpty() || phoneAdd.getText().isEmpty() || !groupsAdd.getText().isEmpty())){
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement("INSERT Teacher (ФИО, Логин, " +
                            "Пароль, ДатаРождения, НомерТелефона, НомерГруппы) VALUES (?,?,?,?,?,?)");

                    preparedStatement.setString(1, FIOAdd.getText());
                    preparedStatement.setString(2, loginAdd.getText());
                    preparedStatement.setString(3, passwordAdd.getText());
                    preparedStatement.setString(4, birthdayAdd.getText());
                    preparedStatement.setString(5, phoneAdd.getText());
                    preparedStatement.setString(6, groupsAdd.getText());

                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } tableTresh();
        });

        delete.setOnAction(event -> {
            if (!(FIOAdd.getText().isEmpty())){
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Teacher ФИО AND Логин AND" +
                            "Пароль AND ДеньРождения AND НомерТелефона AND НомерГруппы");

                    preparedStatement.setString(1, FIOAdd.getText());
                    preparedStatement.setString(2, loginAdd.getText());
                    preparedStatement.setString(3, passwordAdd.getText());
                    preparedStatement.setString(4, birthdayAdd.getText());
                    preparedStatement.setString(5, phoneAdd.getText());
                    preparedStatement.setString(6, groupsAdd.getText());

                }  catch (Exception e) {
                    System.out.println("Ошибка удаления");
                }
            } tableTresh();
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

        FIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        pass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        groups.setCellValueFactory(new PropertyValueFactory<>("groups"));

        setTeacherList();
}

    private void setTeacherList() throws SQLException {
        teacherlist.clear();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Teacher");
        while (resultSet.next()){
            teacherlist.add(new ListTeacher(resultSet.getString("ФИО"), resultSet.getString("Логин"),
                    resultSet.getString("Пароль"), resultSet.getString("ДатаРождения"),
                    resultSet.getString("НомерТелефона"), resultSet.getString("НомерГруппы")));
        }

        tableTeacher.setItems(teacherlist);
    }

    private void tableTresh() {
        teacherlist.clear();
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Teacher");
            if (!teacherlist.isEmpty()){
                tableTeacher.setItems(teacherlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}