import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class OrderDAOGetByOrderIdTestCase
{
public static void main(String gg[])
{
OrderDTOInterface orderDTO;
try{
OrderDAOInterface orderDAO;
orderDAO=new OrderDAO();
orderDTO=orderDAO.getByOrderId(Integer.parseInt(gg[0].trim()));
System.out.println("Order id : "+orderDTO.getOrderId());
System.out.println("Product id : "+orderDTO.getProductId());
System.out.println("Delivery status : "+orderDTO.getDeliveryStatus());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}