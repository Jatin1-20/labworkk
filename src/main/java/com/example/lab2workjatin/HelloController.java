package com.example.lab2workjatin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {

    public TextField ename;
    public TextField esalary;
    public TextField eaddress;
    public TextField eage;
    public TextField eid;
    public Label user_name;
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee,Integer > id;
    @FXML
    private TableColumn<Employee, String> employee_name;
    @FXML
    private TableColumn<Employee,Integer> salary;
    @FXML
    private TableColumn<Employee,String> address;
    @FXML
    private TableColumn<Employee,Integer> age;
    ObservableList<Employee> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("id"));
        employee_name.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("employee_name"));
        salary.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("Salary"));
        address.setCellValueFactory(new
                PropertyValueFactory<Employee,String>("Address"));
        age.setCellValueFactory(new
                PropertyValueFactory<Employee,Integer>("Age"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {

        list.clear();


// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_employee`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                int salary = resultSet.getInt("salary");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");
                tableView.getItems().add(new Employee(id, employee_name, salary,
                        address,age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {

        String Ename =ename.getText();
        String Esalary =esalary.getText();
        String Eaddress =eaddress.getText();
        String Eage =eage.getText();

        InserTable(Ename,Esalary,Eaddress,Eage);
    }


    public void InserTable(String ename,String esalary,String eaddress,String eage) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `tbl_employee`( `employee_name`, `salary`, `address`, `age`) VALUES ('"+ename+"','"+esalary+"','"+eaddress+"','"+eage+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteData(ActionEvent actionEvent) {


        String Eid=eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `tbl_employee` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateData(ActionEvent actionEvent) {

        String Eid =eid.getText();
        String Ename =ename.getText();
        String Esalary =esalary.getText();
        String Eaddress =eaddress.getText();
        String Eage =eage.getText();



        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `tbl_employee` SET `employee_name`='"+Ename+"',`salary`='"+Esalary+"',`address`='"+Eaddress+"',`age`='"+Eage+"' WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {


        String Eid =eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/db_csd214";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_employee` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                int salary = resultSet.getInt("salary");
                String address = resultSet.getString("address");
                int age = resultSet.getInt("age");


                ename.setText(employee_name);
                esalary.setText(String.valueOf(salary));
                eaddress.setText(address);
                eage.setText(String.valueOf(age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void set_username(String messge){
        user_name.setText(messge);
    }
}