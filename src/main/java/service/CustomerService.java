package service;

import dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDao dao;

    @Autowired
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

}
