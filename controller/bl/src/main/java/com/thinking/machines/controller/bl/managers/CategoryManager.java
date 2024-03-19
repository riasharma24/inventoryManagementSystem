package com.thinking.machines.controller.bl.managers;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CategoryManager implements CategoryManagerInterface
{
private Map<Integer,CategoryInterface> codeWiseCategoriesMap;
private Map<String,CategoryInterface> titleWiseCategoriesMap;
private Set<CategoryInterface> categoriesSet;
private static CategoryManagerInterface categoryManager;

private CategoryManager() throws BLException
{
populateDatastructures();
}

private void populateDatastructures() throws BLException
{
this.codeWiseCategoriesMap=new HashMap<>();
this.titleWiseCategoriesMap=new HashMap<>();
this.categoriesSet=new TreeSet<>();
try{
Set<CategoryDTOInterface> dlCategories=new CategoryDAO().getAll();
CategoryInterface category;
for(CategoryDTOInterface categoryDTO : dlCategories)
{
category=new Category();
category.setCode(categoryDTO.getCode());
category.setTitle(categoryDTO.getTitle());
codeWiseCategoriesMap.put(categoryDTO.getCode(),category);
titleWiseCategoriesMap.put(categoryDTO.getTitle().toUpperCase(),category);
categoriesSet.add(category);
}
}catch(DAOException daoException)
{
BLException blException;
blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public static CategoryManagerInterface getCategoryManager() throws BLException
{
if(categoryManager==null)categoryManager=new CategoryManager();
return categoryManager;
}


public void addCategory(CategoryInterface category) throws BLException
{
BLException blException;
blException=new BLException();
if(category==null)
{
blException.setGenericException("Category cannot be null");
throw blException;
}
String title=category.getTitle();
if(title==null)
{
blException.addException("title","Category cannot be null");
}
else
{
title=title.trim();
if(title.length()==0)blException.addException("title","Category length cannot be zero");
}
int code=category.getCode();
if(code!=0)
{
blException.addException("code","Category code cannot be set");
}
if(this.titleWiseCategoriesMap.containsKey(title.toUpperCase()))
{
blException.addException("title","Category : "+title+" already exists.");
}
if(blException.hasExceptions())
{
throw blException;
}
try{
CategoryDTOInterface categoryDTO=new CategoryDTO();
categoryDTO.setTitle(title);
CategoryDAOInterface categoryDAO=new CategoryDAO();
categoryDAO.add(categoryDTO);
code=categoryDTO.getCode();
category.setCode(code);
CategoryInterface dsCategory;
dsCategory=new Category();
dsCategory.setCode(code);
dsCategory.setTitle(title);
this.codeWiseCategoriesMap.put(code,dsCategory);
this.titleWiseCategoriesMap.put(title,dsCategory);
this.categoriesSet.add(dsCategory);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void updateCategory(CategoryInterface category) throws BLException
{
BLException blException;
blException=new BLException();
if(category==null)
{
blException.setGenericException("Category cannot be null");
throw blException;
}
String title=category.getTitle();
int code=category.getCode();
if(title==null)
{
blException.addException("title","Category cannot be null");
throw blException;
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Category cannot be null");
throw blException;
}
}
if(!this.codeWiseCategoriesMap.containsKey(code))
{
blException.addException("code","Code does not exist");
}
if(this.titleWiseCategoriesMap.containsKey(title))
{
blException.addException("title","Category : "+title+" already exists.");
}
if(blException.hasExceptions())
{
throw blException;
}
try{
CategoryDTOInterface categoryDTO=new CategoryDTO();
categoryDTO.setCode(code);
categoryDTO.setTitle(title);
CategoryDAOInterface categoryDAO=new CategoryDAO();
categoryDAO.update(categoryDTO);
CategoryInterface dsCategory=new Category();
dsCategory.setCode(code);
dsCategory.setTitle(title);
CategoryInterface prevCategory;
prevCategory=this.codeWiseCategoriesMap.get(code);
this.titleWiseCategoriesMap.remove(prevCategory.getTitle().toUpperCase());
this.codeWiseCategoriesMap.remove(code);
this.categoriesSet.remove(prevCategory);
this.titleWiseCategoriesMap.put(title.toUpperCase(),dsCategory);
this.codeWiseCategoriesMap.put(code,dsCategory);
this.categoriesSet.add(dsCategory);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void deleteCategory(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(!this.codeWiseCategoriesMap.containsKey(code))
{
blException.addException("code","Code does not exist");
throw blException;
}
try{
CategoryInterface category=this.codeWiseCategoriesMap.get(code);
CategoryDAOInterface categoryDAO=new CategoryDAO();
categoryDAO.delete(code);
this.codeWiseCategoriesMap.remove(code);
this.titleWiseCategoriesMap.remove(category.getTitle().toUpperCase());
this.categoriesSet.remove(category);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public CategoryInterface getCategoryByCode(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0)
{
blException.setGenericException("Invalid code");
throw blException;
}
if(!codeWiseCategoriesMap.containsKey(code))
{
blException.setGenericException("Invalid code");
throw blException;
}
CategoryInterface dsCategory=this.codeWiseCategoriesMap.get(code);
CategoryInterface category=new Category();
category.setCode(code);
category.setTitle(dsCategory.getTitle());
return category;
}

public CategoryInterface getCategoryByTitle(String title) throws BLException
{
BLException blException;
blException=new BLException();
if(title==null)
{
blException.setGenericException("Invalid title");
throw blException;
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.setGenericException("Invalid title");
throw blException;
}
}
if(!titleWiseCategoriesMap.containsKey(title.toUpperCase()))
{
blException.setGenericException("Invalid title");
throw blException;
}
CategoryInterface dsCategory=titleWiseCategoriesMap.get(title.toUpperCase());
CategoryInterface category=new Category();
category.setCode(dsCategory.getCode());
category.setTitle(dsCategory.getTitle());
return category;
}

public Set<CategoryInterface> getCategories()
{
Set<CategoryInterface> categories=new HashSet<>();
CategoryInterface category;
for(CategoryInterface dsCategory : this.categoriesSet)
{
category=new Category();
category.setCode(dsCategory.getCode());
category.setTitle(dsCategory.getTitle());
categories.add(category);
}
return categories;
}

public boolean categoryTitleExists(String title)
{
if(title==null)return false;
title=title.trim();
if(title.length()==0)return false;
return titleWiseCategoriesMap.containsKey(title.toUpperCase());
}

public boolean categoryCodeExists(int code)
{
if(code<=0)return false;
return codeWiseCategoriesMap.containsKey(code);
}

public int getCategoriesCount()
{
return this.categoriesSet.size();
}

}