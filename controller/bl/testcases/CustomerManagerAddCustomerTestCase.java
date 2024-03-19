import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CustomerManagerAddCustomerTestCase
{
public static void main(String gg[])
{
try{
CustomerManagerInterface customerManager=CustomerManager.getCustomerManager();
CustomerInterface customer=new Customer();
customer.setName(gg[0]);
customer.setContactNumber(gg[1]);
customerManager.addCustomer(customer);
System.out.println("Customer added with id : "+customer.getCustomerId());
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