import java.lang.reflect.*;
import java.util.regex.*;

public class Show{
	private static String usa="abc";
	private static Pattern p=Pattern.compile("\\w+\\.");
	public static void main(String [] args){
		if(args.length<1){
			System.out.println(usa);
			System.exit(0);
		}
		
		int lines=0;
		try{
			Class<?> c=Class.forName(args[0]);
			Method[] methods=c.getMethods();
			Constructor[] cons=c.getConstructors();
			if(args.length==1){
				for(Method m:methods){
					System.out.println(m.toString());
				}
				for(Constructor co:cons){
					System.out.println(co.toString());
				}
				lines=methods.length+cons.length;
				System.out.println(lines);	
			}else{
				
				
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}