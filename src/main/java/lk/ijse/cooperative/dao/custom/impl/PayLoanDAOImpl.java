package lk.ijse.cooperative.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.PayLoanDAO;
import lk.ijse.cooperative.db.DBConnection;
import lk.ijse.cooperative.dto.Loan;
import lk.ijse.cooperative.dto.PayLoan;
import lk.ijse.cooperative.dto.tm.PayLoanTM;
import lk.ijse.cooperative.util.CrudUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PayLoanDAOImpl implements PayLoanDAO {

    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);

    public boolean save(PayLoan payLoan) throws SQLException {
//        String sql = "INSERT INTO PayLoan (payLoanId, loanAmount, date, insAmount, completedInstallments, loanId, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        return CrudUtil.execute(sql, payLoan.getDpLId(), payLoan.getLoanAmount(), payLoan.getDate(), payLoan.getInsAmount(), payLoan.getCompInstallments(), payLoan.getLId(), payLoan.getDescription());
    return true;
    }

    public boolean update(PayLoan payLoan) throws SQLException {
        String sql = "UPDATE PayLoan SET payAmount=?, paidAmount=?, completedInstallments=? WHERE payLoanID = ?";
        return CrudUtil.execute(sql, payLoan.getPayAmount(), payLoan.getPaidAmount(), payLoan.getCompInstallments(), payLoan.getDpLId());
    }

    public boolean delete(String dpLId) throws SQLException {
        String sql = "DELETE FROM PayLoan WHERE payLoanId = ?";
        return CrudUtil.execute(sql, dpLId);
    }

    public PayLoan search(String payId) throws SQLException {
        String sql = "SELECT * FROM PayLoan WHERE payLoanId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, payId);
        if (resultSet.next()){
            return new PayLoan(resultSet.getString(1), resultSet.getDouble(2),resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getString(6));
        }return null;
    }

    public ObservableList<PayLoanTM> getAll() throws SQLException {
        String sql = "SELECT * FROM PayLoan";
        ResultSet resultSet = CrudUtil.execute(sql);
        ObservableList<PayLoanTM> data = FXCollections.observableArrayList();
        while (resultSet.next()){
            data.add(new PayLoanTM(
                    resultSet.getString(6),
                    resultSet.getDouble(2),
                    resultSet.getString(1),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)
            ));
        }return data;
    }

    public PayLoan search2(String loanId) throws SQLException {
        String sql = "SELECT * FROM PayLoan WHERE loanId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, loanId);
        if (resultSet.next()){
            return new PayLoan(resultSet.getString(1), resultSet.getDouble(2),resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getInt(5), resultSet.getString(6));
        }return null;
    }

    public String generateNextId() throws SQLException {
        String sql = "SELECT payLoanId FROM PayLoan ORDER BY payLoanId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    public String splitId(String currentPayLoanId) {
        if(currentPayLoanId != null) {
            String[] strings = currentPayLoanId.split("P00");
            int id = Integer.parseInt(strings[1]);
            id++;

            return "P00"+id;
        }
        return "P001";
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    public int completedInstallments(String id) throws SQLException {
        String sql = "SELECT completedInstallments FROM PayLoan WHERE loanId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);
        if (resultSet.next()) {
            int payId = resultSet.getInt(1);
            if (payId > 0) {
                return payId++;
            } else {
                return 1;
            }
        }return 1;
    }

    public boolean saveAndUpdate(PayLoan payLoan, boolean completed) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = update(payLoan);
            if (isSaved) {
                boolean isUpdated = loanDAO.updateCompleted(completed, payLoan.getLId());
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

    public boolean deleteAndUpdate(String dpLId, String lId, boolean completed) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = delete(dpLId);
            if (isSaved) {
                boolean isUpdated = loanDAO.updateCompleted(completed, lId);
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

    public Integer getComIns(String loanId) throws SQLException {
        String sql = "SELECT completedInstallments FROM PayLoan WHERE loanId = ?";
        ResultSet resultSet = CrudUtil.execute(sql, loanId);
        int[] comIns = new int[0];
        while (resultSet.next()){
            int i=0;
            comIns[i] = resultSet.getInt(1);
            i++;
        }
        for (int i=0; i<comIns.length; i++){
            for (int j=0; j<comIns.length; j++){
                if (comIns[i]<comIns[j]){
                    comIns[i]=comIns[j];
                }
            }
        }return comIns[0];
    }

    public boolean updateLoans(Loan ln) throws SQLException {
        PayLoan payLoan = search2(ln.getLoanId());
        int ins = payLoan.getCompInstallments();
        if (ins==0) {
            String sql = "UPDATE PayLoan SET paidAmount=(paidAmount-?), completedInstallments=(completedInstallments+1) WHERE loanId = ?";
            return CrudUtil.execute(sql, ln.getFirInsAmount(), ln.getLoanId());
        }else {
            if (ln.getInstallments()==(payLoan.getCompInstallments()+1)) {
                boolean isUpdated = updateAndCompleted(ln, payLoan);
                return isUpdated;
            }else {
                boolean isUpdated = updateLoans2(ln);
                return isUpdated;
            }
        }
    }

    public boolean updateLoans2(Loan ln) throws SQLException {
        String sql = "UPDATE PayLoan SET paidAmount=(paidAmount-?), completedInstallments=(completedInstallments+1) WHERE loanId = ?";
        return CrudUtil.execute(sql, ln.getInsAmount(), ln.getLoanId());
    }

    public boolean updateAndCompleted(Loan ln, PayLoan payLoan) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = updateLoans2(ln);
            if (isSaved) {
                boolean isUpdated = loanDAO.updateCompleted(true, ln.getLoanId());
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

    @SneakyThrows
    public boolean insert(Loan loan) {
        double payAmount = loan.getFirInsAmount()+(loan.getInsAmount()*(loan.getInstallments()-1));
        String sql = "INSERT INTO PayLoan (payLoanId, loanAmount, payAmount, paidAmount, completedInstallments, loanId) VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql, generateNextId(), loan.getLoanAmount(), payAmount, payAmount, 0, loan.getLoanId());
    }
}
