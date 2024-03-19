import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class ProductManagerAddTestCase
{
public static void main(String gg[])
{
try{
ProductManagerInterface productManager;
productManager=ProductManager.getProductManager();
ProductInterface product=new Product();
product.setName(gg[0]);
product.setCategoryCode(Integer.parseInt(gg[1]));
product.setPrice(Integer.parseInt(gg[2]));
product.setNumberOfUnits(Integer.parseInt(gg[3]));
productManager.addProduct(product);
System.out.println("Product added with product id : "+product.getProductId());
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

