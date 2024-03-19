import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CustomerManagerCustomerContactNumberExistsTestCase
{
public static void main(String gg[])
{
try{
CustomerManagerInterface customerManager=CustomerManager.getCustomerManager();
if(customerManager.customerContactNumberExists(gg[0]))System.out.println("Contact number : "+gg[0]+" exists.");
else System.out.println("Contact number : "+gg[0]+" does not exist.");
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