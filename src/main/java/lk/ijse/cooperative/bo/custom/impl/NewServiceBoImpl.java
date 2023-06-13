package lk.ijse.cooperative.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.cooperative.bo.custom.*;
import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.custom.AccountDAO;
import lk.ijse.cooperative.dao.custom.LoanDAO;
import lk.ijse.cooperative.dao.custom.ServiceDAO;
import lk.ijse.cooperative.dto.AccountDTO;
import lk.ijse.cooperative.dto.ServiceDTO;
import lk.ijse.cooperative.entity.Account;
import lk.ijse.cooperative.entity.Service;
import lk.ijse.cooperative.entity.tm.OtherSerTM;

import java.sql.SQLException;
import java.util.List;

public class NewServiceBoImpl implements NewServiceBo{

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);
    AccountDAO accountDAO = (AccountDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ACCOUNT);

    @Override
    public ObservableList<OtherSerTM> getAllService() throws SQLException {
        return serviceDAO.getAll();
    }

    @Override
    public String generateNextServiceId() throws SQLException {
        return serviceDAO.generateNextId();
    }

    @Override
    public boolean saveService(ServiceDTO service) throws SQLException {
        return serviceDAO.save(new Service(service.getSerId(), service.getType(), service.getAmount(), service.getInterest(), service.getDate(), service.getMemberNo(), service.getCompleted()));
    }

    @Override
    public boolean updateService(ServiceDTO service) throws SQLException {
        return serviceDAO.update(new Service(service.getSerId(), service.getType(), service.getAmount(), service.getInterest(), service.getDate(), service.getMemberNo(), service.getCompleted()));
    }

    @Override
    public boolean deleteService(String id) throws SQLException {
        return serviceDAO.delete(id);
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
    public List<Integer> getMemberNos() throws SQLException {
        return accountDAO.getIds();
    }
}
