package vo;

public class OrderTBL {
	private int order_id;
	private int member_code;
	private int totalmoney;
	private String order_date;
	private String order_status;
	
	//매출관리에서 order_detail 테이블과 조인하여 정보 불러올때 사용할 변수
	
	private int product_no;
	private int order_amount;
	private int order_price;
	private int order_detail_id;
	
	
	
	public OrderTBL() {
		super();
	}

	public OrderTBL(int order_id, int member_code, String order_date, String order_status) {
		super();
		this.order_id = order_id;
		this.member_code = member_code;
		this.order_date = order_date;
		this.order_status = order_status;
	}

	public OrderTBL(int order_id, int order_detail_id, int product_no, int order_amount, int order_price, String order_date) {
		this.order_id = order_id;
		this.order_detail_id = order_detail_id;
		this.product_no = product_no;
		this.order_amount = order_amount;
		this.order_price = order_price;
		this.order_date = order_date;
	}

	public OrderTBL(int order_id, int member_code, int totalmoney,  String order_date, String order_status) {
		super();
		this.order_id = order_id;
		this.member_code = member_code;
		this.totalmoney = totalmoney;
		this.order_date = order_date;
		this.order_status = order_status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getMember_code() {
		return member_code;
	}

	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
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

	public int getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}

	public int getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	
	
	
	
	
}
