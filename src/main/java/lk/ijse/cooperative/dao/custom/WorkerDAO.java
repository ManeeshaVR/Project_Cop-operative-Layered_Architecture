package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dto.Worker;

import java.sql.SQLException;

public interface WorkerDAO extends CrudDAO<Worker, Worker, String, String> {
    public int getCount() throws SQLException;
}
