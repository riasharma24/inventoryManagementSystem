package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public interface ProductDAOInterface
{
public void add(ProductDTOInterface productDTO) throws DAOException;
public void update(ProductDTOInterface productDTO) throws DAOException;
public void delete(int productId) throws DAOException;
public ProductDTOInterface getByProductId(int productId) throws DAOException;
public ProductDTOInterface getByName(String name) throws DAOException;
public int getCount();
public int getCountByCategory(int categoryCode) throws DAOException;
public Set<ProductDTOInterface> getAll() throws DAOException;
public Set<ProductDTOInterface> getByCategory(int categoryCode) throws DAOException;
public boolean productIdExists(int productId);
public boolean nameExists(String name);
public void decreaseNumberOfUnits(int productId) throws DAOException;
public void increaseNumberOfUnits(int productId) throws DAOException;
}