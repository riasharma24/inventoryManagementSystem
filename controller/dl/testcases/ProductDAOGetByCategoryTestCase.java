import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class ProductDAOGetByCategoryTestCase
{
public static void main(String gg[])
{
int category=Integer.parseInt(gg[0]);
Set<ProductDTOInterface> products;
ProductDAOInterface productDAO=new ProductDAO();
try{
products=productDAO.getByCategory(category);
for(ProductDTOInterface productDTO : products)
{
System.out.println("Product Id : "+productDTO.getProductId());
System.out.println("Name : "+productDTO.getName());
System.out.println("Category : "+productDTO.getCategoryCode());
System.out.println("Price : "+productDTO.getPrice());
System.out.println("Number of units : "+productDTO.getNumberOfUnits());
System.out.println("************************************************");
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}