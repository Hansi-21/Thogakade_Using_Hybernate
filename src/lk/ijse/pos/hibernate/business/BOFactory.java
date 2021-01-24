package lk.ijse.pos.hibernate.business;

import lk.ijse.pos.hibernate.business.custom.impl.CustomerBOImpl;
import lk.ijse.pos.hibernate.business.custom.impl.ItemBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            default:
                return null;
        }
    }
}
