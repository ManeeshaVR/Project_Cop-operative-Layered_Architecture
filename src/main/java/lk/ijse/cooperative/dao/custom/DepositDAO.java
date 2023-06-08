package lk.ijse.cooperative.dao.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dao.custom.impl.AccountDAOImpl;
import lk.ijse.cooperative.dao.custom.impl.InterestDAOImpl;
import lk.ijse.cooperative.dto.Account;
import lk.ijse.cooperative.dto.Deposit;
import lk.ijse.cooperative.dto.DpTransaction;
import lk.ijse.cooperative.dto.tm.DepositsTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepositDAO extends CrudDAO<Deposit, DepositsTM, String, Integer> {
    public boolean updateSpecialDeposits(DpTransaction dpTransaction) throws SQLException;

    public boolean updateSpecialDeposits(double amount, String depId) throws SQLException;

    public List<DepositsTM> getDeposits() throws SQLException;

    public boolean updateDeposits(DepositsTM dp) throws SQLException;

    public boolean addYearInterest(DepositsTM dp) throws SQLException;

    public Double getShares() throws SQLException;

    public Double getComDep() throws SQLException;

    public Double getSpecDep() throws SQLException;

    public Double getPenDep() throws SQLException;

}
