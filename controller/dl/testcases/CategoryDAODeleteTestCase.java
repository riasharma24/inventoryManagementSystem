import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAODeleteTestCase
{
public static void main(String gg[])
{
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
try{
categoryDAO.delete(Integer.parseInt(gg[0]));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}