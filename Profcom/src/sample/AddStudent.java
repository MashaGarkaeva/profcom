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

public class AddStudent {
    Connection conn = new DbConnection().getConnection();

    @FXML
    public Button exit;
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public TextField numberAdd;
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
    public TableView<ListStudent> tableStudent;
    @FXML
    public TableColumn<ListStudent, String> number;
    @FXML
    public TableColumn<ListStudent, String> FIO;
    @FXML
    public TableColumn<ListStudent, String> groups;
    @FXML
    public TableColumn<ListStudent, String> birthday;
    @FXML
    public TableColumn<ListStudent, String> login;
    @FXML
    public TableColumn<ListStudent, String> password;
    @FXML
    public TableColumn<ListStudent, String> phone;

    public final ObservableList<ListStudent> studentList = FXCollections.observableArrayList();

    public AddStudent() throws SQLException, ClassNotFoundException {
    }

    public void initialize() throws SQLException {

        tableTresh();

        add.setOnAction(event -> {
            if((!numberAdd.getText().isEmpty() || !FIOAdd.getText().isEmpty() || !groupsAdd.getText().isEmpty() || !birthdayAdd.getText().isEmpty() ||
                    !loginAdd.getText().isEmpty() || !passwordAdd.getText().isEmpty() || !phoneAdd.getText().isEmpty())){
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement("INSERT Student (НомерЗачётнойКнижки, ФИО, КодГруппы, ДатаРождения, " +
                            "Логин, Пароль, НомерТелефона) VALUES (?,?,?,?,?,?,?)");

                    preparedStatement.setString(1, numberAdd.getText());
                    preparedStatement.setString(2, FIOAdd.getText());
                    preparedStatement.setString(3, groupsAdd.getText());
                    preparedStatement.setString(4, birthdayAdd.getText());
                    preparedStatement.setString(5, loginAdd.getText());
                    preparedStatement.setString(6, passwordAdd.getText());
                    preparedStatement.setString(7, phoneAdd.getText());


                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } tableTresh();
        });

        delete.setOnAction(event -> {
            if (!(numberAdd.getText().isEmpty())){
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM Student " +
                            "НомерЗачётнойКнижки AND ФИО AND КодГруппы AND ДеньРождения AND Логин AND" +
                            "Пароль AND НомерТелефона");

                    preparedStatement.setString(1, numberAdd.getText());
                    preparedStatement.setString(2, FIOAdd.getText());
                    preparedStatement.setString(3, groupsAdd.getText());
                    preparedStatement.setString(4, birthdayAdd.getText());
                    preparedStatement.setString(5, loginAdd.getText());
                    preparedStatement.setString(6, passwordAdd.getText());
                    preparedStatement.setString(7, phoneAdd.getText());

                }  catch (Exception e) {
                    System.out.println("Ошибка удаления");
                }
            } tableTresh();
        });

        exit.setOnAction(event -> {
            exit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Teacher.fxml"));

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        FIO.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        groups.setCellValueFactory(new PropertyValueFactory<>("groups"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        setStudentList();
    }

    private void setStudentList() throws SQLException {
        studentList.clear();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
        while (resultSet.next()){
            studentList.add(new ListStudent(resultSet.getString("НомерЗачётнойКнижки"),
                    resultSet.getString("ФИО"), resultSet.getString("КодГруппы"),
                    resultSet.getString("ДатаРождения"), resultSet.getString("Логин"),
                    resultSet.getString("Пароль"), resultSet.getString("НомерТелефона")));
        }

        tableStudent.setItems(studentList);
    }

    private void tableTresh() {
        studentList.clear();
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            if (!studentList.isEmpty()){
                tableStudent.setItems(studentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
