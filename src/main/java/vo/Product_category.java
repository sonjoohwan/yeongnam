package vo;

public class Product_category {
	private String category_code;
	private String category_name;
	
	public Product_category() {
		super();
	}
	
	public Product_category(String category_code, String category_name) {
		super();
		this.category_code = category_code;
		this.category_name = category_name;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	
}
