package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.PayLoanDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.LoanDTO;
import lk.ijse.cooperative.dto.PayLoanDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Loan;
import lk.ijse.cooperative.entity.PayLoan;
import lk.ijse.cooperative.entity.tm.PayLoanTM;

import java.sql.SQLException;
import java.util.List;

public class PayLoanBoImpl implements PayLoanBo{

    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);
    PayLoanDAO payLoanDAO = (PayLoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYLOAN);

    @Override
    public PayLoanDTO searchPayLoan(String payId) throws SQLException {
        PayLoan payLoan = payLoanDAO.search(payId);
        return new PayLoanDTO(payLoan.getDpLId(), payLoan.getLoanAmount(), payLoan.getPayAmount(), payLoan.getPaidAmount(), payLoan.getCompInstallments(), payLoan.getDpLId());
    }

    @Override
    public ObservableList<PayLoanTM> getAllPayLoans() throws SQLException {
        return payLoanDAO.getAll();
    }

    @Override
    public String generateNextPayLoanId() throws SQLException {
        return payLoanDAO.generateNextId();
    }

    @Override
    public int completedInstallments(String id) throws SQLException {
        return payLoanDAO.completedInstallments(id);
    }

    @Override
    public boolean saveAndUpdate(PayLoanDTO payLoan, boolean completed) throws SQLException {
        return payLoanDAO.saveAndUpdate(new PayLoan(payLoan.getDpLId(), payLoan.getLoanAmount(), payLoan.getPayAmount(), payLoan.getPaidAmount(), payLoan.getCompInstallments(), payLoan.getDpLId()), completed);
    }

    @Override
    public boolean deleteAndUpdate(String dpLId, String lId, boolean completed) throws SQLException {
        return payLoanDAO.deleteAndUpdate(dpLId, lId, completed);
    }

    @Override
    public LoanDTO searchLoan(String lId) throws SQLException {
        Loan loan = loanDAO.search(lId);
        return new LoanDTO(loan.getLoanId(), loan.getInterest(), loan.getLoanAmount(), loan.getInstallments(), loan.getFirInsAmount(), loan.getInsAmount(), loan.getDate(), loan.getMemberNo(), loan.getCompleted());
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }

    @Override
    public List<String> getLoanIds() throws SQLException {
        return loanDAO.getIds();
    }
}
