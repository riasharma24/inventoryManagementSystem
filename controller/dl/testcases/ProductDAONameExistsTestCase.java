import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAONameExistsTestCase
{
public static void main(String gg[])
{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
if(productDAO.nameExists(gg[0]))System.out.println("Name : "+gg[0]+" exists.");
else System.out.println("Name : "+gg[0]+" does not exist.");
}
}