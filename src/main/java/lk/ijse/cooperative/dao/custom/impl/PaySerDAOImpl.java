package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.PaySerDAO;
import lk.ijse.cooperative.dao.custom.ServiceDAO;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.entity.PayService;
import lk.ijse.cooperative.entity.tm.PaySerTM;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaySerDAOImpl implements PaySerDAO {

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);

    public boolean paid(PayService payService) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(payService);
            if (isSaved) {
                boolean isUpdated = serviceDAO.completed(true, payService.getServiceId());
                if (isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public boolean save(PayService payService) throws SQLException {
        String sql = "INSERT INTO manageotherservices (payId, amount, payAmount, serviceId, date) VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, payService.getPayId(), payService.getAmount(), payService.getPayAmount(), payService.getServiceId(), payService.getDate());
    }

    public PayService search(String payId) throws SQLException {
        String sql = "SELECT * FROM manageotherservices WHERE payId=?";
        ResultSet resultSet = CrudUtil.execute(sql, payId);
        if (resultSet.next()){
            return new PayService(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getDate(5)
                    );
        }return null;
    }

    public boolean update(PayService payService) throws SQLException {
        String sql = "UPDATE manageotherservices SET payAmount = ?, date=? WHERE payId = ?";
        return CrudUtil.execute(sql, payService.getPayAmount(), payService.getDate(), payService.getPayId());
    }

    public boolean deletePay(String payId, String serId, boolean b) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isDeleted = delete(payId);
            if (isDeleted) {
                boolean isUpdated = serviceDAO.completed(false, serId);
                if (isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            er.printStackTrace();
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    public boolean delete(String payId) throws SQLException {
        String sql = "DELETE FROM manageotherservices WHERE payId=?";
        return CrudUtil.execute(sql, payId);
    }

    public String generateNextId() throws SQLException {
        String sql = "SELECT payId FROM manageotherservices ORDER BY payId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("T00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "T00"+id;
        }
        return "T001";
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    public ObservableList<PaySerTM> getAll() throws SQLException {
        ObservableList<PaySerTM> data = FXCollections.observableArrayList();
        String sql = "SELECT * FROM manageotherservices";
        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()){
            data.add(new PaySerTM(
                    resultSet.getString(4),
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getDouble(3),
                    resultSet.getDate(5)
            ));
        }return data;
    }

    @Override
    public PayService search2(String s) throws SQLException {
        return null;
    }
}
