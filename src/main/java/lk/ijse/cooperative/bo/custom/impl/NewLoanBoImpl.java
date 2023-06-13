package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.InterestDAO;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.MemberDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.LoanDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.tm.LoanTM;

import java.sql.SQLException;
import java.util.List;

public class NewLoanBoImpl implements NewLoanBo{

    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);
    InterestDAO interestDAO = (InterestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INTEREST);

    @Override
    public LoanDTO searchLoan(String lId) throws SQLException {
        Loan loan = loanDAO.search(lId);
        return new LoanDTO(loan.getLoanId(), loan.getInterest(), loan.getLoanAmount(), loan.getInstallments(), loan.getFirInsAmount(), loan.getInsAmount(), loan.getDate(), loan.getMemberNo(), loan.getCompleted());
    }

    @Override
    public boolean saveLoan(LoanDTO loan) throws SQLException {
        return loanDAO.saveAndInsert(new Loan(loan.getLoanId(), loan.getInterest(), loan.getLoanAmount(), loan.getInstallments(), loan.getFirInsAmount(), loan.getInsAmount(), loan.getDate(), loan.getMemberNo(), loan.getCompleted()));
    }

    @Override
    public boolean updateLoan(LoanDTO loan) throws SQLException {
        return loanDAO.update(new Loan(loan.getLoanId(), loan.getInterest(), loan.getLoanAmount(), loan.getInstallments(), loan.getFirInsAmount(), loan.getInsAmount(), loan.getDate(), loan.getMemberNo(), loan.getCompleted()));
    }

    @Override
    public List<Integer> getMemberNos() throws SQLException {
        return accountDAO.getIds();
    }

    @Override
    public ObservableList<LoanTM> getAllLoans() throws SQLException {
        return loanDAO.getAll();
    }

    @Override
    public String generateNextLoanId() throws SQLException {
        return loanDAO.generateNextId();
    }

    @Override
    public boolean deleteLoan(String loanId) throws SQLException {
        return loanDAO.delete(loanId);
    }

    @Override
    public Double getLoanId() throws SQLException {
        return interestDAO.getLoanId();
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }
}
