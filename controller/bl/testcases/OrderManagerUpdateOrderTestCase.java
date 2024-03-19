import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class OrderManagerUpdateOrderTestCase
{
public static void main(String gg[])
{
try{
OrderInterface order=new Order();
order.setOrderId(Integer.parseInt(gg[0]));
order.setProductId(Integer.parseInt(gg[1]));
order.setDeliveryStatus(Boolean.parseBoolean(gg[2]));
OrderManagerInterface orderManager=OrderManager.getOrderManager();
orderManager.updateOrder(order);
System.out.println("Order with id : "+gg[0]+" updated successfully.");
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}else
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
