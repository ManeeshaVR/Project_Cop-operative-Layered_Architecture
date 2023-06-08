package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.custom.WorkerDAO;
import lk.ijse.cooperative.dto.Worker;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {
    public int getCount() throws SQLException {
        String sql = "SELECT workerId FROM worker";
        ResultSet resultSet = CrudUtil.execute(sql);
        int count = 0;
        while (resultSet.next()){
            count++;
        }return count;
    }

    @Override
    public boolean save(Worker dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Worker dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public Worker search(String s) throws SQLException {
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
    public ObservableList<Worker> getAll() throws SQLException {
        return null;
    }

    @Override
    public Worker search2(String s) throws SQLException {
        return null;
    }
}
