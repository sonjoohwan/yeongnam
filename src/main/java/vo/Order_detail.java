package vo;

public class Order_detail {
	private int order_detail_id;
	private int order_id;
	private int product_no;
	private int order_amount;
	private int order_price;
	
	//주문 상세보기에 사용할 생성자용 멤버변수
	private String member_id;
	private String address;
	private String member_phone;
	private String member_email;
	
	
	public Order_detail() {
		super();
	}

	public Order_detail(int order_detail_id, int order_id, int product_no, int order_amount, int order_price) {
		super();
		this.order_detail_id = order_detail_id;
		this.order_id = order_id;
		this.product_no = product_no;
		this.order_amount = order_amount;
		this.order_price = order_price;
	}
	
	//주문 상세보기에 사용할 생성자
	public Order_detail(
			int order_id, 
			String member_id,
			String address, 
			String member_phone, 
			String member_email,
			int product_no, 
			int order_amount, 
			int order_price
			) {
		super();
		this.order_id = order_id;
		this.member_id = member_id;
		this.address = address;
		this.member_phone = member_phone;
		this.member_email = member_email;
		this.product_no = product_no;
		this.order_amount = order_amount;
		this.order_price = order_price;
	}

	public int getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	
	
	
}
