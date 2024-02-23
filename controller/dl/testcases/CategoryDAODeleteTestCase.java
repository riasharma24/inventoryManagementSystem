import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAODeleteTestCase
{
public static void main(String gg[])
{
CategoryDTOInterface categoryDTO;
categoryDTO=new CategoryDTO();
categoryDTO.setCode(Integer.parseInt(gg[0]));
categoryDTO.setTitle(gg[1]);
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
try{
categoryDAO.delete(categoryDTO);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}