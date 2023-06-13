package lk.ijse.cooperative.dao;

import lk.ijse.cooperative.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        ACCOUNT, DEPOSIT, DISTRIBUTE, DPTRANS, INTEREST, ITEM, LOAN, MEMBER, ORDER, SERVICE, PAYLOAN, PAYSER, SEARCH, SUPPLIER, SUPPLIES
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case ACCOUNT:return new AccountDAOImpl();
            case DEPOSIT:return new DepositDAOImpl();
            case DISTRIBUTE:return new DistributeDAOImpl();
            case DPTRANS:return new DpTransactionDAOImpl();
            case INTEREST:return new InterestDAOImpl();
            case ITEM:return new ItemDAOImpl();
            case LOAN:return new LoanDAOImpl();
            case MEMBER:return new MemberDAOImpl();
            case SERVICE:return new ServiceDAOImpl();
            case SUPPLIES:return new SuppliesDAOImpl();
            case PAYLOAN:return new PayLoanDAOImpl();
            case PAYSER:return new PaySerDAOImpl();
            case SUPPLIER:return new SupplierDAOImpl();
            case SEARCH:return new SearchDAOImpl();
            //case ORDER_DETAILS:return new OrderDetailDAOImpl();
            //case QUERY_DAO:return new QueryDAOImpl();
            default:return null;
        }
    }
}
