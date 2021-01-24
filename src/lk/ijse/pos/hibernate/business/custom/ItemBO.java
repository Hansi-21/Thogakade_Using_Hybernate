package lk.ijse.pos.hibernate.business.custom;

import lk.ijse.pos.hibernate.business.SuperBO;
import lk.ijse.pos.hibernate.dto.ItemDTO;
import lk.ijse.pos.hibernate.entity.Item;

import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean add(ItemDTO itemDTO) throws Exception;

    public List<ItemDTO> findAll() throws Exception;

    public boolean delete(String s) throws Exception;

    public boolean update(ItemDTO itemDTO) throws Exception;
}
