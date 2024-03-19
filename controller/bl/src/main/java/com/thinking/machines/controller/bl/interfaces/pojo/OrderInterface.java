package com.thinking.machines.controller.bl.interfaces.pojo;

public interface OrderInterface extends java.io.Serializable,Comparable<OrderInterface>
{
public void setOrderId(int orderId);
public int getOrderId();
public void setProductId(int productId);
public int getProductId();
public void setDeliveryStatus(boolean deliveryStatus);
public boolean getDeliveryStatus();
}