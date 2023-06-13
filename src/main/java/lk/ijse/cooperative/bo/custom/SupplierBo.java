package lk.ijse.cooperative.bo.custom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.SuperBo;
import lk.ijse.cooperative.dto.SupplierDTO;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.tm.SupplierTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierBo extends SuperBo {

    public boolean saveSupplier(SupplierDTO supplier) throws SQLException;

    public boolean updateSupplier(SupplierDTO supplier) throws SQLException;

    public boolean deleteSupplier(String id) throws SQLException;

    public SupplierDTO searchSupplier(String id) throws SQLException;

    public ObservableList<SupplierTM> getAllSuppliers() throws SQLException;

    public String generateSupplierNextId() throws SQLException;
}
