import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class OrderDAOUpdateTestCase
{
public static void main(String gg[])
{
OrderDTOInterface orderDTO=new OrderDTO();
orderDTO.setOrderId(Integer.parseInt(gg[0]));
orderDTO.setProductId(Integer.parseInt(gg[1]));
orderDTO.setDeliveryStatus(Boolean.parseBoolean(gg[2]));
OrderDAO orderDAO;
orderDAO=new OrderDAO();
try{
orderDAO.update(orderDTO);
System.out.println("Order updated successfully.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}