package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.DepositBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.DepositDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.DepositDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Deposit;
import lk.ijse.cooperative.entity.tm.DepositsTM;

import java.sql.SQLException;
import java.util.List;

public class DepositBoImpl implements DepositBo {

    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);

    @Override
    public boolean saveDeposit(DepositDTO depositDTO) throws SQLException {
        Deposit deposit = new Deposit(depositDTO.getDepositId(), depositDTO.getShares(), depositDTO.getCompDep(), depositDTO.getSpecDep(), depositDTO.getPensDep(), depositDTO.getDesc(), depositDTO.getMemberNo());
        return depositDAO.save(deposit);
    }

    @Override
    public boolean updateDeposit(DepositDTO depositDTO) throws SQLException {
        Deposit deposit = new Deposit(depositDTO.getDepositId(), depositDTO.getShares(), depositDTO.getCompDep(), depositDTO.getSpecDep(), depositDTO.getPensDep(), depositDTO.getDesc(), depositDTO.getMemberNo());
        return depositDAO.update(deposit);
    }

    @Override
    public DepositDTO searchDeposit(String depId) throws SQLException {
        Deposit deposit = depositDAO.search(depId);
        return new DepositDTO(deposit.getDepositId(), deposit.getShares(), deposit.getCompDep(), deposit.getSpecDep(), deposit.getPensDep(), deposit.getDesc(), deposit.getMemberNo());
    }

    @Override
    public boolean deleteDeposit(String dpId) throws SQLException {
        return depositDAO.delete(dpId);
    }

    @Override
    public ObservableList<DepositsTM> getAllDeposits() throws SQLException {
        return depositDAO.getAll();
    }

    @Override
    public String generateNextDepositId() throws SQLException {
        return depositDAO.generateNextId();
    }

    @Override
    public List<DepositsTM> getDeposits() throws SQLException {
        return depositDAO.getDeposits();
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }


}
