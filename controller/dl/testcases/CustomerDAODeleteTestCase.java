import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CustomerDAODeleteTestCase
{
public static void main(String gg[])
{
try{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
customerDAO.delete(gg[0]);
System.out.println("Customer with id : "+gg[0]+" deleted successfully.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}