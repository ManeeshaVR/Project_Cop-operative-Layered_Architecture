package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.InterestDAO;
import lk.ijse.cooperative.dao.custom.PaySerDAO;
import lk.ijse.cooperative.dao.custom.ServiceDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.PayServiceDTO;
import lk.ijse.cooperative.dto.ServiceDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.PayService;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.tm.PaySerTM;

import java.sql.SQLException;
import java.util.List;

public class PayServiceBoImpl implements PayServiceBo{

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);
    PaySerDAO paySerDAO = (PaySerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYSER);
    InterestDAO interestDAO = (InterestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INTEREST);

    @Override
    public boolean paidService(PayServiceDTO payService) throws SQLException {
        return paySerDAO.paid(new PayService(payService.getPayId(), payService.getAmount(), payService.getPayAmount(), payService.getServiceId(), payService.getDate()));
    }

    @Override
    public PayServiceDTO searchPayService(String payId) throws SQLException {
        PayService payService = paySerDAO.search(payId);
        return new PayServiceDTO(payService.getPayId(), payService.getAmount(), payService.getPayAmount(), payService.getServiceId(), payService.getDate());
    }

    @Override
    public boolean updatePayService(PayServiceDTO payService) throws SQLException {
        return paySerDAO.update(new PayService(payService.getPayId(), payService.getAmount(), payService.getPayAmount(), payService.getServiceId(), payService.getDate()));
    }

    @Override
    public boolean deletePayService(String payId, String serId, boolean b) throws SQLException {
        return paySerDAO.deletePay(payId, serId, b);
    }

    @Override
    public String generateNextPayServiceId() throws SQLException {
        return paySerDAO.generateNextId();
    }

    @Override
    public ObservableList<String> getServiceIds() throws SQLException {
        return (ObservableList<String>) serviceDAO.getIds();
    }

    @Override
    public ObservableList<PaySerTM> getAllPayServices() throws SQLException {
        return paySerDAO.getAll();
    }

    @Override
    public ServiceDTO searchService(String id) throws SQLException {
        Service service = serviceDAO.search(id);
        return new ServiceDTO(service.getSerId(), service.getType(), service.getAmount(), service.getInterest(), service.getDate(), service.getMemberNo(), service.getCompleted());
    }

    @Override
    public AccountDTO searchAccount(Integer memberNo) throws SQLException {
        Account account = accountDAO.search(memberNo);
        return new AccountDTO(account.getMemberNo(), account.getShares(), account.getCompulsoryDeposits(), account.getSpecialDeposits(), account.getPensionDeposits(), account.getNIC(), account.getName(), account.getMail());
    }

    @Override
    public Double getServiceInterest() throws SQLException {
        return interestDAO.getServiceId();
    }
}
