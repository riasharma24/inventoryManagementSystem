import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class ProductManagerProductNameExistsTestCase
{
public static void main(String gg[])
{
try{
ProductManagerInterface productManager=ProductManager.getProductManager();
if(productManager.productNameExists(gg[0]))System.out.println("Product : "+gg[0]+" exists.");
else System.out.println("Product : "+gg[0]+" does not exist.");
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