package packGlobalFunctions;
//import java.text.NumberFormat;
//import java.text.ParseException;
//import java.math.BigInteger;
//import java.security.SecureRandom;
import java.util.Random;
//import java.util.UUID;

public class GenericFunctions {
	 //private SecureRandom random = new SecureRandom();
	 
	public static String randomString( int len) throws NumberFormatException{
		//String a[]; 		  
		 final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		 Random rnd = new Random();

		//String randomString( int len ) 
		//{
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		//}
		    //System.out.println(new BigInteger(130, random).toString(32));
		    //return new BigInteger(130, random).toString(32);
		/*String uuid = UUID.randomUUID().toString();
		System.out.println("uuid = " + uuid);
		a = uuid.split("-");
		try{
		 Integer.parseInt(a[0]);
		}catch(NumberFormatException e){System.out.println("Number format exception");}
		//if (a[0] == String.valueOf(a[0]))
		return a[0].substring(0, 5);*/
	}
	
	
	/*public static void main(String[] args) throws ParseException{
		String x;
			
	
		GenericFunctions a = new GenericFunctions();
		x = a.randomString(4);
		System.out.println(x);
		
		String someNumber = "1,23,456";
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.parse(someNumber).toString();
		System.out.println(nf.parse(someNumber).toString());
	//	System.out.println(nf.format(someNumber));
	}*/

}
