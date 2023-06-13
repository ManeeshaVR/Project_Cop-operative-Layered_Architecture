package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.entity.Loan;

import java.sql.SQLException;
import java.util.List;

public interface LoanFormBo extends SuperBo {
    public List<Loan> getLoans() throws SQLException;

    public boolean updateLoans(Loan ln) throws SQLException;
}
