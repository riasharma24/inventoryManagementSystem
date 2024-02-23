import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAOTitleExistsTestCase
{
public static void main(String gg[])
{
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
if(categoryDAO.titleExists(gg[0]))System.out.println("Title : "+gg[0]+" exists.");
else System.out.println("Title : "+gg[0]+" does not exist.");
}
}
