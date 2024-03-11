import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAODecreaseNumberOfUnitsTestCase
{
public static void main(String gg[])
{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
try{
productDAO.decreaseNumberOfUnits(Integer.parseInt(gg[0]));
System.out.println("Number of units decreased of product with id : "+gg[0]);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}