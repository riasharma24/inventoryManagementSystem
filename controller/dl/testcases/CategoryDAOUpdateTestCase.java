import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CategoryDAOUpdateTestCase
{
public static void main(String gg[])
{
CategoryDTOInterface categoryDTO;
categoryDTO=new CategoryDTO();
categoryDTO.setCode(Integer.parseInt(gg[0]));
categoryDTO.setTitle(gg[1]);
try{
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
categoryDAO.update(categoryDTO);
System.out.println("Record updated.");
System.out.println("Code : "+categoryDTO.getCode());
System.out.println("Title : "+categoryDTO.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}