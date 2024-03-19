import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class OrderManagerGetAllUndeliveredOrdersTestCase
{
public static void main(String gg[])
{
try{
OrderManagerInterface orderManager=OrderManager.getOrderManager();
Set<OrderInterface> orders=orderManager.getAllUndeliveredOrders();
for(OrderInterface order : orders)
{
System.out.println("Order id : "+order.getOrderId());
System.out.println("Product id : "+order.getProductId());
System.out.println("Delivery status : "+order.getDeliveryStatus());
System.out.println("******************************************");
}
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
else
{
List<String> properties=blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getException(property));
}
}
}
}
}