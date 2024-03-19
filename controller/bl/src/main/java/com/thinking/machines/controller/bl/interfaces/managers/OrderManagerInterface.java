package com.thinking.machines.controller.bl.managers;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public interface OrderManagerInterface
{
public void addOrder(OrderInterface order) throws BLException;
public void updateOrder(OrderInterface order) throws BLException;
public void deleteOrder(int orderId) throws BLException;
public Set<OrderInterface> getAllUndeliveredOrders() throws BLException;
public Set<OrderInterface> getAllDeliveredOrders() throws BLException;
public OrderInterface getOrderByOrderId(int orderId) throws BLException;
public boolean orderIdExists(int orderId);
public Set<OrderInterface> getAllOrders();
}