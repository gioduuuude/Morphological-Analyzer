
public class in2inf_in {
	public static void main(String[] args){                
		System.out.print("it's infinitive form is -> "+in("paplanuhin"));
	}
	
	public static String in(String str_input){
		String str_result = " ";
		String affix = " ";
		String str_temp = str_input;
		String root = " ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		int counter = 0;
		for(int i=0; i<str_input.length(); i++){
			if(in2inf_um.vow(arr[i])){
				counter++;	
			}
		}
		int counter2=0;
		for(int i=0; i<str_input.length(); i++){
			if(in2inf_um.vow(arr[i]) && counter2==counter-1 && arr[i]=='o'){
				str_temp = str_input.substring(0, i) + "u" + str_input.substring(i+1, str_input.length());
				counter2=0;
			}else if(in2inf_um.vow(arr[i])){
				counter2++;
			}
		}
		if(arr[0]=='i' && arr[1]=='n'){
			if(arr[2] != arr[3]){
				affix="in-";
				str_result = str_temp.substring(2, str_temp.length()) + "in";
				root = str_temp.substring(2, str_temp.length());
			}else if(arr[2] == arr[3]){
				tense = "present";
				affix="in-";
				str_result = str_temp.substring(3, str_temp.length()) + "in";
				root = str_temp.substring(3, str_temp.length());
			}
		}
		else if(arr[1]=='i' && arr[2]=='n'){
			if((arr[0] != arr[4])  && (arr[3] != arr[5])){
				affix="-in-";
				str_result = arr[0] + str_temp.substring(3, str_temp.length())+"in"; 
				root = arr[0] + str_temp.substring(3, str_temp.length());
			}
			if((arr[0] == arr[4])  && (arr[3] == arr[5])){
				affix="-in-";
				tense = "present";
				str_result = str_temp.substring(4, str_temp.length())+"in";
				root = str_temp.substring(4, str_temp.length());
			}
			if((arr[0] == arr[4]) && (arr[3]!=arr[5]) && (arr[3]==arr[6])){
				tense="present";
				affix="-in-";
				str_result = str_temp.substring(4, str_temp.length())+"in";
				root = str_temp.substring(4, str_temp.length());
			}
		}
		else if(syllable.getNthSyllable(counter, str_input).endsWith("in")){
			tense="future";
			affix="-in";
			if(arr[0] == arr[2] && arr[1] == arr[3]){
				str_result=str_input.substring(2, str_input.length());
				root = str_result.substring(0,str_result.length()-2);
			}else if(in2inf_um.vow(arr[0]) && in2inf_um.vow(arr[1])){
				str_result=str_input.substring(1, str_input.length());
				root = str_result.substring(0,str_result.length()-2);
			}else if(arr[0] == arr[2] && arr[1]!=arr[3] && arr[1]==arr[4] ){  
				str_result = str_input.substring(2, str_input.length());
				root = str_result.substring(0,str_result.length()-2);
			}
		}
		System.out.println(str_input+" is in the "+tense+" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
   }
}
