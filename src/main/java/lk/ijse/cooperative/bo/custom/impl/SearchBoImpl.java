package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.SearchBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.*;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.Service;

import java.sql.SQLException;

public class SearchBoImpl implements SearchBo {

    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);
    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    InterestDAO interestDAO = (InterestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INTEREST);

    @Override
    public String getEmail(int text) throws SQLException {
        return accountDAO.getEmail(text);
    }

    @Override
    public Account searchAccount(Integer memberNo) throws SQLException {
        return accountDAO.search(memberNo);
    }

    @Override
    public Deposit searchDeposit(Integer memberNo) throws SQLException {
        return depositDAO.search2(memberNo);
    }

    @Override
    public Loan searchLoan(Integer memberNo) throws SQLException {
        return loanDAO.search2(memberNo);
    }

    @Override
    public Service searchService(Integer memberNo) throws SQLException {
        return serviceDAO.search2(memberNo);
    }

    @Override
    public Double getServiceInterest() throws SQLException {
        return interestDAO.getServiceId();
    }
}
