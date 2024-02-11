package com.thinking.machines.conroller.dl.dto;
import com.thinking.machines.controller.dl.interfaces.dto.*;

public class ProductDTO implements ProductDTOInterface
{
private int productId;
private String name;
private String category;
private int price;
public void setProductID(int prodctId)
{
this.productId=productId;
}

public int getProductID()
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

public void setCategory(String category)
{
this.category=category;
}

public String getCategory()
{
return this.category;
}

public void setPrice(int price)
{
this.price=price;
}

public int getPrice()
{
return this.price;
}

}