import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CustomerDAOGetByCustomerIdTestCase
{
public static void main(String gg[])
{
CustomerDTOInterface customerDTO;
try{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
customerDTO=customerDAO.getByCustomerId(gg[0]);
System.out.println("Name : "+customerDTO.getName());
System.out.println("Customer id : "+customerDTO.getCustomerId());
System.out.println("Contact number : "+customerDTO.getContactNumber());
List<Integer> orders=customerDTO.getOrders();
for(int order : orders)System.out.println("Order id : "+order);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}