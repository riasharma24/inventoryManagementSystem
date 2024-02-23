import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAOGetByTitleTestCase
{
public static void main(String gg[])
{
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
CategoryDTOInterface categoryDTO;
try{
categoryDTO=categoryDAO.getByTitle(gg[0]);
System.out.println("Code : "+categoryDTO.getCode());
System.out.println("Title : "+categoryDTO.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}