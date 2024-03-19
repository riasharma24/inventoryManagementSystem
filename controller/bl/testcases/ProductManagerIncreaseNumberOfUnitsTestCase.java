import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class ProductManagerIncreaseNumberOfUnitsTestCase
{
public static void main(String gg[])
{
try{
ProductManagerInterface productManager;
productManager=ProductManager.getProductManager();
productManager.increaseNumberOfUnits(Integer.parseInt(gg[0]));
System.out.println("Number of units for product with id : "+gg[0]+" increased.");
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