package lk.ijse.pos.hibernate.dao;

import lk.ijse.pos.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.hibernate.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();

            default:
                return null;
        }
    }
}
