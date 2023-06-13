package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.LoanDTO;
import lk.ijse.cooperative.dto.PayLoanDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.PayLoan;
import lk.ijse.cooperative.entity.tm.PayLoanTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PayLoanBo extends SuperBo {

    public PayLoanDTO searchPayLoan(String payId) throws SQLException;

    public ObservableList<PayLoanTM> getAllPayLoans() throws SQLException;

    //public PayLoan search2(String loanId) throws SQLException;

    public String generateNextPayLoanId() throws SQLException;

    public int completedInstallments(String id) throws SQLException;

    public boolean saveAndUpdate(PayLoanDTO payLoan, boolean completed) throws SQLException;

    public boolean deleteAndUpdate(String dpLId, String lId, boolean completed) throws SQLException;

    public LoanDTO searchLoan(String lId) throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

    public List<String> getLoanIds() throws SQLException;
}
