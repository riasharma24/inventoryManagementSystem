import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAOGetCountByCategoryTestCase
{
public static void main(String gg[])
{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
try{
System.out.println("Product count : "+productDAO.getCountByCategory(Integer.parseInt(gg[0])));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}