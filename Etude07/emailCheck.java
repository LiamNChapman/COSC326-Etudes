import java.util.*;

public class emailCheck{

    static ArrayList<String> inputtedEmails;
    static String[] extensions = {"co.nz", "com.au", "co.ca", "com", "co.us", "co.uk"};

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String email = scan.nextLine();
            String emailCopy = email;
            if(email.length() > 0){
                email = email.toLowerCase();
                if(email.indexOf("@") == -1){
                    email = new StringBuilder(email).reverse().toString();
                    email = email.replaceFirst("_ta_", "@");
                    email = new StringBuilder(email).reverse().toString();
                }
                email = email.replaceAll("_dot_", ".");
                isValid(email, emailCopy);
            }
        }
    }

    public static void isValid(String email, String emailCopy){
        int index = 0;
        boolean invalid = false;
        String validationMsg = "";
        String currentEmail ="";
        boolean hasAt = false;
        int ipLenTracker = 0;
        //no periods, underscores or hyphens at the start
        if(email.charAt(0)=='-'||email.charAt(0)=='_'||email.charAt(0)=='.'){
            invalid = true;
            validationMsg = " <-- Invalid mailbox name";
        }
        //checks that the mailbox name is valid.
        for(int i = index; i<email.length(); i++){
            String singleChar = email.substring(i,i+1);
            //checking for an @
            if(singleChar.equals("@")){
                //no periods, underscores or hyphens before or no periods after the @
                if(email.charAt(i-1)=='-'||email.charAt(i-1)=='_'||email.charAt(i-1)=='.'||email.charAt(i+1)=='.'){
                    invalid = true;
                    validationMsg = " <-- Invalid mailbox name";
                    if(email.charAt(i+1)=='.'){
                        validationMsg = " <-- Invalid domain name";
                    }
                }
                hasAt = true;
                index = i+1;
                currentEmail += singleChar;
                break;
            }
            //checks for no double spacing characters
            if(singleChar.equals("-") || singleChar.equals("_") || singleChar.equals(".")){
                if(i > 1){
                    if(email.charAt(i-1)=='-'||email.charAt(i-1)=='_'||email.charAt(i-1)=='.'){
                        invalid = true;
                        validationMsg = " <-- Invalid mailbox name";
                    }
                }
            }
            //checking for invalid mailbox characters
			if((!Character.isLetterOrDigit(singleChar.charAt(0)) && !singleChar.equals("-") && !singleChar.equals("_") && !singleChar.equals("."))){
                invalid = true;
                validationMsg = " <-- Invalid mailbox name";
            }
            currentEmail += singleChar;
        }
        //invalid message if no @
        if(!hasAt && !invalid){
            validationMsg = " <-- Missing @ symbol";
            currentEmail += validationMsg;
            System.out.println(currentEmail);
            return;
        }
			
        //checks if the doamin is in numeric form
        if(index < email.length()){
            if(email.substring(index,index+1).equals("[")){ //opening ip brace
                currentEmail += email.substring(index,index+1);
                String numberHolder = "";
                index++;
                //Checks the next character isnt a closing bracket or period
                if(index < email.length()-1){
                    if(email.charAt(index+1) == ']' || email.charAt(index+1) == '.'){
                        invalid = true;
                        validationMsg = " <-- Invalid numeric domain";
                        if(email.charAt(index+1) == ']'){
                            return;
                        }
                    }
                }
                for(int i = index; i<email.length(); i++){
                    String singleChar = email.substring(index,index+1);
                    //check the characters in the ip are vaild
                    if(Character.isDigit(singleChar.charAt(0)) || singleChar.equals(".") || singleChar.equals("]")){
                        if(Character.isDigit(singleChar.charAt(0))){
                            numberHolder += singleChar;
                        } else if ( singleChar.equals(".")){
                            ipLenTracker++;
                            if(numberHolder.length() > 0){
                                //checks the value of the numbers is no grater than 255;
                                int ip = Integer.parseInt(numberHolder);
                                if(( ip > 255 || ip < 0) && !invalid){
                                    invalid = true;
                                    validationMsg = " <-- Invalid numeric domain";
                                }
                                currentEmail += Integer.toString(ip);
                            } else {
                                invalid = true;
                                validationMsg = " <-- Invalid numeric domain";
                            }
                            currentEmail += singleChar;
                            numberHolder = "";
                        } else if( singleChar.equals("]")){ //ending ip brace
                            if(numberHolder.length() > 0){
                                int ip = Integer.parseInt(numberHolder);
                                if(( ip > 255 || ip < 0) && !invalid){
                                    invalid = true;
                                    validationMsg = " <-- Invalid numeric domain";
                                }
                                currentEmail += Integer.toString(ip);
                            } else {
                                invalid = true;
                                validationMsg = " <-- Invalid numeric domain";
                            }
                            currentEmail += singleChar;
                            numberHolder = "";
                            index++;
							if(email.length()-1 > index){
								invalid = true;
								validationMsg = " <-- Invalid numeric domain";
								System.out.println(emailCopy + "" + validationMsg);
								return;
							}
                            break;
                        } 
                        index++;
                    } else {
						invalid = true;
						validationMsg = " <-- Invalid numeric domain";
						System.out.println(emailCopy + "" + validationMsg);
						return;
					}
                }
                //checks that the numeric domain is the right size
                if(ipLenTracker > 3 || ipLenTracker < 3){
                    invalid = true;
                    validationMsg = " <-- Invalid numeric domain";
                }
                if(invalid){
					System.out.println(emailCopy + "" + validationMsg);
				} else {
					System.out.println(currentEmail);
				}
                return;
            }
        }

        //checks that the domain is valid.
        for(int i = index; i<email.length(); i++){
            String singleChar = email.substring(i,i+1);
				
            //checks for no double periods
            if(singleChar.equals(".")){
                if(i > 1){
                    if(email.charAt(i-1)=='.'){
                        invalid = true;
                        validationMsg = " <-- Invalid domain name";
                    }
                }
            }
            //checking for valid characters
            if((!Character.isLetterOrDigit(singleChar.charAt(0)) && !singleChar.equals(".")) && !invalid){
                invalid = true;
                validationMsg = " <-- Invalid domain name";
            }
            currentEmail += singleChar;
        }
			
        //Checks the domain extension
        boolean validExtension = false;
        for(String extension : extensions){
            if(email.endsWith(extension)){
                validExtension = true;
            }
        }
        if(!validExtension && !invalid){
            invalid = true;
            validationMsg = " <-- Invalid extension";
        }
        if(invalid){
            System.out.println(emailCopy + "" + validationMsg);
        } else {
            System.out.println(currentEmail);
        }
    }
}
