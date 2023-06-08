package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dto.Interest;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface InterestDAO extends CrudDAO<Interest, Interest, String, String> {
    public Double getLoanId() throws SQLException;

    public Double getDepositId() throws SQLException;

    public Double getServiceId() throws SQLException;

    public Interest search() throws SQLException;
}
