import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAOAddTestCase
{
public static void main(String gg[])
{
CategoryDTOInterface categoryDTO;
categoryDTO=new CategoryDTO();
categoryDTO.setTitle(gg[0]);
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
try{
categoryDAO.add(categoryDTO);
System.out.println("Title : "+categoryDTO.getTitle());
System.out.println("Code : "+categoryDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}