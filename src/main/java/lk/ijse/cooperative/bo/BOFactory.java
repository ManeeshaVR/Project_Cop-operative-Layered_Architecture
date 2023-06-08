package lk.ijse.cooperative.bo;

import lk.ijse.cooperative.dao.DAOFactory;
import lk.ijse.cooperative.dao.SuperDAO;
import lk.ijse.cooperative.dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        COPHOME, DEPOSIT, DISTRIBUTE, DPTRANS, INTEREST, ACCOUNT, ITEM, LOAN, MEMBER, ORDER, SERVICE, PAYLOAN, PAYSER, SEARCH, SUPPLIER, SUPPLIES
    }

    public SuperBo getBo(BOFactory.BOTypes types) {
        switch (types) {
//            case COPHOME:return new
//            case ACCOUNT:return new AccountDAOImpl();
//            case DEPOSIT:return new DepositDAOImpl();
//            case DISTRIBUTE:return new DistributeDAOImpl();
//            case DPTRANS:return new DpTransactionDAOImpl();
//            case INTEREST:return new InterestDAOImpl();
//            case ITEM:return new ItemDAOImpl();
//            case LOAN:return new LoanDAOImpl();
//            case MEMBER:return new MemberDAOImpl();
//            case SERVICE:return new ServiceDAOImpl();
//            case SUPPLIES:return new SuppliesDAOImpl();
//            case PAYLOAN:return new PayLoanDAOImpl();
//            //case ORDER_DETAILS:return new OrderDetailDAOImpl();
//            //case QUERY_DAO:return new QueryDAOImpl();
            default:return null;
        }
    }
}
