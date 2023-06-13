package lk.ijse.cooperative.bo;

import lk.ijse.cooperative.bo.custom.SearchBo;
import lk.ijse.cooperative.bo.custom.impl.*;
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
        COPHOME, DEPOSIT, DISTRIBUTE, DPTRANS, INTEREST, ACCOUNT, ITEM, LOAN, MEMBER, ORDER, SERVICE, PAYLOAN, PAYSER, SEARCH, SUPHOME, SUPPLIER, SUPPLIES, DEPFORM, LOANFORM
    }

    public SuperBo getBo(BOFactory.BOTypes types) {
        switch (types) {
            case COPHOME:return new CopHomeBoImpl();
            case ACCOUNT:return new NewAccountBoImpl();
            case DEPOSIT:return new DepositBoImpl();
            case DISTRIBUTE:return new DistributionBoImpl();
            case DPTRANS:return new DepositTransBoImpl();
            //case INTEREST:return new InterestDAOImpl();
            case ITEM:return new ItemBoImpl();
            case LOAN:return new NewLoanBoImpl();
            case MEMBER:return new NewMemberBoImpl();
            case SERVICE:return new NewServiceBoImpl();
            //case SUPPLIES:return new SuppliesDAOImpl();
            case PAYLOAN:return new PayLoanBoImpl();
            case PAYSER:return new PayServiceBoImpl();
            case INTEREST:return new InterestBoImpl();
            case SEARCH:return new SearchBoImpl();
            case SUPHOME:return new SupHomeBoImpl();
            case SUPPLIER:return new SupplierBoImpl();
            case SUPPLIES:return new SuppliesBoImpl();
            case DEPFORM:return new DepositFormBoImpl();
            case LOANFORM:return new LoanFormBoImpl();
            //case ORDER_DETAILS:return new OrderDetailDAOImpl();
            //case QUERY_DAO:return new QueryDAOImpl();
            default:return null;
        }
    }
}
