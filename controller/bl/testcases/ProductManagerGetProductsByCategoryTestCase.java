import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class ProductManagerGetProductsByCategoryTestCase
{
public static void main(String gg[])
{
try{
ProductManagerInterface productManager=ProductManager.getProductManager();
Set<ProductInterface> products=productManager.getProductsByCategory(Integer.parseInt(gg[0]));
for(ProductInterface product : products)
{
System.out.println("Product id : "+product.getProductId());
System.out.println("Name : "+product.getName());
System.out.println("Category code : "+product.getCategoryCode());
System.out.println("Price : "+product.getPrice());
System.out.println("Number of units : "+product.getNumberOfUnits());
System.out.println("****************************************");
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
System.out.println(blException.hasException(property));
}
}
}
}
}