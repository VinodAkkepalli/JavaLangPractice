package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 
 * @author VinodAkkepalli
 *
 *	Problem statement:
 *	Write code to find if a given string is a valid IP Address using regex
 */

public class IPAddressValidationRegEX {

	public static void main(String[] args) {
		IPAddressValidator ipv = new IPAddressValidator();
		System.out.println(ipv.validate("10.10.23.101"));;

	}
}

class IPAddressValidator{
	
    private Pattern pattern;
    private Matcher matcher;
 
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	  
    public IPAddressValidator(){
	  pattern = Pattern.compile(IPADDRESS_PATTERN);
    }
	  
   /**
    * Validate ip address with regular expression
    * @param ip ip address for validation
    * @return true valid ip address, false invalid ip address
    */
    public boolean validate(final String ip){		  
	  matcher = pattern.matcher(ip);
	  return matcher.matches();	    	    
    }
}
