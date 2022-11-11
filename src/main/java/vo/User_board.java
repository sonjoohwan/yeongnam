package vo;


public class User_board {
	private int post_no;
	private int member_code;
	private String post_date;
	private String post_pwd;
	private String post_subject;
	private String post_text;
	private String post_file;
	
	//게시글 보기에서 member_code로 memberTBL과 join하여 member_id 불러올때 사용
	private String member_id;
	
	public User_board() {
		super();
	}
	
	
	//게시판 목록 보기용 생성자
	public User_board(int post_no, String post_subject, String member_id, String post_date) {
		super();
		this.post_no = post_no;
		this.post_subject = post_subject;
		this.member_id = member_id;
		this.post_date = post_date;
	}
	
	//게시글 보기 용 생성자
	public User_board(int post_no, String post_subject, String member_id, String post_date, String post_text) {
		super();
		this.post_no = post_no;
		this.post_subject = post_subject;
		this.member_id = member_id;
		this.post_date = post_date;
		this.post_text = post_text;
	}

	//글쓰기 저장용 생성자
	public User_board(int post_no, int member_code, String post_date, String post_pwd, String post_subject,
			String post_text, String post_file) {
		super();
		this.post_no = post_no;
		this.member_code = member_code;
		this.post_date = post_date;
		this.post_pwd = post_pwd;
		this.post_subject = post_subject;
		this.post_text = post_text;
		this.post_file = post_file;
	}




	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getMember_code() {
		return member_code;
	}

	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getPost_pwd() {
		return post_pwd;
	}

	public void setPost_pwd(String post_pwd) {
		this.post_pwd = post_pwd;
	}

	public String getPost_subject() {
		return post_subject;
	}

	public void setPost_subject(String post_subject) {
		this.post_subject = post_subject;
	}

	public String getPost_text() {
		return post_text;
	}

	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}

	public String getPost_file() {
		return post_file;
	}

	public void setPost_file(String post_file) {
		this.post_file = post_file;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	
}
