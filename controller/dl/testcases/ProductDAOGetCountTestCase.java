import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAOGetCountTestCase
{
public static void main(String gg[])
{
ProductDAOInterface productDAO;
productDAO=new ProductDAO();
System.out.println("Count : "+productDAO.getCount());
}
}
