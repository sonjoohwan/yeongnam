package svc.manager;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.OrderDAO;

public class CancelOrderStatusServiece {

	public boolean cancelOrderStatus(int order_id) {
		boolean cancelSuccess = false;
		
		Connection con = getConnection();
		
		OrderDAO oDAO = OrderDAO.getInstance();
		
		oDAO.setConnection(con);
		
		boolean cancelStatus = oDAO.cancelOrderStatus(order_id);
		
		if (!cancelStatus) {
			rollback(con);
		}else {
			cancelSuccess = true;
			commit(con);
		}

		
		return cancelSuccess;
	}

}
