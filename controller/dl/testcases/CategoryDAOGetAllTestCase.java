import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CategoryDAOGetAllTestCase
{
public static void main(String gg[])
{
Set<CategoryDTOInterface> categories;
CategoryDAOInterface categoryDAO;
categoryDAO=new CategoryDAO();
try{
categories=categoryDAO.getAll();
for(CategoryDTOInterface categoryDTO : categories)
{
System.out.println("Code : "+categoryDTO.getCode());
System.out.println("Title : "+categoryDTO.getTitle());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}