import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CategoryManagerUpdateTestCase
{
public static void main(String gg[])
{
CategoryInterface category=new Category();
category.setCode(Integer.parseInt(gg[0]));
category.setTitle(gg[1]);
try{
CategoryManagerInterface categoryManager;
categoryManager=CategoryManager.getCategoryManager();
categoryManager.updateCategory(category);
System.out.println("Category updated with code : "+gg[0]+" and title : "+gg[1]);
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
else
{
List<String> properties;
properties=blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getException(property));
}
}
}
}
}