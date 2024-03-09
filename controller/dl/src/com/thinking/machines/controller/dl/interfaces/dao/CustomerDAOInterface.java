package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public interface CustomerDAOInterface
{
public void add(CustomerDTOInterface customerDTO) throws DAOException;
public void update(CustomerDTOInterface customerDTO) throws DAOException;
public void delete(String customerId) throws DAOException;
public CustomerDTOInterface getByName(String name) throws DAOException;
public CustomerDTOInterface getByCustomerId(String customerId) throws DAOException;
public CustomerDTOInterface getByContactNumber(String contactNumber) throws DAOException;
public void placeOrder(CustomerDTOInterface customerDTO) throws DAOException;
public Set<CustomerDTOInterface> getAll() throws DAOException;
public boolean nameExists(String name) throws DAOException;
public boolean contactNumberExists(String contactNumber) throws DAOException;
public boolean customerIdExists(String customerId) throws DAOException;
public int getCount() throws DAOException;
}