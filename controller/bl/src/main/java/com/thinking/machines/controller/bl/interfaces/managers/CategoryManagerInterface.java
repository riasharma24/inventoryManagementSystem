package com.thinking.machines.controller.bl.interfaces.managers;

import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public interface CategoryManagerInterface
{
public void addCategory(CategoryInterface category) throws BLException;
public void updateCategory(CategoryInterface category) throws BLException;
public void deleteCategory(int code) throws BLException;
public CategoryInterface getCategoryByCode(int code) throws BLException;
public CategoryInterface getCategoryByTitle(String title) throws BLException;
public Set<CategoryInterface> getCategories();
public boolean categoryTitleExists(String title);
public boolean categoryCodeExists(int code);
public int getCategoriesCount();
}