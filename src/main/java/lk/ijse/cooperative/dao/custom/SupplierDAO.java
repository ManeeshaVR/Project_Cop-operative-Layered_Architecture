package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.tm.SupplierTM;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier, SupplierTM, String, String> {
    public int getCount() throws SQLException;
}
