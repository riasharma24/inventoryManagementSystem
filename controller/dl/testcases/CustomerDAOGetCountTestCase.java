import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CustomerDAOGetCountTestCase
{
public static void main(String gg[])
{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
try{
System.out.println("Number of customers : "+customerDAO.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}