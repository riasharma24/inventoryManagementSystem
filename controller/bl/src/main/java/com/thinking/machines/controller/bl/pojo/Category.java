package com.thinking.machines.controller.bl.pojo;
import com.thinking.machines.controller.bl.interfaces.pojo.*;

public class Category implements CategoryInterface
{
private int code;
private String title;

public void setCode(int code)
{
this.code=code;
}

public int getCode()
{
return this.code;
}

public void setTitle(String title)
{
this.title=title;
}

public String getTitle()
{
return this.title;
}

public boolean equals(Object other)
{
if(!(other instanceof CategoryInterface))return false;
CategoryInterface category=(CategoryInterface)other;
return this.code==category.getCode();
}

public int hashCode()
{
return this.code;
}

public int compareTo(CategoryInterface category)
{
return this.code-category.getCode();
}

}