package vo;

public class Deliver_address {
	private int member_code;
	private String address1;
	private String address2;
	private String address3;
	
	
	public Deliver_address() {
		super();
	}
	
	
	public Deliver_address(int member_code, String address1, String address2, String address3) {
		super();
		this.member_code = member_code;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
	}


	public int getMember_code() {
		return member_code;
	}
	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}




}
