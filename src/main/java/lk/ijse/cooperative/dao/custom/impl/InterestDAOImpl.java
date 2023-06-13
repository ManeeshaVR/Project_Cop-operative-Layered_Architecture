package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.custom.InterestDAO;
import lk.ijse.cooperative.entity.Interest;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InterestDAOImpl implements InterestDAO {
    public boolean save(Interest interest) throws SQLException {
        String sql = "UPDATE interest SET loanInt=?, depositInt=?, serviceInt=? WHERE IntId='I001'";
        return CrudUtil.execute(sql, interest.getLoanInt(), interest.getDepositInt(), interest.getServiceInt());
    }

    @Override
    public boolean update(Interest dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public Interest search(String s) throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }

    @Override
    public String splitId(String currentId) {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Interest> getAll() throws SQLException {
        return null;
    }

    @Override
    public Interest search2(String s) throws SQLException {
        return null;
    }

    public Interest searchInterest() throws SQLException {
        String sql = "SELECT * FROM interest WHERE IntId='I001'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return new Interest(resultSet.getDouble(1), resultSet.getDouble(2), resultSet.getDouble(3));
        }return null;
    }

    public Double getLoanId() throws SQLException {
        String sql = "SELECT loanInt FROM interest WHERE IntId='I001'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }return 0.08;
    }

    public Double getDepositId() throws SQLException {
        String sql = "SELECT depositInt FROM interest WHERE IntId='I001'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }return 0.04;
    }

    public Double getServiceId() throws SQLException {
        String sql = "SELECT serviceInt FROM interest WHERE IntId='I001'";
        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()){
            return resultSet.getDouble(1);
        }return 0.08;
    }

}
