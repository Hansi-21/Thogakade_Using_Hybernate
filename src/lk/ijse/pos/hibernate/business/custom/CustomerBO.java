package lk.ijse.pos.hibernate.business.custom;

import lk.ijse.pos.hibernate.business.SuperBO;
import lk.ijse.pos.hibernate.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean add(CustomerDTO customerDTO) throws Exception;

    public List<CustomerDTO> findAll() throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(CustomerDTO customerDTO) throws Exception;
}
