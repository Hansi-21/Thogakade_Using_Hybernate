package lk.ijse.pos.hibernate.business.custom.impl;

import lk.ijse.pos.hibernate.business.BOFactory;
import lk.ijse.pos.hibernate.business.BOType;
import lk.ijse.pos.hibernate.business.custom.CustomerBO;
import lk.ijse.pos.hibernate.dao.DAOFactory;
import lk.ijse.pos.hibernate.dao.DAOType;
import lk.ijse.pos.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.hibernate.dto.CustomerDTO;
import lk.ijse.pos.hibernate.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public boolean add(CustomerDTO customerDTO) throws Exception {
        return customerDAO.add(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        ));
    }

    @Override
    public List<CustomerDTO> findAll() throws Exception {
        List<Customer> all = customerDAO.findAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        CustomerDTO customerDTO = null;

        for (Customer customer : all) {
            dtoList.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return dtoList;

    }

    @Override
    public boolean delete(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        ));
    }

}
