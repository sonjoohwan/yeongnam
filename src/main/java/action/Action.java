package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public interface Action {
	//추상메서드는 반드시 이 인터페이스를 구현한 클래스에서 재정의해야 함 
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
