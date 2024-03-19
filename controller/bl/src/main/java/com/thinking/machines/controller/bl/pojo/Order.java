package com.thinking.machines.controller.bl.pojo;
import com.thinking.machines.controller.bl.interfaces.pojo.*;

public class Order implements OrderInterface
{
private int orderId;
private int productId;
private boolean deliveryStatus;

public Order()
{
this.orderId=0;
this.productId=0;
this.deliveryStatus=false;
}

public void setOrderId(int orderId)
{
this.orderId=orderId;
}

public int getOrderId()
{
return this.orderId;
}

public void setProductId(int productId)
{
this.productId=productId;
}

public int getProductId()
{
return this.productId;
}

public void setDeliveryStatus(boolean deliveryStatus)
{
this.deliveryStatus=deliveryStatus;
}

public boolean getDeliveryStatus()
{
return this.deliveryStatus;
}

public boolean equals(Object other)
{
if(!(other instanceof OrderInterface))return false;
OrderInterface order=(OrderInterface)other;
return this.orderId==order.getOrderId();
}

public int hashCode()
{
return this.orderId;
}

public int compareTo(OrderInterface order)
{
return this.orderId-order.getOrderId();
}

}