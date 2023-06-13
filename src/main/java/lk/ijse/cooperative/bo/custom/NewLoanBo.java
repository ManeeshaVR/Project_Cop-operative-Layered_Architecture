package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.LoanDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.tm.LoanTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface NewLoanBo extends SuperBo {
    public LoanDTO searchLoan(String lId) throws SQLException;

    public boolean saveLoan(LoanDTO loan) throws SQLException;

    public boolean updateLoan(LoanDTO loan) throws SQLException;

    public List<Integer> getMemberNos() throws SQLException;

    public ObservableList<LoanTM> getAllLoans() throws SQLException;

    public String generateNextLoanId() throws SQLException;

    public boolean deleteLoan(String loanId) throws SQLException;

    public Double getLoanId() throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

}
