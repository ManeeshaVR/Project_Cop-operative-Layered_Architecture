package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.tm.OtherSerTM;

import java.sql.SQLException;

public interface ServiceDAO extends CrudDAO<Service, OtherSerTM, String, Integer> {
    public int getCount() throws SQLException;

    public int getServiceAmount() throws SQLException;

    public boolean completed(boolean b, String serId) throws SQLException;
}
