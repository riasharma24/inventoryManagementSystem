package com.thinking.machines.controller.dl.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class OrderDAO implements OrderDAOInterface
{
private static String FILE_NAME="orders.dat";
public void add(OrderDTOInterface orderDTO) throws DAOException
{
if(orderDTO==null)throw new DAOException("Order cannot be null");
int productId=orderDTO.getProductId();
if(productId<=0)throw new DAOException("Invalid product id");
if(!(new ProductDAO().productIdExists(productId)))throw new DAOException("Product does not exist");
int orderId;
boolean deliveryStatus=false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int recordCount=0;
String recordCountString="";
int lastGeneratedCode=0;
String lastGeneratedCodeString="";
if(randomAccessFile.length()==0)
{
recordCountString=String.valueOf(recordCount);
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.writeBytes(lastGeneratedCodeString+"\n");
}
else
{
recordCountString=randomAccessFile.readLine().trim();
lastGeneratedCodeString=randomAccessFile.readLine().trim();
recordCount=Integer.parseInt(recordCountString);
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString);
}

while(randomAccessFile.getFilePointer()<randomAccessFile.length())randomAccessFile.readLine();
orderId=lastGeneratedCode+1;

randomAccessFile.writeBytes(String.valueOf(orderId));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(productId));
randomAccessFile.writeBytes("\n");
if(deliveryStatus)randomAccessFile.writeBytes("True");
else randomAccessFile.writeBytes("False");
randomAccessFile.writeBytes("\n");

randomAccessFile.seek(0);
lastGeneratedCode++;
recordCount++;
recordCountString=String.valueOf(recordCount);
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
orderDTO.setDeliveryStatus(false);
orderDTO.setOrderId(orderId);
ProductDAOInterface productDAO=new ProductDAO();
productDAO.decreaseNumberOfUnits(productId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(OrderDTOInterface orderDTO) throws DAOException
{
if(orderDTO==null)throw new DAOException("Order cannot be null");
int orderId=orderDTO.getOrderId();
if(orderId<=0)throw new DAOException("Invalid order id");

int productId=orderDTO.getProductId();
if(productId<=0)throw new DAOException("Invalid product id");
if(!(new ProductDAO().productIdExists(productId)))throw new DAOException("Invalid product id");

boolean deliveryStatus=orderDTO.getDeliveryStatus();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
boolean orderIdFound=false;
int fOrderId;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
randomAccessFile.readLine();
randomAccessFile.readLine();
if(fOrderId==orderId)
{
orderIdFound=true;
break;
}
}
if(orderIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Order id does not exist.");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
int fProductId;
int prevProductId=0;
boolean fDeliveryStatus;
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
if(fOrderId==orderId)
{
prevProductId=fProductId;
tmpRandomAccessFile.writeBytes(String.valueOf(orderId));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(productId));
tmpRandomAccessFile.writeBytes("\n");
if(deliveryStatus)tmpRandomAccessFile.writeBytes("True");
else tmpRandomAccessFile.writeBytes("False");
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(fOrderId));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fProductId));
tmpRandomAccessFile.writeBytes("\n");
if(fDeliveryStatus)tmpRandomAccessFile.writeBytes("True");
else tmpRandomAccessFile.writeBytes("False");
tmpRandomAccessFile.writeBytes("\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
ProductDAOInterface productDAO=new ProductDAO();
productDAO.decreaseNumberOfUnits(productId);
productDAO.increaseNumberOfUnits(prevProductId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(int orderId) throws DAOException
{
if(orderId<=0)throw new DAOException("Invalid order id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
boolean orderIdFound=false;
int fOrderId;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
randomAccessFile.readLine();
randomAccessFile.readLine();
if(fOrderId==orderId)
{
orderIdFound=true;
break;
}
}
if(orderIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Order id does not exist.");
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
int fProductId;
boolean fDeliveryStatus;
int recordCount;
String recordCountString=randomAccessFile.readLine();
recordCount=Integer.parseInt(recordCountString.trim());
tmpRandomAccessFile.writeBytes(recordCountString);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
if(fOrderId==orderId)
{
continue;
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(fOrderId));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(fProductId));
tmpRandomAccessFile.writeBytes("\n");
if(fDeliveryStatus)tmpRandomAccessFile.writeBytes("True");
else tmpRandomAccessFile.writeBytes("False");
tmpRandomAccessFile.writeBytes("\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.seek(0);
recordCount--;
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=' ';
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<OrderDTOInterface> getAllUndelivered() throws DAOException
{
Set<OrderDTOInterface> undeliveredOrders=new HashSet<>();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
OrderDTOInterface orderDTO;
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fOrderId;
int fProductId;
boolean fDeliveryStatus;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
if(fDeliveryStatus==false)
{
orderDTO=new OrderDTO();
orderDTO.setOrderId(fOrderId);
orderDTO.setProductId(fProductId);
orderDTO.setDeliveryStatus(fDeliveryStatus);
undeliveredOrders.add(orderDTO);
}
}
randomAccessFile.close();
return undeliveredOrders;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<OrderDTOInterface> getAll() throws DAOException
{
Set<OrderDTOInterface> orders=new HashSet<>();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
OrderDTOInterface orderDTO;
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fOrderId;
int fProductId;
boolean fDeliveryStatus;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
orderDTO=new OrderDTO();
orderDTO.setOrderId(fOrderId);
orderDTO.setProductId(fProductId);
orderDTO.setDeliveryStatus(fDeliveryStatus);
orders.add(orderDTO);
}
randomAccessFile.close();
return orders;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public Set<OrderDTOInterface> getAllDelivered() throws DAOException
{
Set<OrderDTOInterface> deliveredOrders=new HashSet<>();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
OrderDTOInterface orderDTO;
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fOrderId;
int fProductId;
boolean fDeliveryStatus;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
if(fDeliveryStatus==true)
{
orderDTO=new OrderDTO();
orderDTO.setOrderId(fOrderId);
orderDTO.setProductId(fProductId);
orderDTO.setDeliveryStatus(fDeliveryStatus);
deliveredOrders.add(orderDTO);
}
}
randomAccessFile.close();
return deliveredOrders;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public OrderDTOInterface getByOrderId(int orderId) throws DAOException
{
if(orderId<=0)throw new DAOException("Invalid order id");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.close();
throw new DAOException("Invalid order id");
}
int fOrderId;
int fProductId;
boolean fDeliveryStatus;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
fProductId=Integer.parseInt(randomAccessFile.readLine());
fDeliveryStatus=Boolean.parseBoolean(randomAccessFile.readLine());
if(fOrderId==orderId)
{
randomAccessFile.close();
OrderDTOInterface orderDTO;
orderDTO=new OrderDTO();
orderDTO.setOrderId(fOrderId);
orderDTO.setProductId(fProductId);
orderDTO.setDeliveryStatus(fDeliveryStatus);
return orderDTO;
}
}
randomAccessFile.close();
System.out.println("Here");
throw new DAOException("Invalid order id");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean orderIdExists(int orderId)
{
if(orderId<=0)return false;
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
int fOrderId;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fOrderId=Integer.parseInt(randomAccessFile.readLine());
if(fOrderId==orderId)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.close();
}catch(IOException ioException)
{
}
return false;
}

}