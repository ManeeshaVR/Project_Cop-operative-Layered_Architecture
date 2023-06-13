package lk.ijse.cooperative.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.DepositDTO;
import lk.ijse.cooperative.dto.DpTransactionDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.DpTransaction;
import lk.ijse.cooperative.entity.tm.TransTM;

import java.sql.SQLException;
import java.util.List;

public interface DepositTransBo extends SuperBo {
    public boolean saveAndUpdate(DpTransactionDTO dpTransactionDTO) throws SQLException;

    public boolean deleteAndUpdate(String transId, double amount, String depId) throws SQLException;

    public List<String> getDepositIds() throws SQLException;

    public DpTransactionDTO searchDpTransaction(String transId) throws SQLException;

    public ObservableList<TransTM> getAllDpTransaction() throws SQLException;

    public boolean updateDpTransaction(DpTransactionDTO dpTransaction) throws SQLException;

    public String generateNextTransId() throws SQLException;

    public DepositDTO searchDeposit(String depId) throws SQLException;

    public AccountDTO searchAccount(Integer memberNo) throws SQLException;

}
