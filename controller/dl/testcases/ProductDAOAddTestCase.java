import com.thinking.machines.controller.dl.exceptions.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;

public class ProductDAOAddTestCase
{
public static void main(String gg[])
{
ProductDTOInterface productDTO;
productDTO=new ProductDTO();
productDTO.setName(gg[0]);
productDTO.setPrice(Integer.parseInt(gg[1]));
productDTO.setCategoryCode(Integer.parseInt(gg[2]));
productDTO.setNumberOfUnits(Integer.parseInt(gg[3]));

ProductDAOInterface productDAO=new ProductDAO();
productDAO=new ProductDAO();
try{
productDAO.add(productDTO);
System.out.println("Product id : "+productDTO.getProductId());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}