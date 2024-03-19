import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CustomerManagerGetCustomerByCustomerIdTestCase
{
public static void main(String gg[])
{
try{
CustomerManagerInterface customerManager=CustomerManager.getCustomerManager();
CustomerInterface customer=customerManager.getCustomerByCustomerId(gg[0]);
System.out.println("Name : "+customer.getName());
System.out.println("Customer id : "+customer.getCustomerId());
System.out.println("Contact number : "+customer.getContactNumber());
List<Integer> orders=customer.getOrders();
for(int order : orders)
{
System.out.println("Order id : "+order);
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