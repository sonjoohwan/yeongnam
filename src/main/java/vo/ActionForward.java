package vo;

public class ActionForward {
	private String path = null;
	private boolean isRedirect = false;//false:디스패치(=기존요청), true:리다이렉트(=새요청)
	
	public ActionForward() {} //반드시 수동으로 삽입해줘야 함
	
	public ActionForward(String path, boolean isRedirect) {
		super();
		this.path = path;
		this.isRedirect = isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}
