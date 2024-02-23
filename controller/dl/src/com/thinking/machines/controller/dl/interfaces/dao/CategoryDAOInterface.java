package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;


public interface CategoryDAOInterface
{
public void add(CategoryDTOInterface categoryDTO) throws DAOException;
public void update(CategoryDTOInterface categoryDTO) throws DAOException;
public void delete(CategoryDTOInterface categoryDTO) throws DAOException;
public CategoryDTOInterface getByCode(int code) throws DAOException;
public CategoryDTOInterface getByTitle(String title) throws DAOException;
public int getCount();
public boolean codeExists(int code);
public boolean titleExists(String title);
public Set<CategoryDTOInterface> getAll() throws DAOException;
}