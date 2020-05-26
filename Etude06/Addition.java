import java.util.Scanner;
public class Addition {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int inputCheck = 0;
		String num1 = "0", num2 = "0", output = "";
		int carry = 0, num1Index, num2Index;
		int base = 10;
		while(inputCheck != 3) {
			if(inputCheck == 0) {
				System.out.print("Enter base between 1-10: ");
				base = Integer.parseInt(scan.next());
				if(base > 0 && base <= 10) {
					inputCheck++;
				} else {
					System.out.println("Invalid base");
				}
			} else if(inputCheck == 1) {
				System.out.print("Enter First Number: ");
				num1 = scan.next();
				boolean invalid = false;
				for(int i = 0; i < num1.length(); i++) {
					if(!Character.isDigit(num1.charAt(i)) || (Character.getNumericValue(num1.charAt(i)) < 0 || Character.getNumericValue(num1.charAt(i)) > base - 1)) {
						invalid = true;
						break;
					}
				}
				if(invalid == false) {
					inputCheck++;
				} else {
					System.out.println("Invalid number");
				}
			} else if(inputCheck == 2) {
				System.out.print("Enter Second Number: ");
				num2 = scan.next();
				boolean invalid = false;
				for(int i = 0; i < num2.length(); i++) {
					if(!Character.isDigit(num2.charAt(i)) || (Character.getNumericValue(num2.charAt(i)) < 0 || Character.getNumericValue(num2.charAt(i)) > base - 1)) {
						invalid = true;
						break;
					}
				}
				if(invalid == false) {
					inputCheck++;
				} else {
					System.out.println("Invalid number");
				}
			}
		}
		num1Index = num1.length()-1;
		num2Index = num2.length()-1;
		while(num1Index >= 0 && num2Index >= 0) {
			int currentNum = Character.getNumericValue(num1.charAt(num1Index)) + Character.getNumericValue(num2.charAt(num2Index));
			int result = (currentNum  + carry) % base;
			carry = ((currentNum + carry) - ((currentNum + carry) % base))/base;
			output = result + output;
			num1Index--;
			num2Index--;
		}
		if(num1Index < 0 && num2Index < 0 && carry != 0) {
			output = carry + output;
		}
		if(num2Index < 0) {
			while(num1Index >= 0) {
				int currentNum = Character.getNumericValue(num1.charAt(num1Index)) + carry;
				if(currentNum == base){
					output = (currentNum % base) + output;
					carry = ((currentNum + carry) - ((currentNum + carry) % base))/base;
				} else {
					output = currentNum + output;
					carry = 0;
				}
				num1Index--;
			}
		} else {
			while(num2Index >= 0) {
				int currentNum = Character.getNumericValue(num2.charAt(num2Index)) + carry;
				if(currentNum == base){
					output = (currentNum % base) + output;
					carry = ((currentNum + carry) - ((currentNum + carry) % base))/base;
				} else {
					output = currentNum + output;
					carry = 0;
				}
				num2Index--;
			}
		}
		System.out.println(output);
		int remainder = 0;
		String result = "";
		for(int i = 0; i < output.length(); i++) {
			int nextDiv = ((remainder * base) + Character.getNumericValue(output.charAt(i))) / 2;
			remainder = ((remainder * base) + Character.getNumericValue(output.charAt(i))) % 2;
			result+= nextDiv;
		}
		
		if(result.charAt(0) == '0'){
			result = result.substring(1, result.length());
		}
		System.out.println(result + " Remainder: " + remainder);
	}
}