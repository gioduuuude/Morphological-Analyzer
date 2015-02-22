
public class in2inf_magpa {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+magpa("magpapakain"));
	}
	
	public static String magpa(String str_input){
		String str_result = " ";
		String affix=" ";
		String root =" "; 
		String tense = "past";
		char[] arr = str_input.toCharArray();
		
		if(str_input.startsWith("nagpa")){
			affix="nagpa-";
			if(arr[3]==arr[5] &&arr[4]==arr[6]){
				tense="present";
				str_result = "magpa" + str_input.substring(7, str_input.length());
				root = str_input.substring(7, str_input.length());
			}else{
				str_result = "m"+ str_input.substring(1, str_input.length());
				root = str_input.substring(5, str_input.length());
			}
		}else if(str_input.startsWith("magpa")){
			affix="magpa-";
			if(arr[3]==arr[5] &&arr[4]==arr[6]){
				tense="future";
				str_result = "magpa" + str_input.substring(7, str_input.length());
				root = str_input.substring(7, str_input.length());;
			}
		}
		
		System.out.println(str_input+" is in the "+ tense +" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
	}
}
