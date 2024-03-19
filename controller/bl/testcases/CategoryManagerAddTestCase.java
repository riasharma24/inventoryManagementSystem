import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CategoryManagerAddTestCase
{
public static void main(String gg[])
{
try{
CategoryInterface category=new Category();
category.setTitle(gg[0]);
CategoryManagerInterface categoryManager;
categoryManager=CategoryManager.getCategoryManager();
categoryManager.addCategory(category);
System.out.println("Category added with code : "+category.getCode());
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
System.out.println(property+" : "+blException.getException(property));
}
}
}
}
}