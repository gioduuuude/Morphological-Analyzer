
public class in2inf_um {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+um("alisan"));
	}
	
	public static String um(String str_input){
		String str_result = " ";
		String affix=" ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		if(vow(arr[0]) && vow(arr[1])){
			tense = "future";
			str_result = "um" + str_input.substring(1, str_input.length());
		}else if(arr[0] == arr[2] && arr[1] == arr[3]){
			tense = "future";
			str_result = arr[0] + "um" + str_input.substring(3, str_input.length());
		}else if(arr[0]=='d' && arr[2]=='r' && arr[1]==arr[3]){
			tense = "future";
			str_result = arr[0] + "um" + str_input.substring(3, str_input.length());
		}else if(str_input.length() >= 6 && ((arr[0]=='n'&&arr[1]=='g'))&&(arr[0]==arr[3]&&arr[1]==arr[4]&&arr[2]==arr[5])){
			str_result = arr[1] + "um" + str_input.substring(5, str_input.length());
			str_result = arr[0] + str_result;
		}else if(arr[0]=='u' && arr[1]=='m'){
			affix="um-";
			if(arr[2] != arr[3]){
				str_result = str_input;
			}else if(arr[2]==arr[3]){
				tense = "present";
				str_result = "um" +  str_input.substring(3, str_input.length());
			}
		}else if((arr.length > 6)&&(arr[1]=='u' && arr[2]=='m')){
			affix="-um-";
			if(arr[0]!=arr[4] && arr[3]!=arr[5]){
				affix="-um-";
				str_result = str_input;
			}else if(arr[0]==arr[4] && arr[3]==arr[5]){
				affix="-um-";
				tense="present";
				str_result = str_input.substring(0, 4) + str_input.substring(6, str_input.length());
			}else if((arr[3]==arr[5])&&(arr[0]=='d' && arr[4]=='r')){
				tense="present";
				affix="-um-";
				str_result = str_input.substring(0, 4) + str_input.substring(6, str_input.length());
			}
		}else if((arr.length > 7) && (arr[2]=='u' && arr[3]=='m')){
			affix="-um-";
			if(arr[0]!=arr[5] && arr[1]!=arr[6] && arr[4]!=arr[7]){
				str_result = str_input;
			}else if(arr[0]==arr[5] && arr[1]==arr[6] && arr[4]==arr[7]){
				tense="present";
				str_result = str_input.substring(0, 5) + str_input.substring(8, str_input.length());
			}
		}
		System.out.println(str_input+" is in the "+tense+" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		String root = " ";
		root = str_result.replace("um", "");
		str_result = str_result + "`" + tense+"`"+affix+"`"+root;
		
		return str_result;
	}

	public static boolean vow(char c) {
		boolean bool_result = false;
		if(c == 'a' || c == 'A' ){
			bool_result=true;
		}else if(c == 'e' || c == 'E'){
			bool_result=true;
		}else if(c == 'i' || c == 'I'){
			bool_result=true;
		}else if(c == 'o' || c == 'O'){
			bool_result=true;
		}else if(c == 'u' || c == 'U'){
			bool_result=true;
		}
		return bool_result;
	}  

}
