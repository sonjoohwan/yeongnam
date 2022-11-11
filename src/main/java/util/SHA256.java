package util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;

public class SHA256 {
	
	private static String salt ="맥카페";//임시 소금(멤버변수로 선언)	
	
	private static String prePassword;	
	
	public static String getPrePassword() {
		return prePassword;
	}
	
	
	public static String encodeSHA256(String password){//password:암호화되기 전 비번
	    prePassword = password;/*--[암호화 전 비밀번호 찾기]를 위해 미리 멤버변수에 저장해둠---------*/
		
		String result = "";
		
		//원본
		/* 
		byte[] passwordByte = password.getBytes("UTF-8");// 소스를 바이트로 변경 
		byte[] salt = Salt.getBytes();//소금도 바이트로 변경
		//소금값을 암호 앞, 뒤 어드쪽에 붙여도 상관 없다. 비밀번호 앞에 사용하는게 좀 더 일반적이긴 하다.
		byte[] bytes = new byte[passwordByte.length+salt.length];
		*/
		
		try {
			byte[] saltByte = salt.getBytes(); //salt도 바이트로 변경
			byte[] passwordByte = password.getBytes("utf-8");//암호화되기 전 비번(password)를 바이트로 변경			
			
			/*
			 * 기본값으로 채워진 byte객체에 바이트로 변경된 소금값과 비번으로 채움
			 */
			byte[] saltPassword = new byte[saltByte.length + passwordByte.length]; 
			
			//소금값을 암호 앞, 뒤 어느쪽에 붙여도 상관 없다. 비밀번호 앞에 사용하는게 좀 더 일반적이긴 하다.
			//void arraycopy(원본, 원본의 시작 index, 도착지, 도착지의 시작index, 복사할 개수)
			System.arraycopy(saltByte, 0, saltPassword, 0, saltByte.length);//saltPassword [앞] : 바이트로 변경된 "salt값"			
			System.arraycopy(passwordByte, 0, saltPassword, saltByte.length, passwordByte.length);//saltPassword [뒤] : 바이트로 변경된 암호화되기 전 "비번"
			//즉, saltPassword [앞] : 바이트로 변경된 "salt값" + [뒤] : 바이트로 변경된 암호화되기 전 "비번"
			
			/* 'SHA-256알고리즘'을 사용하기 위해 먼저,import java.security.MessageDigest;
			 * new 생성자를 따로 쓸 필요 없이 아래와 같이 하면 자동으로 객체 생성됨
			 */
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//md.update()가 바로 입력한 문자열을 해싱하는 함수다. 이 때 문자열은 바이트배열이어야 한다.
			md.update(saltPassword);
			
			byte[] saltPasswordDigest =  md.digest();//md객체의 다이제스트를 얻어 password를 갱신한다.
		
			/* String과 'StingBuffer/StringBuilder' 클래스의 가장 큰 차이점은
			 * String은 불변의 속성을 갖는다. 
			 * 그래서 (예)"안녕"+"하세요"=>새로운문자열객체 "안녕하세요" heap메모리에 생성하므로
			 * 문자열을 수정, 추가, 삭제 등의 연산이 빈번히 발생하는 알고리즘에서는 heap메모리에 가비지(=쓰레기)객체가
			 * 계속 누적되어 힙메모리 부족으로 애플리케이션 성능에 치명적인 영향을 미친다.
			 * 
			 * 이를 해결하기 위해서 가변성을 가진 'StingBuffer/StringBuilder'클래스를 도입했다.
			 * 따라서 문자열을 수정, 추가, 삭제 등의 연산이 빈번하게 발생하는 경우라면 String가 아니라
			 * 'StingBuffer/StringBuilder' 클래스를 사용해야 한다.
			 * 
			 * 
			 * ['StingBuffer/StringBuilder' 클래스의 차이점]
			 * 1.문자열 연산이 많고 '멀티쓰레드' 환경일 경우 : StringBuffer
			 * 2.문자열 연산이 많고 '싱글쓰레드' 환경일 경우 : StringBuilder
			 * 
			 * 3.문자열 연산이 적고 '멀티쓰레드' 환경일 경우 : String
			 */
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<saltPasswordDigest.length ;i++) {
				/* 1.saltPasswordDigest의 하나하나의 byte를 16진수로 변경 ->2."16진수" -> 3.StringBuffer에 추가
				 * byte를 16진수로 변경하는 이유?
				 * 기본적으로 데이터는 binary data로 전달된다.
				 * 16진수 배열로 만들 경우 데이터의 손실없이 유지할 수 있는 장점이 있다.
				 * 꼭 16진수로 변환하지 않고 Base64 같은 64진수로도 표현되기도 한다.
				 */
				//1.saltPasswordDigest의 하나하나의 byte를 16진수로 변경 ->2."16진수" -> 3.StringBuffer에 추가
				sb.append(Integer.toString((saltPasswordDigest[i]&0xFF)+256, 16).substring(1));
			}
			result = sb.toString();
		
		} catch (Exception e) {			
			//e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("encodeSHA256 : "+result);
		return result;
	}
	
	
	/* [수정-1]
	 * https://moonsiri.tistory.com/39
	 * 임시 비밀번호 생성
	 * getRamdomPassword(8)를 호출하면 8 글자의 임시비밀번호가 생성됩니다.
	 * (1.'영어대소문자+숫자+특수문자'가 결합된 8 글자의 임시비밀번호 생성됨)
	 * (2.'영어대소문자+숫자'가 결합된 8 글자의 임시비밀번호 생성됨)
	 */
	public static String getRamdomPassword(int size) { //size가 8이면  8 글자의 임시비밀번호가 생성
		//1.'영어대소문자+숫자+특수문자'가 결합된 8 글자의 임시비밀번호
		/*
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
				'!', '@', '#', '$', '%', '^', '&' }; 
		*/
		
		//2.'영어대소문자+숫자'가 결합된 8 글자의 임시비밀번호
		char[] charSet = new char[] { 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
		         };//특수문자 제외
		
		StringBuffer sb = new StringBuffer(); 
		SecureRandom sr = new SecureRandom(); 
		sr.setSeed(new Date().getTime()); 
		
		int idx = 0; 
		int len = charSet.length; 
		for (int i=0; i<size; i++) { 
			// idx = (int) (len * Math.random()); 
			idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
			sb.append(charSet[idx]); 
			} 
		return sb.toString(); 
	}

	/*== 포트폴리오에 필요한 메서드(끝) ==========================================================*/
}
