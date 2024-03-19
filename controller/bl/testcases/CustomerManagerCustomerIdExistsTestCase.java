import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CustomerManagerCustomerIdExistsTestCase
{
public static void main(String gg[])
{
try{
CustomerManagerInterface customerManager;
customerManager=CustomerManager.getCustomerManager();
if(customerManager.customerIdExists(gg[0]))System.out.println("Customer id : "+gg[0]+" exists.");
else System.out.println("Customer id : "+gg[0]+" does not exist.");

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