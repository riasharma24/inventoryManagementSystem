import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;

public class CategoryManagerGetCategoriesTestCase
{
public static void main(String gg[])
{
try{
CategoryManagerInterface categoryManager=CategoryManager.getCategoryManager();
Set<CategoryInterface> categories=categoryManager.getCategories();
System.out.println(categories.size());
for(CategoryInterface category : categories)
{
System.out.println("Code : "+category.getCode());
System.out.println("Title : "+category.getTitle());
System.out.println("*****************************************");
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
System.out.println(blException.getException(property));
}
}
}
}
}