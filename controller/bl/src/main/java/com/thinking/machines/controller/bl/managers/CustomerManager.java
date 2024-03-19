package com.thinking.machines.controller.bl.managers;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CustomerManager implements CustomerManagerInterface
{
private static CustomerManager customerManager=null;
private Map<String,CustomerInterface> customerIdWiseCustomersMap;
private Map<String,CustomerInterface> contactNumberWiseCustomersMap;
private Set<CustomerInterface> customersSet;

private CustomerManager() throws BLException
{
populateDataStructures();
}

private void populateDataStructures()throws BLException
{
this.customerIdWiseCustomersMap=new HashMap<>();
this.contactNumberWiseCustomersMap=new HashMap<>();
this.customersSet=new TreeSet<>();
try{
Set<CustomerDTOInterface> dlCustomers=new CustomerDAO().getAll();
CustomerInterface customer;
for(CustomerDTOInterface dlCustomer : dlCustomers)
{
customer=new Customer();
customer.setCustomerId(dlCustomer.getCustomerId());
customer.setName(dlCustomer.getName());
customer.setContactNumber(dlCustomer.getContactNumber());
List<Integer> orders=dlCustomer.getOrders();
for(int orderId : orders)
{
customer.addOrder(orderId);
}
this.customerIdWiseCustomersMap.put(customer.getCustomerId().toUpperCase(),customer);
this.contactNumberWiseCustomersMap.put(customer.getContactNumber().toUpperCase(),customer);
this.customersSet.add(customer);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public static CustomerManagerInterface getCustomerManager() throws BLException
{
if(customerManager==null)customerManager=new CustomerManager();
return customerManager;
}

public void addCustomer(CustomerInterface customer) throws BLException
{
BLException blException;
blException=new BLException();
if(customer==null)
{
blException.setGenericException("Customer cannot be null");
throw blException;
}
String name=customer.getName();
if(name==null)
{
blException.addException("name","Name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)blException.addException("name","Length of name cannot be zero");
}
String contactNumber=customer.getContactNumber();
if(contactNumber==null)
{
blException.addException("contact number","Contact number cannot be null");
}
else
{
contactNumber=contactNumber.trim();
if(contactNumber.length()==0)blException.addException("contactNumber","Contact number cannot be of zero length");
}
if(contactNumberWiseCustomersMap.containsKey(contactNumber.toUpperCase()))
{
blException.addException("contact number","Contact number already exists.");
}
if(blException.hasExceptions())
{
throw blException;
}
try{
CustomerInterface dsCustomer=new Customer();
CustomerDTOInterface customerDTO=new CustomerDTO();
customerDTO.setName(customer.getName());
customerDTO.setContactNumber(customer.getContactNumber());
new CustomerDAO().add(customerDTO);
customer.setCustomerId(customerDTO.getCustomerId());
dsCustomer.setCustomerId(customerDTO.getCustomerId());
dsCustomer.setName(customerDTO.getName());
dsCustomer.setContactNumber(customerDTO.getContactNumber());
this.customerIdWiseCustomersMap.put(dsCustomer.getCustomerId().toUpperCase(),dsCustomer);
this.contactNumberWiseCustomersMap.put(dsCustomer.getContactNumber().toUpperCase(),dsCustomer);
this.customersSet.add(dsCustomer);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void updateCustomer(CustomerInterface customer) throws BLException
{
BLException blException;
blException=new BLException();
if(customer==null)
{
blException.setGenericException("Customer cannot be null");
throw blException;
}
String customerId=customer.getCustomerId();
if(customerId==null)
{
blException.addException("customerId","Customer id cannot be null");
}
else
{
customerId=customerId.trim();
if(customerId.length()==0)
{
blException.addException("customerId","Customer id cannot be of zero length");
}
}
String name=customer.getName();
if(name==null)
{
blException.addException("name","Name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)blException.addException("name","Name cannot be of zero length");
}
String contactNumber=customer.getContactNumber();
if(contactNumber==null)
{
blException.addException("contactNumber","Contact number cannot be null");
}
else
{
contactNumber=contactNumber.trim();
if(contactNumber.length()==0)blException.addException("contactNumber","Contact number cannot be of zero length");
}
List<Integer> orders=customer.getOrders();
if(blException.hasExceptions())
{
throw blException;
}
try{
CustomerDTOInterface customerDTO=new CustomerDTO();
customerDTO.setCustomerId(customerId);
customerDTO.setContactNumber(contactNumber);
customerDTO.setName(name);
for(int order : orders)
{
customerDTO.addOrder(order);
}
new CustomerDAO().update(customerDTO);
CustomerInterface prev=customerIdWiseCustomersMap.get(customerId.toUpperCase());
this.customerIdWiseCustomersMap.remove(prev.getCustomerId().toUpperCase());
this.contactNumberWiseCustomersMap.remove(prev.getContactNumber().toUpperCase());
this.customersSet.remove(prev);
CustomerInterface dsCustomer=new Customer();
dsCustomer.setName(name.toUpperCase());
dsCustomer.setContactNumber(contactNumber.toUpperCase());
dsCustomer.setCustomerId(customerId.toUpperCase());
for(int order : orders)dsCustomer.addOrder(order);
this.customerIdWiseCustomersMap.put(customerId.toUpperCase(),dsCustomer);
this.contactNumberWiseCustomersMap.put(contactNumber.toUpperCase(),dsCustomer);
this.customersSet.add(dsCustomer);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void deleteCustomer(String customerId) throws BLException
{
BLException blException;
blException=new BLException();
if(customerId==null)
{
blException.addException("customerId","Customer id cannot be null");
}
else
{
customerId=customerId.trim();
if(customerId.length()==0)
{
blException.addException("customerId","Customer id cannot be of zero length");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try{
new CustomerDAO().delete(customerId);
CustomerInterface prev=customerIdWiseCustomersMap.get(customerId.toUpperCase());
this.customerIdWiseCustomersMap.remove(prev.getCustomerId().toUpperCase());
this.contactNumberWiseCustomersMap.remove(prev.getContactNumber().toUpperCase());
this.customersSet.remove(prev);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public CustomerInterface getCustomerByCustomerId(String customerId) throws BLException
{
BLException blException;
blException=new BLException();
if(customerId==null)
{
blException.setGenericException("Customer id cannot be null");
throw blException;
}
if(customerIdWiseCustomersMap.containsKey(customerId.trim().toUpperCase())==false)
{
blException.setGenericException("Invalid customer id");
throw blException;
}
CustomerInterface customer=new Customer();
CustomerInterface dsCustomer=this.customerIdWiseCustomersMap.get(customerId.trim().toUpperCase());
customer.setName(dsCustomer.getName());
customer.setContactNumber(dsCustomer.getContactNumber());
customer.setCustomerId(dsCustomer.getCustomerId());
List<Integer> orders=dsCustomer.getOrders();
for(int order : orders)
{
customer.addOrder(order);
}
return customer;
}

public CustomerInterface getCustomerByContactNumber(String contactNumber) throws BLException
{
BLException blException;
blException=new BLException();
if(contactNumber==null)
{
blException.setGenericException("Contact number cannot be null");
throw blException;
}
if(contactNumberWiseCustomersMap.containsKey(contactNumber.trim().toUpperCase())==false)
{
blException.setGenericException("Invalid contact number");
throw blException;
}
CustomerInterface customer=new Customer();
CustomerInterface dsCustomer=this.contactNumberWiseCustomersMap.get(contactNumber.trim().toUpperCase());
customer.setName(dsCustomer.getName());
customer.setContactNumber(dsCustomer.getContactNumber());
customer.setCustomerId(dsCustomer.getCustomerId());
List<Integer> orders=dsCustomer.getOrders();
for(int order : orders)
{
customer.addOrder(order);
}
return customer;
}

public Set<CustomerInterface> getAllCustomers()
{
Set<CustomerInterface> customers=new TreeSet<>();
CustomerInterface customer;
for(CustomerInterface dsCustomer : customers)
{
customer=new Customer();
customer.setName(dsCustomer.getName());
customer.setContactNumber(dsCustomer.getContactNumber());
customer.setCustomerId(dsCustomer.getCustomerId());
List<Integer> orders=dsCustomer.getOrders();
for(int order : orders)customer.addOrder(order);
}
return customers;
}

public boolean customerIdExists(String customerId)
{
if(customerId==null)return false;
return this.customerIdWiseCustomersMap.containsKey(customerId.trim().toUpperCase());
}

public boolean customerContactNumberExists(String contactNumber)
{
if(contactNumber==null)return false;
return this.contactNumberWiseCustomersMap.containsKey(contactNumber.trim().toUpperCase());
}

public void placeOrder(CustomerInterface customer) throws BLException
{
BLException blException;
blException=new BLException();
if(customer==null)
{
blException.setGenericException("Customer cannot be null");
throw blException;
}
String customerId=customer.getCustomerId();
if(customerId==null)
{
blException.addException("customerId","Customer id cannot be null");
}
else
{
customerId=customerId.trim();
if(customerId.length()==0)
{
blException.addException("customerId","Customer id cannot be of zero length");
}
}
String name=customer.getName();
if(name==null)
{
blException.addException("name","Name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)blException.addException("name","Name cannot be of zero length");
}
String contactNumber=customer.getContactNumber();
if(contactNumber==null)
{
blException.addException("contactNumber","Contact number cannot be null");
}
else
{
contactNumber=contactNumber.trim();
if(contactNumber.length()==0)blException.addException("contactNumber","Contact number cannot be of zero length");
}
List<Integer> orders=customer.getOrders();
if(blException.hasExceptions())
{
throw blException;
}
try{
CustomerDTOInterface customerDTO=new CustomerDTO();
customerDTO.setCustomerId(customerId);
customerDTO.setContactNumber(contactNumber);
customerDTO.setName(name);
for(int order : orders)
{
customerDTO.addOrder(order);
}
new CustomerDAO().placeOrder(customerDTO);
CustomerInterface prev=customerIdWiseCustomersMap.get(customerId.toUpperCase());
this.customerIdWiseCustomersMap.remove(prev.getCustomerId().toUpperCase());
this.contactNumberWiseCustomersMap.remove(prev.getContactNumber().toUpperCase());
this.customersSet.remove(prev);
CustomerInterface dsCustomer=new Customer();
dsCustomer.setName(name.toUpperCase());
dsCustomer.setContactNumber(contactNumber.toUpperCase());
dsCustomer.setCustomerId(customerId.toUpperCase());
for(int order : orders)dsCustomer.addOrder(order);
this.customerIdWiseCustomersMap.put(customerId.toUpperCase(),dsCustomer);
this.contactNumberWiseCustomersMap.put(contactNumber.toUpperCase(),dsCustomer);
this.customersSet.add(dsCustomer);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

}