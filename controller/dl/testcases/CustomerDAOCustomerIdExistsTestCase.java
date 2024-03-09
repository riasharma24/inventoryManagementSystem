import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class CustomerDAOCustomerIdExistsTestCase
{
public static void main(String gg[])
{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
try{
if(customerDAO.customerIdExists(gg[0]))System.out.println("Customer id : "+gg[0]+" exists.");
else System.out.println("Customer id : "+gg[0]+" does not exist.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}