package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.dto.Supplier;
import lk.ijse.cooperative.dto.tm.SupplierTM;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier, SupplierTM, String, String> {
    public int getCount() throws SQLException;
}
