package com.thinking.machines.controller.dl.dto;
import com.thinking.machines.controller.dl.interfaces.dto.*;

public class CustomerDTO implements CustomerDTOInterface
{
private String name="";
private String customerId="";
private String contactNumber="";

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setCustomerId(String customerId)
{
this.customerId=customerId;
}

public String getCustomerId()
{
return this.customerId;
}

public void setContactNumber(String contactNumber)
{
this.contactNumber=contactNumber;
}

public String getContactNumber()
{
return this.contactNumber;
}

public int compareTo(CustomerDTOInterface customerDTO)
{
return this.customerId.compareTo(customerDTO.getCustomerId());
}

public boolean equals(Object other)
{
if(!(other instanceof CustomerDTOInterface))return false;
CustomerDTOInterface customerDTO=(CustomerDTOInterface)other;
return this.customerId.equalsIgnoreCase(customerDTO.getCustomerId());
}

public int hashCode()
{
return this.customerId.toUpperCase().hashCode();
} 

}