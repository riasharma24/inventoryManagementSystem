import com.thinking.machines.controller.bl.interfaces.pojo.*;
import com.thinking.machines.controller.bl.interfaces.managers.*;
import com.thinking.machines.controller.bl.pojo.*;
import com.thinking.machines.controller.bl.managers.*;
import com.thinking.machines.controller.bl.exceptions.*;
import java.util.*;
import javax.swing.table.*;

public class CategoryModel extends AbstractTableModel
{
private List<CategoryInterface> categories;
private CategoryManagerInterface categoryManager;
private String[] columnTitle;

public CategoryModel()
{
this.populateDatastructures();
}

private void populateDatastructures()
{
this.columnTitle=new String[2];
this.columnTitle[0]=new String("SNo.");
this.columnTitle[1]=new String("Category");
try{
categoryManager=CategoryManager.getCategoryManager();
}catch(BLException blException)
{
//????
}

Set<CategoryInterface> blCategories=categoryManager.getCategories();
this.categories=new LinkedList<>();
for(CategoryInterface category : blCategories)
{
this.categories.add(category);
}

Collections.sort(this.categories,new Comparator<CategoryInterface>(){
public int compare(CategoryInterface left,CategoryInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});

}

public int getRowCount()
{
return categories.size();
}

public int getColumnCount()
{
return this.columnTitle.length;
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)return rowIndex+1;
else return this.categories.get(rowIndex).getTitle();
}

public String getColumnName(int columnIndex)
{
return columnTitle[columnIndex];
}

public Class getColumnClass(int columnIndex)
{
if(columnIndex==0)return Integer.class;
return String.class;
}

public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}

//Application specific methods

public void add(CategoryInterface category) throws BLException
{
categoryManager.addCategory(category);
this.categories.add(category);
Collections.sort(this.categories,new Comparator<CategoryInterface>{
public int compare(CategoryInterface left,CategoryInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});

fireTableDataChanged();
}




}