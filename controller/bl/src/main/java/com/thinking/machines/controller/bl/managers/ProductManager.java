package com.thinking.machines.controller.bl.managers;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class ProductManager implements ProductManagerInterface
{
private Map<String,ProductInterface> nameWiseProductsMap;
private Map<Integer,ProductInterface> productIdWiseProductsMap;
private Set<ProductInterface> productsSet;
private static ProductManagerInterface productManager;

private ProductManager() throws BLException
{
populateDatastructures();
}

private void populateDatastructures() throws BLException
{
this.nameWiseProductsMap=new HashMap<>();
this.productIdWiseProductsMap=new HashMap<>();
this.productsSet=new TreeSet<>();
try{
Set<ProductDTOInterface> products=new ProductDAO().getAll();
ProductInterface product;
for(ProductDTOInterface productDTO : products)
{
product=new Product();
product.setProductId(productDTO.getProductId());
product.setName(productDTO.getName());
product.setCategoryCode(productDTO.getCategoryCode());
product.setPrice(productDTO.getPrice());
product.setNumberOfUnits(productDTO.getNumberOfUnits());
this.productsSet.add(product);
this.productIdWiseProductsMap.put(productDTO.getProductId(),product);
this.nameWiseProductsMap.put(productDTO.getName().toUpperCase(),product);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public static ProductManagerInterface getProductManager() throws BLException
{
if(productManager==null)productManager=new ProductManager();
return productManager;
}

public void addProduct(ProductInterface product) throws BLException
{
BLException blException;
blException=new BLException();
if(product==null)
{
blException.setGenericException("Product cannot be null");
throw blException;
}
String name=product.getName();
if(name==null)
{
blException.addException("name","Name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)
{
blException.addException("name","Name cannot be zero length");
}
if(nameWiseProductsMap.containsKey(name))
{
blException.addException("name","Product already exists");
}
}
int categoryCode=product.getCategoryCode();
if(!new CategoryDAO().codeExists(categoryCode))
{
blException.addException("code","Invalid category code");
}
int price=product.getPrice();
if(price<0)
{
blException.addException("price","Invalid price");
}
int numberOfUnits=product.getNumberOfUnits();
if(numberOfUnits<=0)
{
blException.addException("numberOfUnits","Invalid number of units");
}
if(blException.hasExceptions())
{
throw blException;
}
try{
ProductDTOInterface productDTO=new ProductDTO();
productDTO.setName(name);
productDTO.setCategoryCode(categoryCode);
productDTO.setPrice(price);
productDTO.setNumberOfUnits(numberOfUnits);
new ProductDAO().add(productDTO);
product.setProductId(productDTO.getProductId());
ProductInterface dsProduct=new Product();
dsProduct.setProductId(productDTO.getProductId());
dsProduct.setName(name);
dsProduct.setCategoryCode(categoryCode);
dsProduct.setPrice(price);
dsProduct.setNumberOfUnits(numberOfUnits);
this.productIdWiseProductsMap.put(productDTO.getProductId(),dsProduct);
this.nameWiseProductsMap.put(name.toUpperCase(),dsProduct);
this.productsSet.add(dsProduct);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void updateProduct(ProductInterface product) throws BLException
{
BLException blException;
blException=new BLException();
if(product==null)
{
blException.setGenericException("Product cannot be null");
throw blException;
}
int productId=product.getProductId();
if(productIdWiseProductsMap.containsKey(productId)==false)
{
blException.addException("product id","Invalid product id");
}
String name=product.getName();
if(name==null)
{
blException.addException("name","Product name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)
{
blException.addException("name","Product name cannot be of zero length");
}
if(nameWiseProductsMap.containsKey(name.toUpperCase()))
{
blException.addException("name","Product already exists");
}
}
int categoryCode=product.getCategoryCode();
if(new CategoryDAO().codeExists(categoryCode)==false)
{
blException.addException("categoryCode","Invalid category code");
}
int price=product.getPrice();
if(price<0)
{
blException.addException("price","Invalid price");
}
int numberOfUnits=product.getNumberOfUnits();
if(numberOfUnits<=0)
{
blException.addException("numberOfUnits","Invalid number of units");
}
if(blException.hasExceptions())
{
throw blException;
}
try{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
ProductDTOInterface productDTO=new ProductDTO();
productDTO.setName(name);
productDTO.setProductId(productId);
productDTO.setCategoryCode(categoryCode);
productDTO.setPrice(price);
productDTO.setNumberOfUnits(numberOfUnits);
productDAO.update(productDTO);
ProductInterface prev=productIdWiseProductsMap.get(productId);
this.productIdWiseProductsMap.remove(productId);
this.nameWiseProductsMap.remove(prev.getName());
this.productsSet.remove(prev);
ProductInterface dsProduct=new Product();
dsProduct.setProductId(productId);
dsProduct.setName(name);
dsProduct.setCategoryCode(categoryCode);
dsProduct.setPrice(price);
dsProduct.setNumberOfUnits(numberOfUnits);
this.productIdWiseProductsMap.put(productId,dsProduct);
this.nameWiseProductsMap.put(name,dsProduct);
this.productsSet.add(dsProduct);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void deleteProduct(int productId) throws BLException
{
BLException blException;
blException=new BLException();
if(productId<=0)
{
blException.setGenericException("Invalid product id");
throw blException;
}
if(productIdWiseProductsMap.containsKey(productId)==false)
{
blException.setGenericException("Invalid product id");
throw blException;
}
try{
ProductDAOInterface productDAO=new ProductDAO();
productDAO.delete(productId);
ProductInterface product=productIdWiseProductsMap.get(productId);
this.productIdWiseProductsMap.remove(productId);
this.nameWiseProductsMap.remove(product.getName());
this.productsSet.remove(product);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public ProductInterface getProductByProductId(int productId) throws BLException
{
BLException blException;
blException=new BLException();
if(productId<=0)
{
blException.setGenericException("Invalid product id");
throw blException;
}
if(productIdWiseProductsMap.containsKey(productId)==false)
{
blException.setGenericException("Invalid product id");
throw blException;
}
ProductInterface product=new Product();
ProductInterface dsProduct=productIdWiseProductsMap.get(productId);
product.setName(dsProduct.getName());
product.setProductId(dsProduct.getProductId());
product.setCategoryCode(dsProduct.getCategoryCode());
product.setPrice(dsProduct.getPrice());
product.setNumberOfUnits(dsProduct.getNumberOfUnits());
return product;
}

public ProductInterface getProductByName(String name)throws BLException
{
BLException blException;
blException=new BLException();
if(name==null)
{
blException.setGenericException("Name of the product cannot be null");
throw blException;
}
name=name.trim();
if(name.length()==0)
{
blException.setGenericException("Name of the product cannot be of zero length");
throw blException;
}
if(nameWiseProductsMap.containsKey(name.toUpperCase())==false)
{
blException.setGenericException("Invalid product");
throw blException;
}
ProductInterface product=new Product();
ProductInterface dsProduct=nameWiseProductsMap.get(name.toUpperCase());
product.setName(dsProduct.getName());
product.setProductId(dsProduct.getProductId());
product.setCategoryCode(dsProduct.getCategoryCode());
product.setPrice(dsProduct.getPrice());
product.setNumberOfUnits(dsProduct.getNumberOfUnits());
return product;
}

public Set<ProductInterface> getProductsByCategory(int categoryCode) throws BLException
{
BLException blException;
blException=new BLException();
if(!(new CategoryDAO().codeExists(categoryCode)))
{
blException.setGenericException("Category does not exist");
throw blException;
}
Set<ProductInterface> products=new TreeSet<>();
ProductInterface product;
for(ProductInterface dsProduct : productsSet)
{
if(dsProduct.getCategoryCode()==categoryCode)
{
product=new Product();
product.setProductId(dsProduct.getProductId());
product.setName(dsProduct.getName());
product.setCategoryCode(dsProduct.getCategoryCode());
product.setPrice(dsProduct.getPrice());
product.setNumberOfUnits(dsProduct.getNumberOfUnits());
products.add(product);
}
}
return products;
}

public Set<ProductInterface> getProducts()
{
Set<ProductInterface> products=new TreeSet<>();
ProductInterface product;
for(ProductInterface dsProduct : productsSet)
{
product=new Product();
product.setProductId(dsProduct.getProductId());
product.setName(dsProduct.getName());
product.setCategoryCode(dsProduct.getCategoryCode());
product.setPrice(dsProduct.getPrice());
product.setNumberOfUnits(dsProduct.getNumberOfUnits());
products.add(product);
}
return products;
}

public boolean productNameExists(String name)
{
if(name==null)return false;
name=name.trim();
if(name.length()==0)return false;
return this.nameWiseProductsMap.containsKey(name.toUpperCase());
}

public boolean productProductIdExists(int productId)
{
if(productId<=0)return false;
return productIdWiseProductsMap.containsKey(productId);
}

public void decreaseNumberOfUnits(int productId) throws BLException
{
BLException blException;
blException=new BLException();
if(productIdWiseProductsMap.containsKey(productId)==false)
{
blException.setGenericException("Invalid product id");
throw blException;
}
ProductInterface product=productIdWiseProductsMap.get(productId);
int numberOfUnits=product.getNumberOfUnits();
numberOfUnits--;
product.setNumberOfUnits(numberOfUnits);
}

public void increaseNumberOfUnits(int productId) throws BLException
{
BLException blException;
blException=new BLException();
if(productIdWiseProductsMap.containsKey(productId)==false)
{
blException.setGenericException("Invalid product id");
throw blException;
}
ProductInterface product=productIdWiseProductsMap.get(productId);
int numberOfUnits=product.getNumberOfUnits();
numberOfUnits++;
product.setNumberOfUnits(numberOfUnits);
}

public int getProductsCount()
{
return this.productsSet.size();
}

public int getProductsCountByCategory(int categoryCode) throws BLException
{
BLException blException;
blException=new BLException();
if(new CategoryDAO().codeExists(categoryCode)==false)
{
blException.setGenericException("Invalid category");
throw blException;
}
int count=0;
for(ProductInterface product : this.productsSet)
{
if(product.getCategoryCode()==categoryCode)count++;
}
return count;
}


}