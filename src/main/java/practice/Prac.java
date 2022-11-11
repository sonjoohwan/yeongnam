package practice;

public class Prac {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filePath = "C:\\fakepath\\KakaoTalk_20221030_191240594.png";
		
		String fakePath = "C:\\fakepath\\";
		
		String result = filePath.substring(fakePath.length());
		
		System.out.println(result);

	}

}
