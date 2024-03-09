import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class CustomerDAOUpdateTestCase
{
public static void main(String gg[])
{
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setCustomerId(gg[0]);
customerDTO.setName(gg[1]);
customerDTO.setContactNumber(gg[2]);
try{
//creating order
OrderDTOInterface order1=new OrderDTO();
order1.setProductId(Integer.parseInt(gg[3]));
OrderDTOInterface order2=new OrderDTO();
order2.setProductId(Integer.parseInt(gg[4]));
OrderDTOInterface order3=new OrderDTO();
order3.setProductId(Integer.parseInt(gg[5]));
OrderDAOInterface orderDAO;
orderDAO=new OrderDAO();
orderDAO.add(order1);
orderDAO.add(order2);
orderDAO.add(order3);
System.out.println("Orders created");
//adding orders to DS
customerDTO.addOrder(order1.getOrderId());
customerDTO.addOrder(order2.getOrderId());
customerDTO.addOrder(order3.getOrderId());
List<Integer> orders=customerDTO.getOrders();
for(int order : orders)System.out.println("Order id : "+order);
//placing order

CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
customerDAO.update(customerDTO);
System.out.println("Customer with id : "+customerDTO.getCustomerId()+" updated successfully.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}