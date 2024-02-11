package com.thinking.machines.controller.dl.interfaces.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public interface ProductDAOInterface
{
public void add(ProductDTOInterface productDTO) throws DAOException;
public void update(ProductDTOInterface productDTO) throws DAOException;
public void delete(int productId) throws DAOException;
public ProductDAOInterface getByProductId(int productId) throws DAOException;
public ProductDAOInterface getByName(String name) throws DAOException;
public Set<ProductDAOInterface> getAll() throws DAOException;
public boolean productIdExists(int productId);
public boolean nameExists(String name);
}