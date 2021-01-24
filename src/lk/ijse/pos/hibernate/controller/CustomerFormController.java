package lk.ijse.pos.hibernate.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pos.hibernate.business.BOFactory;
import lk.ijse.pos.hibernate.business.BOType;
import lk.ijse.pos.hibernate.business.custom.impl.CustomerBOImpl;
import lk.ijse.pos.hibernate.dto.CustomerDTO;
import lk.ijse.pos.hibernate.view.tm.CustomerTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public TableView <CustomerTM> tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colOperate;
    public TableColumn colSalary;
    public AnchorPane root;

    CustomerBOImpl customerBOImpl = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void initialize(){
        findAll();
        tableListener();
        setCellValueFactory();
    }

    private void tableListener(){
        tblCustomer.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void setData(CustomerTM tm) {
        try {
            txtId.setText(tm.getId());
            txtName.setText(tm.getName());
            txtAddress.setText(tm.getAddress());
            txtSalary.setText(String.valueOf(tm.getSalary()));
        }catch (Exception e){

        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("Id"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colSalary.setCellValueFactory(new PropertyValueFactory("Salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));
    }

    public void findAll(){
        try {
            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            List<CustomerDTO> all = customerBOImpl.findAll();
            for(CustomerDTO dto : all){
                Button btn = new Button("Delete");
                CustomerTM tm = new CustomerTM(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getSalary(),
                        btn
                );
                tmList.add(tm);
                btn.setOnAction((e) -> {
                    try {

                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (customerBOImpl.delete(tm.getId())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                findAll();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {

                        }


                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblCustomer.setItems(tmList);
        } catch (Exception e) {

        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try {
            if (customerBOImpl.add(new CustomerDTO(
                    id,
                    name,
                    address,
                    salary
            )));{
                new Alert(Alert.AlertType.CONFIRMATION, "Do you wanna Save it?").showAndWait();
                txtId.setText(null);
                txtName.setText(null);
                txtSalary.setText(null);
                txtAddress.setText(null);
                findAll();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        try {
            if(customerBOImpl.update(new CustomerDTO(
                    id,
                    name,
                    address,
                    salary
            ))){
                //new Alert(Alert.AlertType.CONFIRMATION, "Updated").showAndWait();
                findAll();
                txtId.setText(null);
                txtName.setText(null);
                txtAddress.setText(null);
                txtSalary.setText(null);
            }else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml")));
    }
}
