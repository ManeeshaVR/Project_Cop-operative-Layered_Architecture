package lk.ijse.cooperative.bo.custom.impl;

import lk.ijse.cooperative.bo.custom.SupHomeBo;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.DistributeDAO;
import lk.ijse.cooperative.dao.custom.ItemDAO;
import lk.ijse.cooperative.dao.custom.SupplierDAO;
import lk.ijse.cooperative.dao.custom.SuppliesDAO;

import java.sql.SQLException;

public class SupHomeBoImpl implements SupHomeBo {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    SuppliesDAO suppliesDAO = (SuppliesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIES);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    DistributeDAO distributeDAO = (DistributeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DISTRIBUTE);

    @Override
    public int getSuppliesQtyCount() throws SQLException {
        return suppliesDAO.getQtyCount();
    }

    @Override
    public int getDistributionQtyCount() throws SQLException {
        return distributeDAO.getQtyCount();
    }

    @Override
    public int getItemQtyCount() throws SQLException {
        return itemDAO.getQtyCount();
    }

    @Override
    public int getDistributionCount() throws SQLException {
        return distributeDAO.getCount();
    }

    @Override
    public int getSuppliesCount() throws SQLException {
        return suppliesDAO.getCount();
    }

    @Override
    public Integer getItemTypesQty(String type) throws SQLException {
        return itemDAO.getTypes(type);
    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getCount();
    }

    @Override
    public int getItemCount() throws SQLException {
        return itemDAO.getCount();
    }
}
