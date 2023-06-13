package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.InterestBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.InterestDAO;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dto.InterestDTO;
import lk.ijse.cooperative.entity.Interest;

import java.sql.SQLException;

public class InterestBoImpl implements InterestBo {

    InterestDAO interestDAO = (InterestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INTEREST);

    @Override
    public boolean saveInterest(InterestDTO interest) throws SQLException {
        return interestDAO.save(new Interest(interest.getLoanInt(), interest.getDepositInt(), interest.getServiceInt()));
    }

    @Override
    public InterestDTO searchInterest() throws SQLException {
        Interest interest = interestDAO.searchInterest();
        return new InterestDTO(interest.getLoanInt(), interest.getDepositInt(), interest.getServiceInt());
    }
}
