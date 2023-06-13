package lk.ijse.cooperative.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import lk.ijse.cooperative.bo.BOFactory;
import lk.ijse.cooperative.bo.custom.DepositFormBo;
import lk.ijse.cooperative.bo.custom.LoanFormBo;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.dao.custom.impl.LoanDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.PayLoanDAOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoanFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblTopic;

    LoanFormBo loanFormBo = (LoanFormBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LOANFORM);

    @FXML
    void newLoanBtnOnAction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/new_loan.fxml")));
        lblTopic.setText("Get a new Loan");
    }

    @FXML
    void payLoanBtnOnaction(ActionEvent event) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/pay_loan.fxml")));
        lblTopic.setText("Pay the loan");
    }

    @FXML
    void payAllBtnOnaction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to update monthly loan amounts ?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            try {
                lblTopic.setText("Update monthly loan amounts");
                List<Loan> data = loanFormBo.getLoans();
                int count = 0;
                for (Loan ln : data) {
                    boolean isUpdated = loanFormBo.updateLoans(ln);
                    if (isUpdated) {
                        count++;
                    }
                }
                if (count == data.size()) {
                    new Alert(Alert.AlertType.CONFIRMATION, "All monthly loans updated successfully").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Someyhing went wrong!").show();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/new_loan.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
