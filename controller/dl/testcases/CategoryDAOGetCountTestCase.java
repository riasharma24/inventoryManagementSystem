import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAOGetCountTestCase
{
public static void main(String gg[])
{
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
System.out.println("Number of categories : "+categoryDAO.getCount());
}
}