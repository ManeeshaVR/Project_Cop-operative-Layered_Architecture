package lk.ijse.cooperative.dao.custom;

import lk.ijse.cooperative.dao.CrudDAO;
import lk.ijse.cooperative.entity.DpTransaction;
import lk.ijse.cooperative.entity.tm.TransTM;

import java.sql.SQLException;

public interface DpTransactionDAO extends CrudDAO<DpTransaction, TransTM, String, String> {
    public boolean saveAndUpdate(DpTransaction dpTransaction) throws SQLException;

    public boolean deleteAndUpdate(String transId, double amount, String depId) throws SQLException;
}
