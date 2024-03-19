package com.thinking.machines.controller.bl.interfaces.pojo;

public interface ProductInterface extends java.io.Serializable,Comparable<ProductInterface>
{
public void setProductId(int productId);
public int getProductId();
public void setName(String name);
public String getName();
public void setCategoryCode(int code);
public int getCategoryCode();
public void setPrice(int price);
public int getPrice();
public void setNumberOfUnits(int numberOfUnits);
public int getNumberOfUnits();
}