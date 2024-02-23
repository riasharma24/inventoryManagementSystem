package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public interface OrderDAOInterface
{
public void add(OrderDTOInterface orderDTO) throws DAOException;
public void update(OrderDTOInterface orderDTO) throws DAOException;
public void delete(int orderId) throws DAOException;
public Set<OrderDTOInterface> getAll() throws DAOException;
public Set<OrderDTOInterface> getAllUndelivered() throws DAOException;
public Set<OrderDTOInterface> getAllDelivered() throws DAOException;
public OrderDTOInterface getByOrderId(int orderId) throws DAOException;
public boolean orderIdExists(int orderId);
}