package com.thinking.machines.controller.bl.interfaces.managers;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import java.util.*;

public interface CustomerManagerInterface
{
public void addCustomer(CustomerInterface customer) throws BLException;
public void updateCustomer(CustomerInterface customer) throws BLException;
public void deleteCustomer(String customerId) throws BLException;
public CustomerInterface getCustomerByCustomerId(String customerId) throws BLException;
public CustomerInterface getCustomerByContactNumber(String contactNumber) throws BLException;
public Set<CustomerInterface> getAllCustomers() throws BLException;
public boolean customerIdExists(String customerId);
public boolean customerContactNumberExists(String contactNumber);
public void placeOrder(CustomerInterface customer) throws BLException;
}