import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.*;

public class Dates{
    static ArrayList<String> checkedDates = new ArrayList<String>();

    public static void main(String[] args){
        Scanner newDate = new Scanner(System.in);
        while(newDate.hasNext()){
            String date = newDate.nextLine();
			if(date.equals("")){
				continue;
			}
            checkDate(date);
        }
        for(String date : checkedDates){
            System.out.println(date);
        }
    }

    public static void checkDate(String date){
        String[] a = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] b = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        String[] c = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        List<String> monthList = Arrays.asList(a);
        List<String> monthListCaps = Arrays.asList(b);
        List<String> monthListLowers = Arrays.asList(c);
        Integer[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String days = "", months = "", years = "";

        boolean leapYear = false;

        String finalDate = "";
		if(!Character.isDigit(date.charAt(0))){
			checkedDates.add(date + " - INVALID: Format.");
                return;
		}

        //Splitting the string into 3 parts.
        if(date.contains("-")){
            if(date.contains(" ") || date.contains("/")){
                checkedDates.add(date + " - INVALID: Format.");
                return;
            }
            String[] parts = date.split("-");
			if(parts.length != 3){
				checkedDates.add(date + " - INVALID: Format.");
                return;
			}
            days = parts[0];
            months = parts[1];
            years = parts[2];
        } else if(date.contains(" ")){
            if(date.contains("-") || date.contains("/")){
                checkedDates.add(date + " - INVALID: Format.");
                return;
            }
            String[] parts = date.split(" ");
			if(parts.length != 3){
				checkedDates.add(date + " - INVALID: Format.");
                return;
			}
            days = parts[0];
            months = parts[1];
            years = parts[2];
        } else if(date.contains("/")){
            if(date.contains(" ") || date.contains("-")){
                checkedDates.add(date + " - INVALID: Format.");
                return;
            }
            String[] parts = date.split("/");
			if(parts.length != 3){
				checkedDates.add(date + " - INVALID: Format.");
                return;
			}
            days = parts[0];
            months = parts[1];
            years = parts[2];
        }
        for(int i = 0; i < years.length(); i++){
            if(!Character.isDigit(years.charAt(i))){
                checkedDates.add(date + " - INVALID: Year input invalid.");
                return;
            }
        }
        String temp = years;
        if(years.length() == 2){
            if(Integer.parseInt(years) < 50 && Integer.parseInt(years) >= 00){
                temp = "20" + years;

            }
            if(Integer.parseInt(years) > 49 && Integer.parseInt(years) <= 99){
                temp = "19" + years;
            }
            years = temp;
        } else if(years.length() == 4){
            if(Integer.parseInt(years) < 1753 || Integer.parseInt(years) > 3000){
                checkedDates.add(date + " - INVALID: Year input invalid.");
                return;
            }
            
        } else {
			checkedDates.add(date + " - INVALID: Year input invalid.");
            return;
		}
        if(Integer.parseInt(years)%4==0){
            if(Integer.parseInt(years)%100==0 && Integer.parseInt(years)%400!=0){
            } else {
                leapYear = true;
            }
        }
         if(months.length() == 3){
             if(monthListCaps.contains(months)){
                 months = "" + (monthListCaps.indexOf(months) + 1);
             } else if(monthList.contains(months)){
                 months = "" + (monthList.indexOf(months) + 1);
             }  else if(monthListLowers.contains(months)){
                 months = "" + (monthListLowers.indexOf(months) + 1);
             } else {
                 checkedDates.add(date + " - INVALID: Month input invalid.");
                 return;
             }
         } else if(months.length() == 2){ //done
             if(!Character.isDigit(months.charAt(0)) || !Character.isDigit(months.charAt(1))){
                 checkedDates.add(date + " - INVALID: Month input invalid.");
                 return;
             }
             if(Integer.parseInt(months) > 12 || Integer.parseInt(months) < 1){
                 checkedDates.add(date + " - INVALID: Month input invalid.");
                 return;
             }
         } else if (months.length() == 1){ //done
             if(!Character.isDigit(months.charAt(0)) || months.charAt(0)=='0'){
                 checkedDates.add(date + " - INVALID: Month input invalid.");
                 return;
             }
         } else {
             checkedDates.add(date + " - INVALID: Month input invalid.");
             return;
         }
         int index = Integer.parseInt(months)-1;
         months = monthList.get(index);
         
         for(int i = 0; i < days.length(); i++){
             if(!Character.isDigit(days.charAt(i))){
                 checkedDates.add(date + " - INVALID: Day input invalid.");
                 return;
             }
         }

         int tempDay = Integer.parseInt(days);
         
         if(tempDay > monthDays[index] || tempDay <= 0){
             if(!(index == 1 && tempDay == 29 && leapYear)){
                 checkedDates.add(date + " - INVALID: Day input invalid.");
                 return;
             }
         }





         finalDate = days + " " + months + " " + years;
         checkedDates.add(finalDate);
    }
}
