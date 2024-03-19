package com.thinking.machines.controller.bl.pojo;
import com.thinking.machines.controller.bl.interfaces.pojo.*;

public class Product implements ProductInterface
{
private int productId;
private String name;
private int categoryCode;
private int price;
private int numberOfUnits;

public Product()
{
this.productId=0;
this.name="";
this.categoryCode=0;
this.price=0;
this.numberOfUnits=0;
}

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

public boolean equals(Object other)
{
if(!(other instanceof ProductInterface))return false;
ProductInterface product=(ProductInterface)other;
return this.productId==product.getProductId();
}

public int hashCode()
{
return this.productId;
}

public int compareTo(ProductInterface product)
{
return this.productId-product.getProductId();
}

}