package utils;

import java.util.Random;

public class JavaUtils {

	//Generating random number
	public Integer randonNumber() {
		Random random = new Random(); 
		int randomString = random.nextInt(2000000);
		
		return randomString;
	}
	

}
