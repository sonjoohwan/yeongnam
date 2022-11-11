package svc.manager;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;

public class UpdateOrderStatusService {

	public boolean updateOrderStatus(int order_id) {
		boolean updateSuccess = false;
		
		Connection con = getConnection();
		
		OrderDAO oDAO = OrderDAO.getInstance();
		
		oDAO.setConnection(con);
		
		boolean updateStatus = oDAO.updateOrderStatus(order_id);
		
		if (!updateStatus) {
			rollback(con);
		}else {
			updateSuccess = true;
			commit(con);
		}

		
		return updateSuccess;
	}

}
