package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dao.custom.impl.LoanDAOImpl;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.Loan;
import lk.ijse.cooperative.dto.PayLoan;
import lk.ijse.cooperative.dto.tm.PayLoanTM;
import lk.ijse.cooperative.util.CrudUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PayLoanDAO extends CrudDAO<PayLoan, PayLoanTM, String, String> {
    public int completedInstallments(String id) throws SQLException;

    public boolean saveAndUpdate(PayLoan payLoan, boolean completed) throws SQLException;

    public boolean deleteAndUpdate(String dpLId, String lId, boolean completed) throws SQLException;

    public Integer getComIns(String loanId) throws SQLException;

    public boolean updateLoans(Loan ln) throws SQLException;

    public boolean updateLoans2(Loan ln) throws SQLException;

    public boolean updateAndCompleted(Loan ln, PayLoan payLoan) throws SQLException;

    @SneakyThrows
    public boolean insert(Loan loan);
}
