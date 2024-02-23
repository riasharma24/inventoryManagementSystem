import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAOProductIdExistsTestCase
{
public static void main(String gg[])
{
ProductDAO productDAO;
productDAO=new ProductDAO();
if(productDAO.productIdExists(Integer.parseInt(gg[0])))System.out.println("Product id "+gg[0]+" exists.");
else System.out.println("Product id : "+gg[0]+" does not exist.");
}
}
