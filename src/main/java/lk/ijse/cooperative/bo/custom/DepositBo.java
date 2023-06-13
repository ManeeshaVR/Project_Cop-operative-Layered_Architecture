package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.DepositDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.tm.DepositsTM;

import java.sql.SQLException;
import java.util.List;

public interface DepositBo extends SuperBo {
    public boolean saveDeposit(DepositDTO deposit) throws SQLException;

    public boolean updateDeposit(DepositDTO deposit) throws SQLException;

    public DepositDTO searchDeposit(String depId) throws SQLException;

    public boolean deleteDeposit(String dpId) throws SQLException;

    public ObservableList<DepositsTM> getAllDeposits() throws SQLException;

    public String generateNextDepositId() throws SQLException;

    public List<DepositsTM> getDeposits() throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;
}
