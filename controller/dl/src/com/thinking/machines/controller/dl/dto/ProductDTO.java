package com.thinking.machines.controller.dl.dto;
import com.thinking.machines.controller.dl.interfaces.dto.*;

public class ProductDTO implements ProductDTOInterface
{
private int productId;
private String name;
private int categoryCode;
private int price;
private int numberOfUnits;

public void setProductId(int productId)
{
this.productId=productId;
}

public int getProductId()
{
return this.productId;
}

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setCategoryCode(int categoryCode)
{
this.categoryCode=categoryCode;
}

public int getCategoryCode()
{
return this.categoryCode;
}

public void setPrice(int price)
{
this.price=price;
}

public int getPrice()
{
return this.price;
}

public void setNumberOfUnits(int numberOfUnits)
{
this.numberOfUnits=numberOfUnits;
}

public int getNumberOfUnits()
{
return this.numberOfUnits;
}

}