import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class ProductManagerUpdateTestCase
{
public static void main(String gg[])
{
try{
ProductManagerInterface productManager;
productManager=ProductManager.getProductManager();
ProductInterface product=new Product();
product.setProductId(Integer.parseInt(gg[0]));
product.setName(gg[1]);
product.setCategoryCode(Integer.parseInt(gg[2]));
product.setPrice(Integer.parseInt(gg[3]));
product.setNumberOfUnits(Integer.parseInt(gg[4]));
productManager.updateProduct(product);
System.out.println("Product updated with product id : "+product.getProductId());
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

