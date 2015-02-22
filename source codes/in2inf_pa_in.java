
public class in2inf_pa_in {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+pa_in(""));
	}
	
	public static String pa_in(String str_input){
		String str_result = " ";
		String affix=" ";
		String root=" ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		int last_letter_vow = 0;
		for(int i=0; i<str_input.length(); i++){
			if(in2inf_um.vow(arr[i]) && i == str_input.length()-1){
				last_letter_vow = 1; 
			}
		}
		
		if(str_input.startsWith("pina") && last_letter_vow==1 ){
			affix="pina-";
		    if(arr[4]==arr[6] && arr[5]==arr[7]){
		    	tense="present";
				str_result = "pa"+ str_input.substring(6,str_input.length()) + "hin";
				root= str_input.substring(6,str_input.length());
		    }else {
		    	tense="past";
		    	str_result = "pa"+ str_input.substring(4,str_input.length()) + "hin";
		    	root= str_input.substring(4,str_input.length());
		    }
		}else if(str_input.startsWith("pina") && last_letter_vow==0){
			affix="pina-";
			if(arr[4]==arr[6] && arr[5]==arr[7]){
		    	tense="present";
				str_result = "pa"+ str_input.substring(6,str_input.length()) + "in";
				root= str_input.substring(6,str_input.length());
		    }else {
		    	tense="past";
		    	str_result = "pa"+ str_input.substring(4,str_input.length()) + "in";
		    	root= str_input.substring(4,str_input.length());
		    }
		}else if(str_input.startsWith("pa") && (arr[2]==arr[4] && arr[3]==arr[5])){
			    if(str_input.endsWith("hin")){
			        affix="pa- and -hin";
			        root = str_input.substring(4,str_input.length()-3);
			    }else if(str_input.endsWith("in")){
			    	affix="pa- and -in";
			    	root = str_input.substring(4,str_input.length()-2);
			    }else{
			    	affix="pa-";
			    	root = str_input.substring(4,str_input.length());
			    }
			    tense="future";
			    str_result = "pa"+ str_input.substring(4,str_input.length());
		}
		
		System.out.println(str_input+" is in the "+ tense +" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
		
	}
}
