package lk.ijse.cooperative.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.cooperative.bo.BOFactory;
import lk.ijse.cooperative.bo.custom.SupplierBo;
import lk.ijse.cooperative.bo.custom.SuppliesBo;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.ItemDTO;
import lk.ijse.cooperative.dto.SupplierDTO;
import lk.ijse.cooperative.dto.SuppliesDTO;
import lk.ijse.cooperative.entity.Item;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.Supplies;
import lk.ijse.cooperative.entity.tm.SuppliesTM;
import lk.ijse.cooperative.dao.custom.impl.ItemDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.OrderDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.SuppliesDAOImpl;
import lk.ijse.cooperative.util.RegEx;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuppliesFormController implements Initializable {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUniPrice;

    @FXML
    private TableColumn<?, ?> colRemQty;

    @FXML
    private DatePicker dateOrder;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<SuppliesTM> tblOrderCart;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtUnitPrice;

    SuppliesBo suppliesBo = (SuppliesBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.SUPPLIES);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        populateSuppliesTable();
        loadSupplierIds();
        loadItemIds();
        generateNextOrderId();
        dateOrder.setValue(LocalDate.now());
    }

    private void setCellValues() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUniPrice.setCellValueFactory(new PropertyValueFactory<>("uniPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    }

    private void populateSuppliesTable() {
        try {
            ObservableList<SuppliesTM> data = suppliesBo.getAllSupplies();
            tblOrderCart.setItems(data);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    private void loadItemIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = suppliesBo.getItemIds();

            for (String code : codes) {
                obList.add(code);
            }
            cmbItemId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadSupplierIds() {
        try {
            List<String> ids = suppliesBo.getSupplierIds();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String id : ids) {
                obList.add(id);
            }
            cmbSupplierId.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void generateNextOrderId() {
        try {
            String nextId = suppliesBo.generateNextOrderId();
            txtOrderId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove order ?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            String orderId = txtOrderId.getText();
            int qty = Integer.parseInt(txtQty.getText());
            String itemId = cmbItemId.getValue();
            try {
                boolean isDeleted = suppliesBo.deleteAndUpdateSupplies(orderId, qty, itemId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Deleted Successfully").show();
                    clearFields();
                    populateSuppliesTable();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Order not deleted").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
            }
        }
    }

    @FXML
    void btnNewSupplierOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml")));
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (RegEx.getOrderIdPattern().matcher(txtOrderId.getText()).matches()) {
            txtOrderId.setUnFocusColor(Paint.valueOf("#09bff2"));
            if (RegEx.getIntPattern().matcher(txtQty.getText()).matches()) {
                txtQty.setUnFocusColor(Paint.valueOf("#09bff2"));
                String orderId = txtOrderId.getText();
                String supId = cmbSupplierId.getValue();
                String supName = txtSupplierName.getText();
                String itemId = cmbItemId.getValue();
                String itemName = txtItemName.getText();
                int qty = Integer.parseInt(txtQty.getText());
                double uniPrice = Double.parseDouble(txtUnitPrice.getText());
                double amount = qty * uniPrice;
                Date date = java.sql.Date.valueOf(dateOrder.getValue());
                int remQty = qty;

                SuppliesDTO supplies = new SuppliesDTO(orderId, supId, supName, itemId, itemName, qty, uniPrice, amount, date);

                try {
                    boolean isSaved = suppliesBo.saveAndUpdateSupplies(supplies);
                    if (isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION, "Order Saved Successfully").show();
                        clearFields();
                        populateSuppliesTable();
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Order not saved").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
                }
            }else {
                txtQty.setUnFocusColor(Paint.valueOf("orange"));
                new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
            }
        }else {
            txtOrderId.setUnFocusColor(Paint.valueOf("orange"));
            new Alert(Alert.AlertType.WARNING,"Invalid Order Id").show();
        }

    }

    private void clearFields() {
        generateNextOrderId();
        dateOrder.setValue(LocalDate.now());
        cmbSupplierId.setValue(null);
        txtSupplierName.setText("");
        cmbItemId.setValue(null);
        txtItemName.setText("");
        txtQty.setText("");
        txtType.setText("");
        txtUnitPrice.setText("");
        generateNextOrderId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String supId = cmbSupplierId.getValue();
        String supName = txtSupplierName.getText();
        String itemId = cmbItemId.getValue();
        String itemName = txtItemName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double uniPrice = Double.parseDouble(txtUnitPrice.getText());
        double amount = qty * uniPrice;
        Date date = java.sql.Date.valueOf(dateOrder.getValue());
        int remQty = qty;

        SuppliesDTO supplies = new SuppliesDTO(orderId, supId, supName, itemId, itemName, qty, uniPrice, amount, date);

        try {
            boolean isUpdated = suppliesBo.updateSupplies(supplies);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Updated Successfully").show();
                clearFields();
                populateSuppliesTable();
            }else {
                new Alert(Alert.AlertType.WARNING, "Order not updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


    @FXML
    void cmbItemOnAction(ActionEvent event) {
        if( cmbItemId.getValue() == null) {
            return;
        }
        String itemId = cmbItemId.getValue();
        try {
            ItemDTO item = suppliesBo.searchItem(itemId);
            txtItemName.setText(item.getName());
            txtType.setText(item.getType());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQty.requestFocus();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void cmbSupplierIdrOnAction(ActionEvent event) {
        if( cmbSupplierId.getValue() == null) {
            return;
        }
        String supId = cmbSupplierId.getValue();
        try {
            SupplierDTO supplier = suppliesBo.searchSupplier(supId);
                txtSupplierName.setText(supplier.getName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void txtOrderIdOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        try {
            SuppliesDTO supplies = suppliesBo.searchSupplies(orderId);
            if (supplies!=null){
                String date = String.valueOf(supplies.getDate());
                dateOrder.setValue(LocalDate.parse(date));
                cmbSupplierId.setValue(supplies.getSupId());
                txtSupplierName.setText(supplies.getSupName());
                ItemDTO item = suppliesBo.searchItem(supplies.getItemId());
                cmbItemId.setValue(supplies.getItemId());
                txtItemName.setText(supplies.getItemName());
                txtType.setText(item.getType());
                txtUnitPrice.setText(String.valueOf(supplies.getUniPrice()));
                txtQty.setText(String.valueOf(supplies.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("D:\\My Projects\\project co-operative\\src\\main\\resources\\reports\\orders.jrxml");
        String sql = "SELECT * FROM managesupplies";

        JRDesignQuery updateQuary = new JRDesignQuery();
        updateQuary.setText(sql);

        jasperDesign.setQuery(updateQuary);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

}
