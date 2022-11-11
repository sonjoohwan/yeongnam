package vo;

public class ManagerTBL {
	private String manager_id;
	private String manager_pwd;
	private String manager_email;
	public ManagerTBL() {
		super();
	}
	public ManagerTBL(String manager_id, String manager_pwd, String manager_email) {
		super();
		this.manager_id = manager_id;
		this.manager_pwd = manager_pwd;
		this.manager_email = manager_email;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_pwd() {
		return manager_pwd;
	}
	public void setManager_pwd(String manager_pwd) {
		this.manager_pwd = manager_pwd;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}
	
	
}
