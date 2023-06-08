package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dao.custom.impl.PayLoanDAOImpl;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.Loan;
import lk.ijse.cooperative.dto.tm.LoanTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LoanDAO extends CrudDAO<Loan, LoanTM, String, Integer> {
    public int getLoanAmount() throws SQLException;

    public int getCount() throws SQLException;

    public boolean updateCompleted(boolean completed, String lId) throws SQLException;

    public List<Loan> getLoans() throws SQLException;

    public boolean saveAndInsert(Loan loan) throws SQLException;
}
