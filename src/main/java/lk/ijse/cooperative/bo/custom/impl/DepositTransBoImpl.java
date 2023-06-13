package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.DepositDAO;
import lk.ijse.cooperative.dao.custom.DpTransactionDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.DepositDTO;
import lk.ijse.cooperative.dto.DpTransactionDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.DpTransaction;
import lk.ijse.cooperative.entity.tm.TransTM;

import java.sql.SQLException;
import java.util.List;

public class DepositTransBoImpl implements DepositTransBo{

    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);
    DpTransactionDAO dpTransDAO = (DpTransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DPTRANS);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);

    @Override
    public boolean saveAndUpdate(DpTransactionDTO dpTransaction) throws SQLException {
        return dpTransDAO.saveAndUpdate(new DpTransaction(dpTransaction.getTransId(), dpTransaction.getType(), dpTransaction.getAmount(), dpTransaction.getDate(), dpTransaction.getDesc(), dpTransaction.getDpId()));
    }

    @Override
    public boolean deleteAndUpdate(String transId, double amount, String depId) throws SQLException {
        return dpTransDAO.deleteAndUpdate(transId, amount, depId);
    }

    @Override
    public List<String> getDepositIds() throws SQLException {
        return depositDAO.getIds();
    }


    @Override
    public DpTransactionDTO searchDpTransaction(String transId) throws SQLException {
        DpTransaction dpTransaction = dpTransDAO.search(transId);
        return new DpTransactionDTO(dpTransaction.getTransId(), dpTransaction.getType(), dpTransaction.getAmount(), dpTransaction.getDate(), dpTransaction.getDesc(), dpTransaction.getDpId());
    }

    @Override
    public ObservableList<TransTM> getAllDpTransaction() throws SQLException {
        return dpTransDAO.getAll();
    }

    @Override
    public boolean updateDpTransaction(DpTransactionDTO dpTransaction) throws SQLException {
        return dpTransDAO.update(new DpTransaction(dpTransaction.getTransId(), dpTransaction.getType(), dpTransaction.getAmount(), dpTransaction.getDate(), dpTransaction.getDesc(), dpTransaction.getDpId()));
    }

    @Override
    public String generateNextTransId() throws SQLException {
        return dpTransDAO.generateNextId();
    }

    @Override
    public DepositDTO searchDeposit(String depId) throws SQLException {
        Deposit deposit = depositDAO.search(depId);
        return new DepositDTO(deposit.getDepositId(), deposit.getShares(), deposit.getCompDep(), deposit.getSpecDep(), deposit.getPensDep(), deposit.getDesc(), deposit.getMemberNo());
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }
}
