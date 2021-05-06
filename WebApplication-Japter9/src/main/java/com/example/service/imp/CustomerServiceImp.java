package com.example.service.imp;

import com.example.dao.CustomerDao;
import com.example.dao.imp.CustomerImp;
import com.example.domain.Customer;
import com.example.service.CustomerService;
import com.example.service.ServiceException;

public class CustomerServiceImp implements CustomerService {
    CustomerDao cd1 = new CustomerImp();
    @Override
    public void register(Customer c1) throws ServiceException {
        if (cd1.findBypk(c1.getId()) != null) {
            throw new ServiceException("customer id already exist");
        }
        cd1.create(c1);

    }

    @Override
    public boolean login(Customer c1) {
        Customer dbCustomer = cd1.findBypk(c1.getId());
        if (dbCustomer != null && dbCustomer.getPassword().equals( c1.getPassword() )) {
            c1.setName(dbCustomer.getName());
            c1.setBirthday(dbCustomer.getBirthday());
            c1.setAddress(dbCustomer.getAddress());
            c1.setPhone(dbCustomer.getPhone());
            return true;
        }
        return false;
    }
}
