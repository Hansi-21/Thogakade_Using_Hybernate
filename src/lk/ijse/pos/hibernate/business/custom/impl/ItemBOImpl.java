package lk.ijse.pos.hibernate.business.custom.impl;

import lk.ijse.pos.hibernate.business.custom.ItemBO;
import lk.ijse.pos.hibernate.dao.DAOFactory;
import lk.ijse.pos.hibernate.dao.DAOType;
import lk.ijse.pos.hibernate.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.hibernate.dto.ItemDTO;
import lk.ijse.pos.hibernate.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);

    @Override
    public boolean add(ItemDTO itemDTO) throws Exception {
        return itemDAO.add(new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQty()
        ));

    }

    @Override
    public List<ItemDTO> findAll() throws Exception {
        List<Item> all = itemDAO.findAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();


        for (Item item : all) {
            itemDTOS.add(new ItemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQty()
            ));
        }
        return itemDTOS;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return itemDAO.delete(s);
    }

    @Override
    public boolean update(ItemDTO itemDTO) throws Exception {
        return itemDAO.update(new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQty()
        ));
    }
}
