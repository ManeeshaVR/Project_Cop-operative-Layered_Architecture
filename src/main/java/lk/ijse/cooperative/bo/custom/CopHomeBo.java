package lk.ijse.cooperative.bo.custom;

import lk.ijse.cooperative.bo.SuperBo;

import java.sql.SQLException;

public interface CopHomeBo extends SuperBo {
    public Double getShares() throws SQLException;

    public Double getComDep() throws SQLException;

    public Double getSpecDep() throws SQLException;

    public Double getPenDep() throws SQLException;

}
