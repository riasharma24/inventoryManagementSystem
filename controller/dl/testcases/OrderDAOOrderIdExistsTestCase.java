import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class OrderDAOOrderIdExistsTestCase
{
public static void main(String gg[])
{
int orderId=Integer.parseInt(gg[0]);
OrderDAOInterface orderDAO=new OrderDAO();
if(orderDAO.orderIdExists(orderId))System.out.println("Order exists.");
else System.out.println("Order does not exists.");
}
}