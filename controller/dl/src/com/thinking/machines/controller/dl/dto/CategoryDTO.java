package com.thinking.machines.controller.dl.dto;
import com.thinking.machines.controller.dl.interfaces.dto.*;

public class CategoryDTO implements CategoryDTOInterface
{
private int code;
private String title;

public void setTitle(String title)
{
this.title=title;
}

public String getTitle()
{
return this.title;
}

public void setCode(int code)
{
this.code=code;
}

public int getCode()
{
return this.code;
}

public int hashCode()
{
return this.code;
}

public int compareTo(CategoryDTOInterface other)
{
return this.code-other.getCode();
}

public boolean equals(Object other)
{
if(!(other instanceof CategoryDTOInterface))return false;
CategoryDTOInterface categoryDTO=(CategoryDTO)other;
return this.code==categoryDTO.getCode();
}

}