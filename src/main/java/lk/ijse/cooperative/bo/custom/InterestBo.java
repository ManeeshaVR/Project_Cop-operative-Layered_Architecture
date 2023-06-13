package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.InterestDTO;
import lk.ijse.cooperative.entity.Interest;

import java.sql.SQLException;

public interface InterestBo extends SuperBo {
    public boolean saveInterest(InterestDTO interest) throws SQLException;

    public InterestDTO searchInterest() throws SQLException;
}
