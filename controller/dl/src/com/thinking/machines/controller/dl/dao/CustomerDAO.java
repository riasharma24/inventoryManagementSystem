package com.thinking.machines.controller.dl.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class CustomerDAO implements CustomerDAOInterface
{
private static String FILE_NAME="customers.dat";

public void add(CustomerDTOInterface customerDTO) throws DAOException
{
if(customerDTO==null)throw new DAOException("Customer cannot be null");
String name=customerDTO.getName();
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Name cannot be of zero length");
String customerId;
String contactNumber=customerDTO.getContactNumber();
if(contactNumber==null)throw new DAOException("Contact number cannot be null");
contactNumber=contactNumber.trim();
if(contactNumber.length()!=10)throw new DAOException("Invalid contact number");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int lastGeneratedCode=1000000;
int recordCount=0;
String lastGeneratedCodeString="";
String recordCountString="";
if(randomAccessFile.length()==0)
{
recordCountString=String.format("%-10d",recordCount);
lastGeneratedCodeString=String.format("%-10d",lastGeneratedCode);
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
}
else
{
recordCountString=randomAccessFile.readLine();
lastGeneratedCodeString=randomAccessFile.readLine();
recordCount=Integer.parseInt(recordCountString.trim());
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString.trim());
}
String fContactNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
if(fContactNumber.equalsIgnoreCase(contactNumber))
{
randomAccessFile.close();
throw new DAOException("Contact number already exists.");
}
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
}
}
customerId=String.valueOf(lastGeneratedCode+1);
customerId="A"+String.format("%-10s",customerId);
randomAccessFile.writeBytes(customerId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(contactNumber+"\n");
randomAccessFile.writeBytes("$#$");
randomAccessFile.writeBytes("\n");
randomAccessFile.seek(0);
lastGeneratedCode++;
recordCount++;
lastGeneratedCodeString=String.format("%-10d",lastGeneratedCode);
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.writeBytes(lastGeneratedCodeString+"\n");
randomAccessFile.close();
customerDTO.setCustomerId(customerId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(CustomerDTOInterface customerDTO) throws DAOException
{
String name=customerDTO.getName();
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Name cannot be of zero length");
String customerId=customerDTO.getCustomerId();
if(customerId==null)throw new DAOException("Customer id cannot be null");
customerId=customerId.trim();
if(customerId.length()==0)throw new DAOException("Customer id cannot be of zero length");
String contactNumber=customerDTO.getContactNumber();
if(contactNumber==null)throw new DAOException("Contact number cannot be null");
contactNumber=contactNumber.trim();
if(contactNumber.length()!=10)throw new DAOException("Invalid contact number");
List<Integer> orders=customerDTO.getOrders();
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
String fName;
String fContactNumber;
String fCustomerId;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
if(fCustomerId.equalsIgnoreCase(customerId))
{
found=true;
break;
}
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Details not found");
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
if(!fCustomerId.equalsIgnoreCase(customerId) && fContactNumber.equalsIgnoreCase(contactNumber))
{
randomAccessFile.close();
throw new DAOException("Contact number already exists against customer id : "+fCustomerId);
}
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
}
}
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
List<Integer> fOrders;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
fOrders=new ArrayList<>();
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
fOrders.add(Integer.parseInt(line));
}
if(fCustomerId.equalsIgnoreCase(customerId))
{
tmpRandomAccessFile.writeBytes(customerId+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(contactNumber+"\n");
for(int orderId : orders)
{
tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
}
tmpRandomAccessFile.writeBytes("$#$"+"\n");
}else
{
tmpRandomAccessFile.writeBytes(fCustomerId+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fContactNumber+"\n");
for(int orderId : fOrders)tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
tmpRandomAccessFile.writeBytes("$#$"+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(String customerId) throws DAOException
{
if(customerId==null)throw new DAOException("Customer id cannot be null");
customerId=customerId.trim();
if(customerId.length()==0)throw new DAOException("Customer id cannot be of zero length");
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
String fName;
String fContactNumber;
String fCustomerId;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
if(fCustomerId.equalsIgnoreCase(customerId))
{
found=true;
break;
}
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Details not found");
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
List<Integer> fOrders;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
fOrders=new ArrayList<>();
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
fOrders.add(Integer.parseInt(line));
}
if(fCustomerId.equalsIgnoreCase(customerId))
{
continue;
}else
{
tmpRandomAccessFile.writeBytes(fCustomerId+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fContactNumber+"\n");
for(int orderId : fOrders)tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
tmpRandomAccessFile.writeBytes("$#$"+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.seek(0);
String recordCountString=randomAccessFile.readLine().trim();
int recordCount=Integer.parseInt(recordCountString);
recordCount--;
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.seek(0);
randomAccessFile.writeBytes(recordCountString+"\n");
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public CustomerDTOInterface getByName(String name) throws DAOException
{
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Length of name cannot be 0");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid name");
}
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fName.equalsIgnoreCase(name))
{
randomAccessFile.close();
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setCustomerId(fCustomerId);
customerDTO.setName(fName);
customerDTO.setContactNumber(fContactNumber);
for(int order : orders)customerDTO.addOrder(order);
return customerDTO;
}
}
randomAccessFile.close();
throw new DAOException("Customer not found");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public CustomerDTOInterface getByCustomerId(String customerId) throws DAOException
{
if(customerId==null)throw new DAOException("Customer id cannot be null");
customerId=customerId.trim();
if(customerId.length()==0)throw new DAOException("Length of customer id cannot be 0");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid customer id");
}
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fCustomerId.equalsIgnoreCase(customerId))
{
randomAccessFile.close();
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setCustomerId(fCustomerId);
customerDTO.setName(fName);
customerDTO.setContactNumber(fContactNumber);
for(int order : orders)customerDTO.addOrder(order);
return customerDTO;
}
}
randomAccessFile.close();
throw new DAOException("Customer not found");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public CustomerDTOInterface getByContactNumber(String contactNumber) throws DAOException
{
if(contactNumber==null)throw new DAOException("Contact number cannot be null");
contactNumber=contactNumber.trim();
if(contactNumber.length()==0)throw new DAOException("Length of contact number cannot be 0");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid contact number");
}
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fContactNumber.equalsIgnoreCase(contactNumber))
{
randomAccessFile.close();
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setCustomerId(fCustomerId);
customerDTO.setName(fName);
customerDTO.setContactNumber(fContactNumber);
for(int order : orders)customerDTO.addOrder(order);
return customerDTO;
}
}
randomAccessFile.close();
throw new DAOException("Customer not found");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void placeOrder(CustomerDTOInterface customerDTO) throws DAOException
{
String name=customerDTO.getName();
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Name cannot be of zero length");
String customerId=customerDTO.getCustomerId();
if(customerId==null)throw new DAOException("Customer id cannot be null");
customerId=customerId.trim();
if(customerId.length()==0)throw new DAOException("Customer id cannot be of zero length");
String contactNumber=customerDTO.getContactNumber();
if(contactNumber==null)throw new DAOException("Contact number cannot be null");
contactNumber=contactNumber.trim();
if(contactNumber.length()!=10)throw new DAOException("Invalid contact number");
List<Integer> orders=customerDTO.getOrders();
if(orders==null)throw new DAOException("No orders to place");
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
String fName;
String fContactNumber;
String fCustomerId;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
if(fCustomerId.equalsIgnoreCase(customerId) && fName.equalsIgnoreCase(name) && fContactNumber.equalsIgnoreCase(contactNumber))
{
found=true;
break;
}
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Details not found");
}
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
List<Integer> fOrders;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
fOrders=new ArrayList<>();
String line;
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
fOrders.add(Integer.parseInt(line));
}
if(fCustomerId.equalsIgnoreCase(customerId))
{
tmpRandomAccessFile.writeBytes(customerId+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(contactNumber+"\n");
/* new */
for(int orderId : fOrders)tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
for(int orderId : orders)
{
tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
}
tmpRandomAccessFile.writeBytes("$#$"+"\n");
}else
{
tmpRandomAccessFile.writeBytes(fCustomerId+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fContactNumber+"\n");
for(int orderId : fOrders)tmpRandomAccessFile.writeBytes(String.valueOf(orderId)+"\n");
tmpRandomAccessFile.writeBytes("$#$"+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public Set<CustomerDTOInterface> getAll() throws DAOException
{
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("No entries yet");
}
Set<CustomerDTOInterface> customers=new HashSet<>();
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setCustomerId(fCustomerId);
customerDTO.setName(fName);
customerDTO.setContactNumber(fContactNumber);
for(int order : orders)customerDTO.addOrder(order);
customers.add(customerDTO);
}
randomAccessFile.close();
return customers;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean nameExists(String name) throws DAOException
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
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fName.equalsIgnoreCase(name))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean contactNumberExists(String contactNumber) throws DAOException
{
if(contactNumber==null)return false;
contactNumber=contactNumber.trim();
if(contactNumber.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fContactNumber.equalsIgnoreCase(contactNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean customerIdExists(String customerId) throws DAOException
{
if(customerId==null)return false;
customerId=customerId.trim();
if(customerId.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
String fName;
String fCustomerId;
String fContactNumber;
List<Integer> orders;
String line;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCustomerId=randomAccessFile.readLine().trim();
fName=randomAccessFile.readLine();
fContactNumber=randomAccessFile.readLine();
orders=new ArrayList<>();
while(true)
{
line=randomAccessFile.readLine();
if(line.equalsIgnoreCase("$#$"))break;
orders.add(Integer.parseInt(line));
}
if(fCustomerId.equalsIgnoreCase(customerId))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public int getCount() throws DAOException
{
int count=0;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return count;
}
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

}