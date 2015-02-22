
public class in2inf_ma {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+ma("nakikita"));
	}
	
	public static String ma(String str_input){
		String str_result = " ";
		String root=" ";
		String affix = " ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		if(arr[0]=='n' && arr[1]=='a' && !(arr[2]=='g')){
			affix="na-";
			if(arr[2]==arr[4] && arr[3]==arr[5]){
				tense="present";
				str_result="ma"+ str_input.substring(4,str_input.length());
				root = str_input.substring(4,str_input.length());
			}else if(arr[2]!=arr[4] && arr[3]!=arr[5]){
				str_result = "m"+str_input.substring(1,str_input.length());
				root = str_input.substring(2,str_input.length());
			}
		}else if((arr[0]=='m' && arr[1]=='a') &&(arr[2]==arr[4] && arr[3]==arr[5])){
			tense="future";
			affix="ma-";
			str_result= "ma"+ str_input.substring(4,str_input.length());
			root = str_input.substring(4,str_input.length());
		}
		System.out.println(str_input+" is in the "+tense+" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
		}
}
