package com.thinking.machines.controller.dl.interfaces.dto;
import com.thinking.machines.controller.dl.dto.*;

public interface OrderDTOInterface extends java.io.Serializable,Comparable<OrderDTOInterface>
{
public void setProductId(int productId);
public int getProductId();
public void setOrderId(int orderId);
public int getOrderId();
public void setDeliveryStatus(boolean deliveryStatus);
public boolean getDeliveryStatus();
}