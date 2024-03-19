import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class OrderManagerAddOrderTestCase
{
public static void main(String gg[])
{
try{
OrderManagerInterface orderManager=OrderManager.getOrderManager();
OrderInterface order=new Order();
order.setProductId(Integer.parseInt(gg[0]));
orderManager.addOrder(order);
System.out.println("Order added with id : "+order.getOrderId());
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