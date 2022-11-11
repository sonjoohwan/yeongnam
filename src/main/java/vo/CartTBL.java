package vo;

public class CartTBL {
	private int cart_id;
	private int member_code;
	private int product_no;
	private int product_amount;
	
	//주환이형이 추가한거
	private String category_code;
	private int product_price;
	private String member_id;
	private String product_name;
	private String product_image;
	
	public CartTBL() {
		super();
	}
	
	public CartTBL(int cart_id, int member_code, int product_no, int product_amount) {
		super();
		this.cart_id = cart_id;
		this.member_code = member_code;
		this.product_no = product_no;
		this.product_amount = product_amount;
	}
	
	
	//22-11-04 추가된 생성자
	public CartTBL(int cart_id, int member_code, int product_no, int product_amount, String category_code,
			int product_price, String member_id, String product_name, String product_image) {
		super();
		this.cart_id = cart_id;
		this.member_code = member_code;
		this.product_no = product_no;
		this.product_amount = product_amount;
		this.category_code = category_code;
		this.product_price = product_price;
		this.member_id = member_id;
		this.product_name = product_name;
		this.product_image = product_image;
	}

	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getMember_code() {
		return member_code;
	}
	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}
	public int getProduct_no() {
		return product_no;
	}
	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}
	public int getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
	
	
	
}
