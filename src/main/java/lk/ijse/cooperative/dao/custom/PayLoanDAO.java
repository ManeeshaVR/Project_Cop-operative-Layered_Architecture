package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.PayLoan;
import lk.ijse.cooperative.entity.tm.PayLoanTM;
import lombok.SneakyThrows;

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
