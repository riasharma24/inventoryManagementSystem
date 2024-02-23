import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class ProductDAOUpdateTestCase
{
public static void main(String gg[])
{
ProductDTOInterface productDTO;
productDTO=new ProductDTO();
productDTO.setProductId(Integer.parseInt(gg[0]));
productDTO.setName(gg[1]);
productDTO.setCategoryCode(Integer.parseInt(gg[2]));
productDTO.setPrice(Integer.parseInt(gg[3]));
productDTO.setNumberOfUnits(Integer.parseInt(gg[4]));

ProductDAOInterface productDAO;
productDAO=new ProductDAO();
try{
productDAO.update(productDTO);
System.out.println("Product updated.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
