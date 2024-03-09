import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CustomerDAONameExistsTestCase
{
public static void main(String gg[])
{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
try{
if(customerDAO.nameExists(gg[0]))System.out.println("Name : "+gg[0]+" exists.");
else System.out.println("Name : "+gg[0]+" does not exist.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}