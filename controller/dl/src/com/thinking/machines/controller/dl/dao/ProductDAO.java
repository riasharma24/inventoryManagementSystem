package com.thinking.machines.controller.dl.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class ProductDAO implements ProductDAOInterface
{
private static String FILE_NAME="products.dat";

public void add(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO==null)throw new DAOException("Product cannot be null");

String name=productDTO.getName();
if(name==null)throw new DAOException("Product name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Product name cannot be null");

int categoryCode=productDTO.getCategoryCode();
if(categoryCode<=0)throw new DAOException("Invalid category");
if(!(new CategoryDAO().codeExists(categoryCode)))throw new DAOException("Invalid category code");

int price=productDTO.getPrice();
if(price<0)throw new DAOException("Invalid product price");

int numberOfUnits=productDTO.getNumberOfUnits();
if(numberOfUnits<=0) throw new DAOException("Invalid number of units.");

try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int productId=0;
int lastGeneratedProductId=0;
String lastGeneratedProductIdString="";
int recordCount=0;
String recordCountString="";
if(randomAccessFile.length()==0)
{
lastGeneratedProductId=0;
recordCount=0;
lastGeneratedProductIdString=String.valueOf(lastGeneratedProductId);
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedProductIdString.length()<10)lastGeneratedProductIdString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedProductIdString);
randomAccessFile.writeBytes("\n");
}
else
{
recordCountString=randomAccessFile.readLine();
lastGeneratedProductIdString=randomAccessFile.readLine();
recordCount=Integer.parseInt(recordCountString.trim());
lastGeneratedProductId=Integer.parseInt(lastGeneratedProductIdString.trim());
}

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
if(randomAccessFile.readLine().toUpperCase().equalsIgnoreCase(name))
{
randomAccessFile.close();
throw new DAOException("Product already exists.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
lastGeneratedProductId=Integer.parseInt(lastGeneratedProductIdString.trim());
lastGeneratedProductId++;
lastGeneratedProductIdString=String.valueOf(lastGeneratedProductId);

randomAccessFile.writeBytes(lastGeneratedProductIdString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(name);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(categoryCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(price));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(numberOfUnits));
randomAccessFile.writeBytes("\n");

randomAccessFile.seek(0);
recordCount=Integer.parseInt(recordCountString.trim());

recordCount++;
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedProductIdString.length()<10)lastGeneratedProductIdString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedProductIdString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
productDTO.setProductId(lastGeneratedProductId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO==null)throw new DAOException("Product cannot be null");
String name=productDTO.getName();
if(name==null)throw new DAOException("Product cannot be null");
name=name.trim();

int categoryCode=productDTO.getCategoryCode();
if(categoryCode<=0)throw new DAOException("Invalid category code");
if(!(new CategoryDAO().codeExists(categoryCode)))throw new DAOException("Invalid category code");

int productId=productDTO.getProductId();
if(productId<0)throw new DAOException("Invalid price");

int price=productDTO.getPrice();
if(price<0)throw new DAOException("Invalid price");

int numberOfUnits=productDTO.getNumberOfUnits();
if(numberOfUnits<0) throw new DAOException("Invalid number of units");

try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid details");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCategoryCode;
String fName;
int fProductId;
int fPrice;
int fNumberOfUnits;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fName.equalsIgnoreCase(name) && fProductId!=productId)
{
randomAccessFile.close();
throw new DAOException("Name exists against code : "+fProductId);
}
}

randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
tmpRandomAccessFile.writeBytes(productId+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(categoryCode+"\n");
tmpRandomAccessFile.writeBytes(price+"\n");
tmpRandomAccessFile.writeBytes(numberOfUnits+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(fProductId+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fCategoryCode+"\n");
tmpRandomAccessFile.writeBytes(fPrice+"\n");
tmpRandomAccessFile.writeBytes(fNumberOfUnits+"\n");
}
}

randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(int productId) throws DAOException
{
if(productId<=0)throw new DAOException("Invalid product id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String recordCountString=randomAccessFile.readLine();
int recordCount=Integer.parseInt(recordCountString.trim());
randomAccessFile.readLine();
String fName;
int fCategoryCode;
int fProductId;
int fPrice;
int fNumberOfUnits;
boolean productIdFound=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)productIdFound=true;
}

if(productIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid product id");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");

tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
continue;
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(fProductId)+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fCategoryCode)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fPrice)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fNumberOfUnits)+"\n");
}
}

randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}

randomAccessFile.seek(0);
recordCount--;
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public ProductDTOInterface getByProductId(int productId) throws DAOException
{
if(productId<=0)throw new DAOException("Invalid product Id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid product Id");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fProductId;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
ProductDTOInterface productDTO;
productDTO=new ProductDTO();
productDTO.setProductId(productId);
productDTO.setName(randomAccessFile.readLine());
productDTO.setCategoryCode(Integer.parseInt(randomAccessFile.readLine()));
productDTO.setPrice(Integer.parseInt(randomAccessFile.readLine()));
productDTO.setNumberOfUnits(Integer.parseInt(randomAccessFile.readLine()));
randomAccessFile.close();
return productDTO;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
throw new DAOException("Invalid product id");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public ProductDTOInterface getByName(String name) throws DAOException
{
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Name cannot be null");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid product name");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fProductId;
String fName;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
if(fName.equalsIgnoreCase(name))
{
ProductDTOInterface productDTO;
productDTO=new ProductDTO();
productDTO.setProductId(fProductId);
productDTO.setName(fName);
productDTO.setCategoryCode(Integer.parseInt(randomAccessFile.readLine()));
productDTO.setPrice(Integer.parseInt(randomAccessFile.readLine()));
productDTO.setNumberOfUnits(Integer.parseInt(randomAccessFile.readLine()));
randomAccessFile.close();
return productDTO;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
throw new DAOException("Invalid product name");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<ProductDTOInterface> getAll() throws DAOException
{
Set<ProductDTOInterface> products=new HashSet<>();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
String fName;
int fProductId;
int fCategoryCode;
int fPrice;
int fNumberOfUnits;
ProductDTOInterface productDTO;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
productDTO=new ProductDTO();
productDTO.setProductId(fProductId);
productDTO.setName(fName);
productDTO.setPrice(fPrice);
productDTO.setCategoryCode(fCategoryCode);
productDTO.setNumberOfUnits(fNumberOfUnits);
products.add(productDTO);
}
randomAccessFile.close();
return products;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean productIdExists(int productId)
{
if(productId<=0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fProductId;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
}catch(IOException ioException)
{
}
return false;
}

public boolean nameExists(String name)
{
if(name==null)return false;
name=name.trim();
if(name.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fName;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fName=randomAccessFile.readLine();
if(fName.equalsIgnoreCase(name))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
}catch(IOException ioException)
{
}
return false;
}


public Set<ProductDTOInterface> getByCategory(int categoryCode) throws DAOException
{
if(categoryCode<=0)throw new DAOException("Invalid category code");
if(new CategoryDAO().codeExists(categoryCode)==false)throw new DAOException("Invalid category code");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No such products added yet.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
Set<ProductDTOInterface> products=new HashSet<>();
ProductDTOInterface productDTO;
int fProductId;
String fName;
int fCategoryCode;
int fPrice;
int fNumberOfUnits;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(categoryCode==fCategoryCode)
{
productDTO=new ProductDTO();
productDTO.setProductId(fProductId);
productDTO.setName(fName);
productDTO.setCategoryCode(fCategoryCode);
productDTO.setPrice(fPrice);
productDTO.setNumberOfUnits(fNumberOfUnits);
products.add(productDTO);
}
}
randomAccessFile.close();
return products;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public int getCount()
{
int count=0;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
}catch(IOException ioException)
{
}
return count;
}

public int getCountByCategory(int categoryCode) throws DAOException
{
if(categoryCode<=0)throw new DAOException("Invalid category");
if(new CategoryDAO().codeExists(categoryCode)==false)throw new DAOException("Invalid  category");
int count=0;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.readLine();
randomAccessFile.readLine();
String fName;
int fCategoryCode;
int fProductId;
int fPrice;
int fNumberOfUnits;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fCategoryCode==categoryCode)count++;
}
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void decreaseNumberOfUnits(int productId) throws DAOException
{
if(productId<=0)throw new DAOException("Invalid product id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fProductId;
boolean productIdFound=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
productIdFound=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
if(productIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid product id");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
String fName;
int fCategoryCode;
int fPrice;
int fNumberOfUnits;
boolean isEmpty=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
fNumberOfUnits--;
if(fNumberOfUnits==0)
{
isEmpty=true;
continue;
}
}
tmpRandomAccessFile.writeBytes(String.valueOf(fProductId)+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fCategoryCode)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fPrice)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fNumberOfUnits)+"\n");
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
if(isEmpty)
{
randomAccessFile.seek(0);
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount--;
randomAccessFile.seek(0);
String recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString+"\n");
}
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void increaseNumberOfUnits(int productId) throws DAOException
{
if(productId<=0)throw new DAOException("Invalid product id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fProductId;
boolean productIdFound=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
productIdFound=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
if(productIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid product id");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
String fName;
int fCategoryCode;
int fPrice;
int fNumberOfUnits;
boolean isEmpty=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fProductId=Integer.parseInt(randomAccessFile.readLine());
fName=randomAccessFile.readLine();
fCategoryCode=Integer.parseInt(randomAccessFile.readLine());
fPrice=Integer.parseInt(randomAccessFile.readLine());
fNumberOfUnits=Integer.parseInt(randomAccessFile.readLine());
if(fProductId==productId)
{
fNumberOfUnits++;
}
tmpRandomAccessFile.writeBytes(String.valueOf(fProductId)+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fCategoryCode)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fPrice)+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fNumberOfUnits)+"\n");
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


}