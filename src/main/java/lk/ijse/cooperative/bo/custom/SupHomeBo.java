package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;

import java.sql.SQLException;

public interface SupHomeBo extends SuperBo {
    public int getSuppliesQtyCount() throws SQLException;

    public int getDistributionQtyCount() throws SQLException;

    public int getItemQtyCount() throws SQLException;

    public int getDistributionCount() throws SQLException;

    public int getSuppliesCount() throws SQLException;

    public Integer getItemTypesQty(String type) throws SQLException;

    public int getSupplierCount() throws SQLException;

    public int getItemCount() throws SQLException;

}
