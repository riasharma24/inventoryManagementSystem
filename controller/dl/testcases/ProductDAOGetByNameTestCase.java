import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAOGetByNameTestCase
{
public static void main(String gg[])
{
ProductDTOInterface productDTO;
String name=gg[0];
try{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
productDTO=productDAO.getByName(name);
System.out.println("Product id : "+productDTO.getProductId());
System.out.println("Name : "+productDTO.getName());
System.out.println("Category : "+productDTO.getCategoryCode());
System.out.println("Price : "+productDTO.getPrice());
System.out.println("Number of units : "+productDTO.getNumberOfUnits());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
