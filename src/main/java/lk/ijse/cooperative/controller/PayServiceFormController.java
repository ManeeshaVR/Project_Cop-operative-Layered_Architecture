package lk.ijse.cooperative.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.cooperative.bo.BOFactory;
import lk.ijse.cooperative.bo.custom.PayLoanBo;
import lk.ijse.cooperative.bo.custom.PayServiceBo;
import lk.ijse.cooperative.bo.custom.impl.PayServiceBoImpl;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.PayServiceDTO;
import lk.ijse.cooperative.dto.ServiceDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.PayService;
import lk.ijse.cooperative.entity.tm.PaySerTM;
import lk.ijse.cooperative.dao.custom.impl.AccountDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.InterestDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.ServiceDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.PaySerDAOImpl;
import lk.ijse.cooperative.util.RegEx;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PayServiceFormController implements Initializable {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPayAmount;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colServiceId;

    @FXML
    private DatePicker datePay;

    @FXML
    private TableView<PaySerTM> tblOtherSer;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtMemberNo;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtPayAmount;

    @FXML
    private JFXTextField txtPayId;

    @FXML
    private JFXComboBox<String> cmbServiceId;

    @FXML
    private JFXTextField txtType;

    PayServiceBo paySerBo = (PayServiceBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PAYSER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        populatePaySerTable();
        loadServiceIds();
        generateNextId();
    }

    private void setCellValues() {
        colServiceId.setCellValueFactory(new PropertyValueFactory<>("serId"));
        colPayId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPayAmount.setCellValueFactory(new PropertyValueFactory<>("payAmount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void populatePaySerTable() {
        try {
            ObservableList<PaySerTM> data = paySerBo.getAllPayServices();
            tblOtherSer.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateNextId() {
        try {
            String nextId = paySerBo.generateNextPayServiceId();
            txtPayId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadServiceIds() {
        try {
            ObservableList<String> data = paySerBo.getServiceIds();
            cmbServiceId.setItems(data);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (RegEx.getPaySerIdPattern().matcher(txtPayId.getText()).matches()) {
            txtPayId.setUnFocusColor(Paint.valueOf("#ffffff"));
            if (RegEx.getDoublePattern().matcher(txtPayAmount.getText()).matches()) {
                txtPayAmount.setUnFocusColor(Paint.valueOf("#09bff2"));
                String payId = txtPayId.getText();
                double amount = Double.parseDouble(txtAmount.getText());
                double payAmount = Double.parseDouble(txtPayAmount.getText());
                String serId = cmbServiceId.getValue();
                int memberNo = Integer.parseInt(txtMemberNo.getText());
                Date date = Date.valueOf(datePay.getValue());

                PayServiceDTO payService = new PayServiceDTO(payId, amount, payAmount, serId, date);

                try {
                    boolean isPaid = paySerBo.paidService(payService);
                    if (isPaid) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Pay Service Saved Successfully").show();
                        clearTextFields();
                        populatePaySerTable();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
                }
            }else {
                txtPayAmount.setUnFocusColor(Paint.valueOf("orange"));
                new Alert(Alert.AlertType.WARNING,"Invalid Pay Service Amount").show();
            }
        }else {
            txtPayId.setUnFocusColor(Paint.valueOf("orange"));
            new Alert(Alert.AlertType.WARNING,"Invalid Pay Service Id").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (RegEx.getPaySerIdPattern().matcher(txtPayId.getText()).matches()) {
            txtPayId.setUnFocusColor(Paint.valueOf("#09bff2"));
            if (RegEx.getDoublePattern().matcher(txtPayAmount.getText()).matches()) {
                txtPayAmount.setUnFocusColor(Paint.valueOf("#ffffff"));
                String payId = txtPayId.getText();
                double amount = Double.parseDouble(txtAmount.getText());
                double payAmount = Double.parseDouble(txtPayAmount.getText());
                String serId = cmbServiceId.getValue();
                int memberNo = Integer.parseInt(txtMemberNo.getText());
                Date date = Date.valueOf(datePay.getValue());

                PayServiceDTO payService = new PayServiceDTO(payId, amount, payAmount, serId, date);

                try {
                    boolean isUpdated = paySerBo.updatePayService(payService);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Pay Service Updated Successfully").show();
                        clearTextFields();
                        populatePaySerTable();
                    }else {
                        new Alert(Alert.AlertType.WARNING,"Pay service not updated").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
                }
            }else {
                txtPayAmount.setUnFocusColor(Paint.valueOf("orange"));
                new Alert(Alert.AlertType.WARNING,"Invalid Pay Service Amount").show();
            }
        }else {
            txtPayId.setUnFocusColor(Paint.valueOf("orange"));
            new Alert(Alert.AlertType.WARNING,"Invalid Pay Service Id").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove pay service ?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            String payId = txtPayId.getText();
            String serId = cmbServiceId.getValue();
            try {
                boolean isDeleted = paySerBo.deletePayService(payId, serId, false);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Pay Service Deleted Successfully").show();
                    clearTextFields();
                    populatePaySerTable();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Pay Service not deleted").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
            }
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFields();
    }

    private void clearTextFields() {
        txtPayId.setText("");
        cmbServiceId.setValue(null);
        txtAmount.setText("");
        txtMemberNo.setText("");
        txtNic.setText("");
        txtName.setText("");
        txtType.setText("");
        txtPayAmount.setText("");
        datePay.setValue(null);
        generateNextId();
    }

    @FXML
    void txtMemberNoOnAction(ActionEvent event) {

    }

    @FXML
    void txtPayAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtPayIdOnAction(ActionEvent event) {
        String payId = txtPayId.getText();
        try {
            PayServiceDTO payService = paySerBo.searchPayService(payId);
            if (payService!=null){
                ServiceDTO service = paySerBo.searchService(payService.getServiceId());
                AccountDTO account = paySerBo.searchAccount(service.getMemberNo());
                cmbServiceId.setValue(service.getSerId());
                txtType.setText(service.getType());
                txtAmount.setText(String.valueOf(service.getAmount()));
                txtPayAmount.setText(String.valueOf(payService.getPayAmount()));
                txtMemberNo.setText(String.valueOf(account.getMemberNo()));
                txtNic.setText(account.getNIC());
                txtName.setText(account.getName());
                String date = String.valueOf(payService.getDate());
                datePay.setValue(LocalDate.parse(date));
            }else {
                new Alert(Alert.AlertType.WARNING, "No Pay Service found").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbServiceIdOnAction(ActionEvent event) {
        String serId = cmbServiceId.getValue();
        try {
            ServiceDTO service = paySerBo.searchService(serId);
            AccountDTO account = paySerBo.searchAccount(service.getMemberNo());
            txtType.setText(service.getType());
            txtAmount.setText(String.valueOf(service.getAmount()));
            double payAmount = service.getAmount()+(service.getAmount()* paySerBo.getServiceInterest());
            txtPayAmount.setText(String.valueOf(payAmount));
            txtMemberNo.setText(String.valueOf(service.getMemberNo()));
            txtNic.setText(account.getNIC());
            txtName.setText(account.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void txtTypeOnAction(ActionEvent event) {

    }

    @FXML
    void btnReportsOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("D:\\My Projects\\project co-operative\\src\\main\\resources\\reports\\payService.jrxml");
        String sql = "SELECT * FROM manageotherservices";

        JRDesignQuery updateQuary = new JRDesignQuery();
        updateQuary.setText(sql);

        jasperDesign.setQuery(updateQuary);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }
}
