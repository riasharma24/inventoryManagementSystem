package com.thinking.machines.controller.bl.interfaces.managers;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public interface ProductManagerInterface
{
public void addProduct(ProductInterface product) throws BLException;
public void updateProduct(ProductInterface product) throws BLException;
public void deleteProduct(int productId) throws BLException;
public ProductInterface getProductByProductId(int productId) throws BLException;
public ProductInterface getProductByName(String name)throws BLException;
public Set<ProductInterface> getProductsByCategory(int categoryCode) throws BLException;
public Set<ProductInterface> getProducts();
public boolean productNameExists(String name);
public boolean productProductIdExists(int productId);
public void decreaseNumberOfUnits(int productId) throws BLException;
public void increaseNumberOfUnits(int productId) throws BLException;
public int getProductsCount();
public int getProductsCountByCategory(int categoryCode) throws BLException;
}