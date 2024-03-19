package com.thinking.machines.controller.bl.interfaces.pojo;
import java.util.*;

public interface CustomerInterface extends java.io.Serializable,Comparable<CustomerInterface>
{
public void setCustomerId(String customerId);
public String getCustomerId();
public void setName(String name);
public String getName();
public void setContactNumber(String contactNumber);
public String getContactNumber();
public void addOrder(int orderId);
public List<Integer> getOrders();
}