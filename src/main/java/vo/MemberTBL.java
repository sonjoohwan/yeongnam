package vo;

import java.sql.Date;

public class MemberTBL {
	//2. 멤버변수 접근제한자 : private
		private int member_code;
		private String member_id;
		private String member_pwd;
		private String member_name;
		private String member_birth;
		private String member_phone;
		private String member_email;
		private String member_gender;
		
		//회원 관리 페이지에서 회원 정보를 한번에 불러올수 있게 추가함
		//주소 등록 등의 기능에서는 기존의 주소관리용 테이블인 (Deliver_address.java) 사용
		private String address1;
		private String address2;
		private String address3;
		

		//3. 매개변수가 없는 생성자 : 기본생성자
		public MemberTBL() {
			super();
		}



		public MemberTBL(
				int member_code, String member_id, String member_pwd, 
				String member_name, String member_birth,
				String member_phone, String member_email, String member_gender
				) {
			super();
			this.member_code = member_code;
			this.member_id = member_id;
			this.member_pwd = member_pwd;
			this.member_name = member_name;
			this.member_birth = member_birth;
			this.member_phone = member_phone;
			this.member_email = member_email;
			this.member_gender = member_gender;
		}

		
		/*
		 * 회원 관리 페이지용 생성자 ( member_code로 join한 테이블 정보를 한번에 가져올때 사용 
		 * ※기존의 유저 마이페이지에도 사용 가능하나 이미 완성된 페이지기 때문에 수정하지 않음 추후에 필요시 수정 가능)
		 */
		public MemberTBL(int member_code, String member_id, String member_pwd, String member_name, String member_birth,
				String member_phone, String member_email, String member_gender, String address1, String address2,
				String address3) {
			super();
			this.member_code = member_code;
			this.member_id = member_id;
			this.member_pwd = member_pwd;
			this.member_name = member_name;
			this.member_birth = member_birth;
			this.member_phone = member_phone;
			this.member_email = member_email;
			this.member_gender = member_gender;
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



		public String getMember_id() {
			return member_id;
		}



		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}



		public String getMember_pwd() {
			return member_pwd;
		}



		public void setMember_pwd(String member_pwd) {
			this.member_pwd = member_pwd;
		}



		public String getMember_name() {
			return member_name;
		}



		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}



		public String getMember_birth() {
			return member_birth;
		}



		public void setMember_birth(String member_birth) {
			this.member_birth = member_birth;
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



		public String getMember_gender() {
			return member_gender;
		}



		public void setMember_gender(String member_gender) {
			this.member_gender = member_gender;
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



		
		
} // class
