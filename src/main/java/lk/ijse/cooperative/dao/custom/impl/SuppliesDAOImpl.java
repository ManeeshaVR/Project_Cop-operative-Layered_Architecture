package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dao.custom.SuppliesDAO;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.Supplies;
import lk.ijse.cooperative.dto.tm.SuppliesTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliesDAOImpl implements SuppliesDAO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public String generateNextId() throws SQLException {
        String sql = "SELECT orderId FROM managesupplies ORDER BY OrderId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] strings = currentOrderId.split("O00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "O00"+id;
        }
        return "O001";
    }

    public ObservableList<SuppliesTM> getAll() throws SQLException {
        String sql = "SELECT * FROM managesupplies";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<SuppliesTM> data = FXCollections.observableArrayList();
        while (resultSet.next()){
            data.add(new SuppliesTM(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getDate(9),
                    resultSet.getString(1)
            ));
        }return data;
    }

    public boolean save(Supplies supplies) throws SQLException {
        String sql = "INSERT INTO managesupplies (orderId, supplierId, supName, itemId, itemName, qty, unitPrice, amount, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, supplies.getOrderId(), supplies.getSupId(), supplies.getSupName(), supplies.getItemId(), supplies.getItemName(), supplies.getQty(), supplies.getUniPrice(), supplies.getAmount(), supplies.getDate());
    }

    public boolean update(Supplies supplies) throws SQLException {
        String sql = "UPDATE managesupplies SET supplierId=?, supName=?, itemId=?, itemName=?, qty=?, unitPrice=?, amount=?, date=? WHERE orderId=?";
        return CrudUtil.execute(sql, supplies.getSupId(), supplies.getSupName(), supplies.getItemId(), supplies.getItemName(), supplies.getQty(), supplies.getUniPrice(), supplies.getAmount(), supplies.getDate(), supplies.getOrderId());
    }

    public boolean delete(String orderId) throws SQLException {
        String sql = "DELETE FROM managesupplies WHERE orderId=?";
        return CrudUtil.execute(sql, orderId);
    }

    public Supplies search(String orderId) throws SQLException {
        String sql = "SELECT * FROM managesupplies WHERE orderId=?";
        ResultSet resultSet = CrudUtil.execute(sql, orderId);
        if (resultSet.next()){
            return new Supplies(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDate(9));
        }else {
            return null;
        }
    }

    public boolean updateQty(int disQty, String itemId) throws SQLException {
        String sql = "UPDATE managesupplies SET remain_qty=(remain_qty - ?) WHERE itemId=?";
        return CrudUtil.execute(sql, disQty, itemId);
    }

    public Supplies search2(String itemId) throws SQLException {
        String sql = "SELECT * FROM managesupplies WHERE itemId=?";
        ResultSet resultSet = CrudUtil.execute(sql, itemId);
        if (resultSet.next()){
            return new Supplies(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDate(9));
        }else {
            return null;
        }
    }

    public List<String> getIds() throws SQLException {
        String sql = "SELECT itemId FROM managesupplies";
        List<String> ids = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }return ids;
    }

    public int getCount() throws SQLException {
        String sql = "SELECT orderId FROM managesupplies";
        ResultSet resultSet = CrudUtil.execute(sql);
        int count = 0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    public boolean saveAndUpdate(Supplies supplies) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(supplies);
            if (isSaved) {
                boolean isUpdated = itemDAO.updateQty1(supplies.getQty(), supplies.getItemId());
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

    public boolean deleteAndUpdate(String orderId, Integer qty, String itemId) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isDeleted = delete(orderId);
            if (isDeleted) {
                boolean isUpdated = itemDAO.updateQty2(qty, itemId);
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

    public int getQtyCount() throws SQLException {
        String sql = "SELECT qty FROM managesupplies";
        ResultSet resultSet = CrudUtil.execute(sql);
        int count = 0;
        while (resultSet.next()){
            int qty = resultSet.getInt(1);
            count+=qty;
        }return count;
    }
}
