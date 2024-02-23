import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAODeleteTestCase
{
public static void main(String gg[])
{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
try{
productDAO.delete(Integer.parseInt(gg[0]));
System.out.println("Product with id : "+gg[0]+" deleted.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}