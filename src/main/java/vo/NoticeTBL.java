package vo;


public class NoticeTBL {
	private int notice_no;
	private String notice_subject;
	private String notice_text;
	private String notice_date;
	
	public NoticeTBL() {
		super();
	}

	public NoticeTBL(int notice_no, String notice_subject, String notice_text, String notice_date) {
		super();
		this.notice_no = notice_no;
		this.notice_subject = notice_subject;
		this.notice_text = notice_text;
		this.notice_date = notice_date;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_subject() {
		return notice_subject;
	}

	public void setNotice_subject(String notice_subject) {
		this.notice_subject = notice_subject;
	}

	public String getNotice_text() {
		return notice_text;
	}

	public void setNotice_text(String notice_text) {
		this.notice_text = notice_text;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	
	
	
	
	
}
