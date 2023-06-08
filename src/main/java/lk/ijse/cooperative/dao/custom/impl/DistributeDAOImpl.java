package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.custom.DistributeDAO;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.Distribute;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DistributeDAOImpl implements DistributeDAO {
    public boolean save(Distribute distribute) throws SQLException{
        String sql = "INSERT INTO distribution (disId, itemId, itemName, date, department, qty, `desc`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, distribute.getDisId(), distribute.getItemId(), distribute.getItemName(), distribute.getDate(), distribute.getDep(), distribute.getDisQty(), distribute.getDesc());
    }

    @Override
    public boolean update(Distribute dto) throws SQLException {
        return false;
    }

    public String generateNextId() throws SQLException {
        String sql = "SELECT disId FROM distribution ORDER BY disId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public String splitId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("D00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "D00" + id;
        }
        return "D001";
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    public boolean saveAndUpdate(Distribute distribute) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(distribute);
            if (isSaved) {
                boolean isUpdated = ItemDAOImpl.updateQty2(distribute.getDisQty(), distribute.getItemId());
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

    public boolean deleteAndUpdate(String disId, String itemId, int disQty) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isDeleted = delete(disId);
            if (isDeleted) {
                System.out.println("DELETE");
                boolean isUpdated = ItemDAOImpl.updateQty1(disQty, itemId);
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

    public boolean delete(String disId) throws SQLException {
        String sql = "DELETE FROM distribution WHERE disId=?";
        return CrudUtil.execute(sql, disId);
    }

    public Distribute search(String disId) throws SQLException {
        String sql = "SELECT * FROM distribution WHERE disId=?";
        ResultSet resultSet = CrudUtil.execute(sql, disId);
        if (resultSet.next()){
            return new Distribute(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7));
        }return null;
    }

    public int getQtyCount() throws SQLException {
        String sql = "SELECT qty FROM distribution";
        ResultSet resultSet = CrudUtil.execute(sql);
        int count = 0;
        while (resultSet.next()){
            int qty = resultSet.getInt(1);
            count+=qty;
        }return count;
    }

    public int getCount() throws SQLException {
        String sql = "SELECT disId FROM distribution";
        ResultSet resultSet = CrudUtil.execute(sql);
        int count = 0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    public ObservableList<Distribute> getAll() throws SQLException {
        ObservableList<Distribute> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM distribution";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            data.add(new Distribute(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }return data;
    }

    @Override
    public Distribute search2(String s) throws SQLException {
        return null;
    }
}
