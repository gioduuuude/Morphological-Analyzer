
public class in2inf_an {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+an("binanatan"));
	}
	
	public static String an(String str_input){
		String str_result = " ";
		String affix=" ";
		String root = " ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		int counter = 0;
		for(int i=0; i<str_input.length(); i++){
			if(in2inf_um.vow(arr[i])){
				counter++;	
			}
		}
		if(syllable.getNthSyllable(counter, str_input).equals("han")){
			if(arr[1]=='i' && arr[2]=='n'){
				if(arr[0]==arr[4] && arr[3]==arr[5]){
					tense="present";
					affix = "-in- and -han";
					str_result = str_input.substring(4,str_input.length());
					root= str_input.substring(4,str_input.length()-3);
				}else{
					tense = "past";
					affix = "-in- and -han";
					str_result=arr[0]+ str_input.substring(3,str_input.length());
					root= arr[0]+str_input.substring(3,str_input.length()-3);
				}
			}
		}else if(arr[0]==arr[2] && arr[1]==arr[3] && syllable.getNthSyllable(counter, str_input).endsWith("an")){
			tense="future";
			affix = "-an";
			str_result= str_input.substring(2,str_input.length());
			root = str_input.substring(2,str_input.length()-2);
		}else if(str_input.endsWith("an")){
			if(arr[0]=='i' && arr[1]=='n'){
				if(arr[2]==arr[3]){
					tense = "present";
					affix = "in- and -an";
					str_result= str_input.substring(3,str_input.length());
					root=str_input.substring(3,str_input.length()-2);
				}else{
					tense = "past";
					affix = "in- and -an";
					str_result= str_input.substring(2,str_input.length());
					root=str_input.substring(2,str_input.length()-2);
				}
			}else if(arr[0]==arr[1]){
				tense="future";
				affix="-an";
				str_result = str_input.substring(1,str_input.length());
				root = str_input.substring(1,str_input.length()-2);
			}else if(arr[1]=='i' && arr[2]=='n'){
				if(arr[0]==arr[4] && arr[3]==arr[5]){
					tense="present";
					affix = "-in- and -an";
					str_result = str_input.substring(4,str_input.length());
					root = str_input.substring(4,str_input.length()-2);
				}else{
					tense = "past";
					affix = "-in- and -an";
					str_result=arr[0]+ str_input.substring(3,str_input.length());
					root = arr[0]+ str_input.substring(3,str_input.length()-2);
				}
			}
		}
		System.out.println(str_input+" is in the "+ tense +" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
	}
}
