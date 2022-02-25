package com.bjpowernode.mapper;

import com.bjpowernode.domain.Customer;
import com.bjpowernode.domain.DicType;

import java.util.List;

public interface CustomerMapper {

     Customer getCustomerByName(String commpany);


    int insert(Customer customer);
}
