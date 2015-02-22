
public class in2inf_maka {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+maka("makakakain"));
	}
	
	public static String maka(String str_input){
		String str_result = " ";
		String affix= " ";
		String root  = " ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		if(arr[0]=='n' && arr[1]=='a' && arr[2]=='k' && arr[3]=='a'){
			affix="naka-";
			if(arr[4]==arr[6] && arr[5]==arr[7]){
				tense="present";
				str_result="maka"+ str_input.substring(6,str_input.length());
				root = str_input.substring(6,str_input.length());
			}else if(arr[4]!=arr[6] && arr[5]!=arr[7]){
				str_result = "m"+str_input.substring(1,str_input.length());
				root = str_input.substring(4,str_input.length());
			}
		}else if((arr[0]=='m' && arr[1]=='a' && arr[2]=='k' && arr[3]=='a') &&(arr[4]==arr[6] && arr[5]==arr[7])){
			tense="future";
			affix="maka-";
			str_result= "maka"+ str_input.substring(6,str_input.length());
			root =   str_input.substring(6,str_input.length());
		}
		System.out.println(str_input+" is in the "+tense+" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
	}
}
