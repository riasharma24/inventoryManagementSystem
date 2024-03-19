package com.thinking.machines.controller.bl.pojo;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import java.util.*;

public class Customer implements CustomerInterface
{
private String customerId;
private String name;
private String contactNumber;
private List<Integer> orders;

public Customer()
{
this.customerId="";
this.name="";
this.contactNumber="";
this.orders=new ArrayList<>();
}

public void setCustomerId(String customerId)
{
this.customerId=customerId;
}

public String getCustomerId()
{
return this.customerId;
}

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setContactNumber(String contactNumber)
{
this.contactNumber=contactNumber;
}

public String getContactNumber()
{
return this.contactNumber;
}

public void addOrder(int orderId)
{
this.orders.add(orderId);
}

public List<Integer> getOrders()
{
return this.orders;
}

public boolean equals(Object other)
{
if(!(other instanceof CustomerInterface))return false;
CustomerInterface customer=(CustomerInterface)other;
return this.customerId.equalsIgnoreCase(customer.getCustomerId());
}

public int compareTo(CustomerInterface customer)
{
return this.customerId.compareTo(customer.getCustomerId());
}

public int hashCode()
{
return this.customerId.hashCode();
}

}