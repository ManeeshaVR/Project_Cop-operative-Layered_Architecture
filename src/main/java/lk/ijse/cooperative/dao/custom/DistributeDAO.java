package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Distribute;

import java.sql.SQLException;

public interface DistributeDAO extends CrudDAO<Distribute, Distribute, String, String> {
    public boolean saveAndUpdate(Distribute distribute) throws SQLException;

    public boolean deleteAndUpdate(String disId, String itemId, int disQty) throws SQLException;

    public int getQtyCount() throws SQLException;

    public int getCount() throws SQLException;
}
