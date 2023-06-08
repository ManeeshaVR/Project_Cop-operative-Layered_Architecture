package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.custom.SearchDAO;
import lk.ijse.cooperative.dto.All;
import lk.ijse.cooperative.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SearchDAOImpl implements SearchDAO {

    public All getAll(int text) throws SQLException {
        String sql = "SELECT a.memberNo AS Member_No, a.name AS Name, NIC, d.depositId AS Deposit_Id, d.shares AS Shares, d.compulsoryDeposits AS Compulsory_Deposits, d.specialDeposits AS Special_Deposits, d.pensionDeposits AS Pension_Deposits, l.loanId AS Loan_Id, l.loanAmount AS Loan_Amount, l.installments AS Insatallments, o.serviceId AS Service_Id, o.amount AS Amount\n" +
                "FROM account a\n" +
                "left JOIN deposit d on a.memberNo = d.memberNo\n" +
                "left JOIN loan l on d.memberNo = l.memberNo\n" +
                "left JOIN otherservices o on l.memberNo = o.memberNo\n" +
                "WHERE l.completed=false && o.completed=false && a.memberNo=?";
        ResultSet resultSet = CrudUtil.execute(sql, text);
        if (resultSet.next()){
            return new All(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9),
                    resultSet.getDouble(10),
                    resultSet.getInt(11),
                    resultSet.getString(12),
                    resultSet.getDouble(13)
            );
        }return null;
    }

    @Override
    public boolean save(All dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(All dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public All search(String s) throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return null;
    }

    @Override
    public String splitId(String currentId) {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<All> getAll() throws SQLException {
        return null;
    }

    @Override
    public All search2(String s) throws SQLException {
        return null;
    }
}
