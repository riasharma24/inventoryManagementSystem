import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;
import java.util.*;

public class OrderDAOGetAllUndeliveredTestCase
{
public static void main(String gg[])
{
Set<OrderDTOInterface> orders;
OrderDAOInterface orderDAO=new OrderDAO();
try{
orders=orderDAO.getAllUndelivered();
for(OrderDTOInterface orderDTO : orders)
{
System.out.println("Order id : "+orderDTO.getOrderId());
System.out.println("Product id : "+orderDTO.getProductId());
System.out.println("Delivery status : "+orderDTO.getDeliveryStatus());
System.out.println("**************************************************");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}