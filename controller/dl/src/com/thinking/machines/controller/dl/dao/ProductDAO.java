package com.thinking.machines.controller.dl.dao;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class ProductDAO implements ProductDAOInterface
{
private static String FILE_NAME="products.dat";

public void add(ProductDTOInterface productDTO) throws DAOException
{
throw new DAOException("Not yet implemented");
}

public void update(ProductDTOInterface productDTO) throws DAOException
{
throw new DAOException("Not yet implemented");
}

public void delete(int productId) throws DAOException
{
throw new DAOException("Not yet implemented");
}

public ProductDAOInterface getByProductId(int productId) throws DAOException
{
throw new DAOException("Not yet implemented");
}

public ProductDAOInterface getByName(String name) throws DAOException
{
throw new DAOException("Not yet implemented");
}

public Set<ProductDAOInterface> getAll() throws DAOException
{
throw new DAOException("Not yet implemented");
}

public boolean productIdExists(int productId)
{
return false;
}

public boolean nameExists(String name)
{
return false;
}


}