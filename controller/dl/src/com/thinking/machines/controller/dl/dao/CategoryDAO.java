package com.thinking.machines.controller.dl.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class CategoryDAO implements CategoryDAOInterface
{
private String FILE_NAME="category.dat";

public void add(CategoryDTOInterface categoryDTO) throws DAOException
{
if(categoryDTO==null)throw new DAOException("Category cannot be null");
String title=categoryDTO.getTitle();
if(title==null)throw new DAOException("Category cannot be null");
title=title.trim();
if(title.length()==0)throw new DAOException("Category cannot be null");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int recordCount=0;
int lastGeneratedCode=0;
String recordCountString="";
String lastGeneratedCodeString="";

if(randomAccessFile.length()==0)
{
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
recordCountString=String.valueOf(recordCount);
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
while(recordCountString.length()<10)recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
}
else
{
recordCountString=randomAccessFile.readLine();
lastGeneratedCodeString=randomAccessFile.readLine();
}
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Category already exists");
}
}
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString.trim());
recordCount=Integer.parseInt(recordCountString.trim());
int code=lastGeneratedCode+1;
randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(title);
randomAccessFile.writeBytes("\n");

randomAccessFile.seek(0);
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString.trim());
recordCount=Integer.parseInt(recordCountString.trim());
recordCount++;
lastGeneratedCode++;
recordCountString=String.valueOf(recordCount);
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
while(recordCountString.length()<10)recordCountString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
categoryDTO.setCode(code);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(CategoryDTOInterface categoryDTO) throws DAOException
{
if(categoryDTO==null)throw new DAOException("Category cannot be null");
String title=categoryDTO.getTitle();
if(title==null)throw new DAOException("Category cannot be null");
title=title.trim();
if(title.length()==0)throw new DAOException("Category cannot be null");
int code=categoryDTO.getCode();
if(code<=0)throw new DAOException("Code cannot be null");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fTitle;
int fCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code && fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Title already exists against code : "+fCode);
}
}
File tmpFile;
tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(code));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(title);
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
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
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(CategoryDTOInterface categoryDTO) throws DAOException
{
if(categoryDTO==null)throw new DAOException("Category cannot be null");
String title=categoryDTO.getTitle();
if(title==null)throw new DAOException("Title cannot be null");
title=title.trim();
if(title.length()==0)throw new DAOException("Title cannot be null");
int code=categoryDTO.getCode();
if(code<=0)throw new DAOException("Invalid code");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid details");
}
String recordCountString=randomAccessFile.readLine();
int recordCount=Integer.parseInt(recordCountString.trim());
randomAccessFile.readLine();
int fCode;
String fTitle;
boolean codeFound=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
codeFound=true;
break;
}
}
if(codeFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid code");
}
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
continue;
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
}

randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
recordCount--;
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";
randomAccessFile.seek(0);
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public CategoryDTOInterface getByCode(int code) throws DAOException
{
if(code<=0)throw new DAOException("Invalid code");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid title");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fTitle;
int fCode;
CategoryDTOInterface categoryDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
categoryDTO=new CategoryDTO();
categoryDTO.setCode(fCode);
categoryDTO.setTitle(fTitle);
randomAccessFile.close();
return categoryDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid category");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public CategoryDTOInterface getByTitle(String title) throws DAOException
{
if(title==null)throw new DAOException("Title cannot be null");
title=title.trim();
if(title.length()==0)throw new DAOException("Title cannot be null");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid title");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fTitle;
int fCode;
CategoryDTOInterface categoryDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
categoryDTO=new CategoryDTO();
categoryDTO.setCode(fCode);
categoryDTO.setTitle(fTitle);
randomAccessFile.close();
return categoryDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid category");
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
return count;
}
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
}catch(IOException ioException)
{
}
return count;
}

public boolean codeExists(int code)
{
if(code<=0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
int fCode;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(fCode==code)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
}catch(IOException ioException)
{
}
return false;
}

public boolean titleExists(String title)
{
if(title==null)return false;
title=title.trim();
if(title.length()==0)return false;
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
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
}catch(IOException ioException)
{
}
return false;
}

public Set<CategoryDTOInterface> getAll() throws DAOException
{
Set<CategoryDTOInterface> categories=new HashSet<>();
CategoryDTOInterface categoryDTO;
int fCode;
String fTitle;
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
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
categoryDTO=new CategoryDTO();
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
categoryDTO.setCode(fCode);
categoryDTO.setTitle(fTitle);
categories.add(categoryDTO);
}
randomAccessFile.close();
return categories;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

}