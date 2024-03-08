import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.exceptions.*;

class CustomerDAOAddTestCase
{
public static void main(String gg[])
{
CustomerDTOInterface customerDTO;
customerDTO=new CustomerDTO();
customerDTO.setName(gg[0]);
customerDTO.setContactNumber(gg[1]);
try{
CustomerDAOInterface customerDAO;
customerDAO=new CustomerDAO();
customerDAO.add(customerDTO);
System.out.println("Customer added with Id : "+customerDTO.getCustomerId());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}