package com.thinking.machines.controller.dl.dto;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;

public class OrderDTO implements OrderDTOInterface
{
private int productId;
private int orderId;
private boolean deliveryStatus;

public void setProductId(int productId)
{
this.productId=productId;
}

public int getProductId()
{
return this.productId;
}

public void setOrderId(int orderId)
{
this.orderId=orderId;
}

public int getOrderId()
{
return this.orderId;
}

public void setDeliveryStatus(boolean deliveryStatus)
{
this.deliveryStatus=deliveryStatus;
}

public boolean getDeliveryStatus()
{
return this.deliveryStatus;
}

public int compareTo(OrderDTOInterface orderDTO)
{
return this.orderId-orderDTO.getOrderId();
}

public boolean equals(Object other)
{
if(!(other instanceof OrderDTOInterface))return false;
OrderDTOInterface orderDTO=(OrderDTOInterface)other;
return this.orderId==orderDTO.getOrderId();
}

public int hashCode()
{
return this.orderId;
}

}