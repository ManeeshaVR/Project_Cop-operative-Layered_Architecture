package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.DepositFormBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.DepositDAO;
import lk.ijse.cooperative.entity.tm.DepositsTM;

import java.sql.SQLException;
import java.util.List;

public class DepositFormBoImpl implements DepositFormBo {

    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);

    @Override
    public List<DepositsTM> getDeposits() throws SQLException {
        return depositDAO.getDeposits();
    }

    @Override
    public boolean updateDeposits(DepositsTM dp) throws SQLException {
        return depositDAO.updateDeposits(dp);
    }

    @Override
    public boolean addYearInterest(DepositsTM dp) throws SQLException {
        return depositDAO.addYearInterest(dp);
    }
}
