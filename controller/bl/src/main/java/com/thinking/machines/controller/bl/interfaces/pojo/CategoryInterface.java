package com.thinking.machines.controller.bl.interfaces.pojo;

public interface CategoryInterface extends java.io.Serializable,Comparable<CategoryInterface>
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}