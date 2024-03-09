import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CustomerDAOGetAllTestCase
{
public static void main(String gg[])
{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
try{
Set<CustomerDTOInterface> customers=customerDAO.getAll();
List<Integer> orders;
for(CustomerDTOInterface customerDTO : customers)
{
System.out.println("Name : "+customerDTO.getName());
System.out.println("Customer id : "+customerDTO.getCustomerId());
System.out.println("Contact number : "+customerDTO.getContactNumber());
orders=customerDTO.getOrders();
for(int order : orders)System.out.println("Order id : "+order);
System.out.println("###################################################");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}