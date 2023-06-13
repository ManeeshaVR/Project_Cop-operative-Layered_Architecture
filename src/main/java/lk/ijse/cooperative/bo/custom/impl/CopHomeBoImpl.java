package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.CopHomeBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.DepositDAO;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.MemberDAO;
import lk.ijse.cooperative.dao.custom.ServiceDAO;

import java.sql.SQLException;

public class CopHomeBoImpl implements CopHomeBo{

    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);
    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);

    @Override
    public Double getShares() throws SQLException {
        return depositDAO.getShares();
    }

    @Override
    public Double getComDep() throws SQLException {
        return depositDAO.getComDep();
    }

    @Override
    public Double getSpecDep() throws SQLException {
        return depositDAO.getSpecDep();
    }

    @Override
    public Double getPenDep() throws SQLException {
        return depositDAO.getPenDep();
    }

    @Override
    public int getLoanAmount() throws SQLException {
        return loanDAO.getLoanAmount();
    }

    @Override
    public int getServiceAmount() throws SQLException {
        return serviceDAO.getServiceAmount();
    }

    @Override
    public int getMemberCount() throws SQLException {
        return memberDAO.getCount();
    }

    @Override
    public int getLoanCount() throws SQLException {
        return loanDAO.getCount();
    }

    @Override
    public int getServiceCount() throws SQLException {
        return serviceDAO.getCount();
    }
}
