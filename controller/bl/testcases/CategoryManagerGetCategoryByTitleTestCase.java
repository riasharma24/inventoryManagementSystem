import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CategoryManagerGetCategoryByTitleTestCase
{
public static void main(String gg[])
{
try{
CategoryInterface category;
CategoryManagerInterface categoryManager;
categoryManager=CategoryManager.getCategoryManager();
category=categoryManager.getCategoryByTitle(gg[0]);
System.out.println("Code : "+category.getCode());
System.out.println("Title : "+category.getTitle());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}else
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