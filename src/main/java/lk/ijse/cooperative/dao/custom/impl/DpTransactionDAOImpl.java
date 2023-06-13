package lk.ijse.cooperative.dao.custom.impl;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.DepositDAO;
import lk.ijse.cooperative.dao.custom.DpTransactionDAO;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.entity.DpTransaction;
import lk.ijse.cooperative.entity.tm.TransTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DpTransactionDAOImpl implements DpTransactionDAO {

    DepositDAO depositDAO = (DepositDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEPOSIT);

    public boolean save(DpTransaction dpTransaction) throws SQLException {
        String sql = "INSERT INTO deposittransactions (transactionId, type, amount, date, description, depositId) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, dpTransaction.getTransId(), dpTransaction.getType(), dpTransaction.getAmount(), dpTransaction.getDate(), dpTransaction.getDesc(), dpTransaction.getDpId());
    }

    public boolean delete(String transId) throws SQLException {
        String sql = "DELETE FROM deposittransactions WHERE transactionId=?";
        return CrudUtil.execute(sql, transId);
    }

    public DpTransaction search(String transId) throws SQLException {
        String sql = "SELECT * FROM deposittransactions WHERE transactionId=?";
        ResultSet resultSet = CrudUtil.execute(sql, transId);
        if (resultSet.next()){
            return new DpTransaction(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6));
        }return null;
    }

    public ObservableList<TransTM> getAll() throws SQLException {
        String sql = "SELECT * FROM deposittransactions";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<TransTM> data = FXCollections.observableArrayList();
        while (resultSet.next()){
            JFXButton button = new JFXButton("Remove");
            data.add(new TransTM(
                    resultSet.getString(6),
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(4),
                    resultSet.getString(5),
                    button
            ));
        }return data;
    }

    public DpTransaction search2(String depositId) throws SQLException {
        String sql = "SELECT * FROM deposittransactions WHERE depositId=?";
        ResultSet resultSet = CrudUtil.execute(sql, depositId);
        if (resultSet.next()){
            return new DpTransaction(resultSet.getString(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6));
        }return null;
    }

    public boolean update(DpTransaction dpTransaction) throws SQLException {
        String sql = "UPDATE deposittransactions SET date=? description=? WHERE transactionId=?";
        return CrudUtil.execute(sql, dpTransaction.getDate(), dpTransaction.getDesc());
    }

    public String generateNextId() throws SQLException {
        String sql = "SELECT transactionId FROM deposittransactions ORDER BY transactionId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public String splitId(String currentTransId) {
        if(currentTransId != null) {
            String[] strings = currentTransId.split("W00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "W00"+id;
        }
        return "W001";
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    public boolean saveAndUpdate(DpTransaction dpTransaction) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(dpTransaction);
            if (isSaved) {
                DepositDAO deposit = new DepositDAOImpl();
                boolean isUpdated = deposit.updateSpecialDeposits(dpTransaction);
                if (isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public boolean deleteAndUpdate(String transId, double amount, String depId) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = delete(transId);
            if (isSaved) {
                DepositDAO dep = new DepositDAOImpl();
                boolean isUpdated = dep.updateSpecialDeposits(amount, depId);;
                if (isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }
}
