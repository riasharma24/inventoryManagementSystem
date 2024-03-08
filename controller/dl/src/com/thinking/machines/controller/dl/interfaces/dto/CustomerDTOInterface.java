package com.thinking.machines.controller.dl.interfaces.dto;
import java.util.*;


public interface CustomerDTOInterface extends java.io.Serializable,Comparable<CustomerDTOInterface>
{
public void setName(String name);
public String getName();
public void setCustomerId(String customerId);
public String getCustomerId();
public void setContactNumber(String contactNumber);
public String getContactNumber();
public void addOrder(int orderId);
public List<Integer> getOrders();
}