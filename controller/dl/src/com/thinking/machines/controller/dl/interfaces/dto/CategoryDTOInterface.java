package com.thinking.machines.controller.dl.interfaces.dto;

public interface CategoryDTOInterface extends java.io.Serializable,Comparable<CategoryDTOInterface>
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}