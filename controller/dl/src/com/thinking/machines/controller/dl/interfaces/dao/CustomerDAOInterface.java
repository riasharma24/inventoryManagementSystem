package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dto.*;
import java.util.*;

public interface CustomerDAOInterface
{
public void add(CustomerDTOInterface customerDTO) throws new DAOException;
public void update(CustomerDTOInterface customerDTO) throws new DAOException;
public void delete(CustomerDTOInterface customerDTO) throws new DAOException;
public CustomerDTOInterface getByName(String name) throws new DAOException;
public CustomerDTOInterface getByCustomerId(String customerId) throws new DAOException;
public CustomerDTOInterface getByContactNumber(String contactNumber) throws new DAOException;
public Set<CustomerDTOInterface> getAll() throws new DAOException;
public boolean nameExists(String name) throws new DAOException;
public boolean contactNumberExists(String contactNumber) throws new DAOException;
public boolean customerIdExists(String customerId) throws new DAOException;
}