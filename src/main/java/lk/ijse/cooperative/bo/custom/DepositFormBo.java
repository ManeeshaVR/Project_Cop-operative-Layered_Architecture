package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.entity.tm.DepositsTM;

import java.sql.SQLException;
import java.util.List;

public interface DepositFormBo extends SuperBo {
    public List<DepositsTM> getDeposits() throws SQLException;

    public boolean updateDeposits(DepositsTM dp) throws SQLException;

    public boolean addYearInterest(DepositsTM dp) throws SQLException;
}
