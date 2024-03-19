import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CustomerManagerUpdateCustomerTestCase
{
public static void main(String gg[])
{
try{
CustomerManagerInterface customerManager=CustomerManager.getCustomerManager();
CustomerInterface customer=new Customer();
customer.setName(gg[0]);
customer.setCustomerId(gg[1]);
customer.setContactNumber(gg[2]);
customer.addOrder(Integer.parseInt(gg[3]));
customer.addOrder(Integer.parseInt(gg[4]));
customerManager.updateCustomer(customer);
System.out.println("Customer with customer id : "+gg[1]+" updated successfully.");
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