package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.SupplierBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.SupplierDAO;
import lk.ijse.cooperative.dto.SupplierDTO;
import lk.ijse.cooperative.entity.Supplier;
import lk.ijse.cooperative.entity.tm.SupplierTM;

import java.sql.SQLException;

public class SupplierBoImpl implements SupplierBo {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public boolean saveSupplier(SupplierDTO supplier) throws SQLException {
        return supplierDAO.save(new Supplier(supplier.getId(), supplier.getName(), supplier.getContact(), supplier.getAddress()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
        return supplierDAO.update(new Supplier(supplier.getId(), supplier.getName(), supplier.getContact(), supplier.getAddress()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getContact(), supplier.getAddress());
    }

    @Override
    public ObservableList<SupplierTM> getAllSuppliers() throws SQLException {
        return supplierDAO.getAll();
    }

    @Override
    public String generateSupplierNextId() throws SQLException {
        return supplierDAO.generateNextId();
    }
}
