package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.Service;

import java.sql.SQLException;

public interface SearchBo extends SuperBo {

    public String getEmail(int text) throws SQLException;

    public Account searchAccount(Integer memberNo) throws SQLException;

    public Deposit searchDeposit(Integer memberNo) throws SQLException;

    public Loan searchLoan(Integer memberNo) throws SQLException;

    public Service searchService(Integer memberNo) throws SQLException;

    public Double getServiceInterest() throws SQLException;

}
