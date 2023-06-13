package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Supplies;
import lk.ijse.cooperative.entity.tm.SuppliesTM;

import java.sql.SQLException;

public interface SuppliesDAO extends CrudDAO<Supplies, SuppliesTM, String, String> {
    public boolean updateQty(int disQty, String itemId) throws SQLException;

    public int getCount() throws SQLException;

    public boolean saveAndUpdate(Supplies supplies) throws SQLException;

    public boolean deleteAndUpdate(String orderId, Integer qty, String itemId) throws SQLException;

    public int getQtyCount() throws SQLException;
}
