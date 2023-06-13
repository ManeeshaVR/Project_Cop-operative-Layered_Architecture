package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.LoanFormBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.PayLoanDAO;
import lk.ijse.cooperative.entity.Loan;

import java.sql.SQLException;
import java.util.List;

public class LoanFormBoImpl implements LoanFormBo {

    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    PayLoanDAO payLoanDAO = (PayLoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYLOAN);

    @Override
    public List<Loan> getLoans() throws SQLException {
        return loanDAO.getLoans();
    }

    @Override
    public boolean updateLoans(Loan ln) throws SQLException {
        return payLoanDAO.updateLoans(ln);
    }
}
