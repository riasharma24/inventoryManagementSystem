import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class OrderDAOAddTestCase
{
public static void main(String gg[])
{
OrderDTOInterface orderDTO=new OrderDTO();
orderDTO.setProductId(Integer.parseInt(gg[0]));
OrderDAO orderDAO;
orderDAO=new OrderDAO();
try{
orderDAO.add(orderDTO);
System.out.println("Order added with order id : "+orderDTO.getOrderId());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}