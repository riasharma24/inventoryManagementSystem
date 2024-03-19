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

public class OrderManager implements OrderManagerInterface
{
private Map<Integer,OrderInterface> orderIdWiseOrdersMap;
private Set<OrderInterface> ordersSet;

private static OrderManagerInterface orderManager;
private OrderManager() throws BLException
{
populateDataStructures();
}

private void populateDataStructures() throws BLException
{
this.orderIdWiseOrdersMap=new HashMap<>();
this.ordersSet=new TreeSet<>();
try{
Set<OrderDTOInterface> dlOrders=new OrderDAO().getAll();
OrderInterface order;
for(OrderDTOInterface orderDTO : dlOrders)
{
order=new Order();
order.setOrderId(orderDTO.getOrderId());
order.setProductId(orderDTO.getProductId());
order.setDeliveryStatus(orderDTO.getDeliveryStatus());
this.orderIdWiseOrdersMap.put(orderDTO.getOrderId(),order);
this.ordersSet.add(order);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public static OrderManagerInterface getOrderManager() throws BLException
{
if(orderManager==null)orderManager=new OrderManager();
return orderManager;
}

public void addOrder(OrderInterface order) throws BLException
{
BLException blException=new BLException();
if(order==null)
{
blException.setGenericException("Order cannot be null");
throw blException;
}
int productId=order.getProductId();
if(new ProductDAO().productIdExists(productId)==false)
{
blException.addException("productId","Invalid product id");
throw blException;
}
if(blException.hasExceptions())
{
throw blException;
}
try{
OrderDTOInterface orderDTO=new OrderDTO();
orderDTO.setProductId(productId);
new OrderDAO().add(orderDTO);
order.setOrderId(orderDTO.getOrderId());
order.setDeliveryStatus(orderDTO.getDeliveryStatus());
OrderInterface dsOrder=new Order();
dsOrder.setProductId(productId);
dsOrder.setDeliveryStatus(orderDTO.getDeliveryStatus());
dsOrder.setOrderId(orderDTO.getOrderId());
this.orderIdWiseOrdersMap.put(orderDTO.getOrderId(),dsOrder);
this.ordersSet.add(dsOrder);
ProductManagerInterface productManager=ProductManager.getProductManager();
productManager.decreaseNumberOfUnits(productId);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void updateOrder(OrderInterface order) throws BLException
{
BLException blException=new BLException();
if(order==null)
{
blException.setGenericException("Order cannot be null");
throw blException;
}
int orderId=order.getOrderId();
if(orderId<=0)
{
blException.addException("orderId","Invalid order id");
throw blException;
}
if(orderIdWiseOrdersMap.containsKey(orderId)==false)
{
blException.addException("orderId","Invalid order id");
throw blException;
}
int productId=order.getProductId();
if(new ProductDAO().productIdExists(productId)==false)
{
blException.addException("productId","Invalid product id");
throw blException;
}
boolean deliveryStatus=order.getDeliveryStatus();
if(blException.hasExceptions())
{
throw blException;
}
try{
OrderDTOInterface orderDTO=new OrderDTO();
orderDTO.setOrderId(orderId);
orderDTO.setProductId(productId);
orderDTO.setDeliveryStatus(deliveryStatus);
new OrderDAO().update(orderDTO);
OrderInterface prev=orderIdWiseOrdersMap.get(orderId);
orderIdWiseOrdersMap.remove(orderId);
ordersSet.remove(prev);
OrderInterface dsOrder=new Order();
dsOrder.setOrderId(orderId);
dsOrder.setProductId(productId);
dsOrder.setDeliveryStatus(deliveryStatus);
ProductManagerInterface productManager=ProductManager.getProductManager();
productManager.increaseNumberOfUnits(productId);
productManager.decreaseNumberOfUnits(prev.getProductId());
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void deleteOrder(int orderId) throws BLException
{
BLException blException=new BLException();
if(orderId<=0)
{
blException.addException("orderId","Invalid order id");
throw blException;
}
if(orderIdWiseOrdersMap.containsKey(orderId)==false)
{
blException.addException("orderId","Invalid order id");
throw blException;
}
if(blException.hasExceptions())
{
throw blException;
}
try{
new OrderDAO().delete(orderId);
OrderInterface prev=orderIdWiseOrdersMap.get(orderId);
orderIdWiseOrdersMap.remove(orderId);
ordersSet.remove(prev);
ProductManagerInterface productManager=ProductManager.getProductManager();
productManager.decreaseNumberOfUnits(prev.getProductId());
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public Set<OrderInterface> getAllUndeliveredOrders()
{
Set<OrderInterface> orders=new TreeSet<>();
OrderInterface order;
for(OrderInterface dsOrder : ordersSet)
{
if(dsOrder.getDeliveryStatus()==true)continue;
order=new Order();
order.setOrderId(dsOrder.getOrderId());
order.setProductId(dsOrder.getOrderId());
order.setDeliveryStatus(dsOrder.getDeliveryStatus());
orders.add(order);
}
return orders;
}

public Set<OrderInterface> getAllDeliveredOrders()
{
Set<OrderInterface> orders=new TreeSet<>();
OrderInterface order;
for(OrderInterface dsOrder : ordersSet)
{
if(dsOrder.getDeliveryStatus()==false)continue;
order=new Order();
order.setOrderId(dsOrder.getOrderId());
order.setProductId(dsOrder.getOrderId());
order.setDeliveryStatus(dsOrder.getDeliveryStatus());
orders.add(order);
}
return orders;
}

public OrderInterface getOrderByOrderId(int orderId) throws BLException
{
BLException blException=new BLException();
if(orderIdWiseOrdersMap.containsKey(orderId)==false)
{
blException.setGenericException("Invalid order id");
throw blException;
}
OrderInterface order=new Order();
OrderInterface dsOrder=orderIdWiseOrdersMap.get(orderId);
order.setOrderId(dsOrder.getOrderId());
order.setProductId(dsOrder.getProductId());
order.setDeliveryStatus(dsOrder.getDeliveryStatus());
return order;
}

public boolean orderIdExists(int orderId)
{
return orderIdWiseOrdersMap.containsKey(orderId);
}

public Set<OrderInterface> getAllOrders()
{
Set<OrderInterface> orders=new TreeSet<>();
OrderInterface order;
for(OrderInterface dsOrder : ordersSet)
{
order=new Order();
order.setOrderId(dsOrder.getOrderId());
order.setProductId(dsOrder.getOrderId());
order.setDeliveryStatus(dsOrder.getDeliveryStatus());
orders.add(order);
}
return orders;
}


}