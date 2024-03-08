import com.thinking.machines.controller.dl.interfaces.dto.*;
import com.thinking.machines.controller.dl.interfaces.dao.*;
import com.thinking.machines.controller.dl.dto.*;
import com.thinking.machines.controller.dl.dao.*;
import com.thinking.machines.controller.dl.exceptions.*;

public class OrderDAODeleteTestCase
{
public static void main(String gg[])
{
OrderDAOInterface orderDAO;
orderDAO=new OrderDAO();
try{
orderDAO.delete(Integer.parseInt(gg[0]));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}