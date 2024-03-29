package lk.ijse.cooperative.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cooperative.bo.BOFactory;
import lk.ijse.cooperative.bo.custom.DepositBo;
import lk.ijse.cooperative.bo.custom.DepositFormBo;
import lk.ijse.cooperative.entity.tm.DepositsTM;
import lk.ijse.cooperative.dao.custom.impl.DepositDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DepositFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblTopic;

    DepositFormBo depFormBo = (DepositFormBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.DEPFORM);

    @FXML
    void depositsBtnOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/deposits.fxml")));
        lblTopic.setText("Manage deposits");
    }

    @FXML
    void deposttransBtnOnaction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/deposit_trans.fxml")));
        lblTopic.setText("Withdraw a deposit");
    }

    @FXML
    void monthBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to update monthly deposits ?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                lblTopic.setText("Update monthly deposits");
                List<DepositsTM> data = depFormBo.getDeposits();
                int count = 0;
                for (DepositsTM dp : data) {
                    boolean isUpdated = depFormBo.updateDeposits(dp);
                    if (isUpdated) {
                        count++;
                    }
                }
                if (count == data.size()) {
                    new Alert(Alert.AlertType.CONFIRMATION, "All monthly deposits updated successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Someyhing went wrong!").show();
            }
        }
    }

    @FXML
    void yearBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to update annual interest ?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                lblTopic.setText("Update year interest");
                List<DepositsTM> data = depFormBo.getDeposits();
                int count = 0;
                for (DepositsTM dp : data) {
                    boolean isAdded = depFormBo.addYearInterest(dp);
                    if (isAdded) {
                        count++;
                    }
                }
                if (count == data.size()) {
                    new Alert(Alert.AlertType.CONFIRMATION, "All year interests updated successfully").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/deposits.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
