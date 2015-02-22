
public class in2inf_i {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+i("inihalo"));
	}
	
	public static String i(String str_input){
		String str_result = " ";
		String root =" ";
		String affix = " ";
		String tense = "past";
		char[] arr = str_input.toCharArray();
		
		if(!in2inf_um.vow(arr[1]) && arr[2]=='i' && arr[3]=='n' ){
			if(str_input.indexOf("ipinag")==0 && arr[6]==arr[8] && arr[7]==arr[9]){
				tense="present";
				affix ="i- and -in-";
				str_result = "ipag" + str_input.substring(8, str_input.length());
				root= str_input.substring(8, str_input.length());
			}else if(arr[1]==arr[5] && arr[4]==arr[6]){
				tense="present";
				affix = "i- and -in-";
				str_result=arr[0] + str_input.substring(5, str_input.length());
				root= str_input.substring(5, str_input.length());
			}else{
				affix ="i- and -in-";
				str_result = arr[0] +""+arr[1] +str_input.substring(4, str_input.length());
				root= arr[1] +str_input.substring(4, str_input.length());
			}
		}else if(!in2inf_um.vow(arr[1]) && arr[0]=='i' && arr[1]==arr[3] && arr[2]==arr[4]){
			tense="future";
			affix = "i-";
			str_result= 'i' + str_input.substring(3, str_input.length());
			root= str_input.substring(3, str_input.length());
		}else if(str_input.indexOf("ipag")==0 && arr[4]==arr[6] && arr[5]==arr[7]){
			tense = "future";
			affix= "i- and pag-";
			str_result="ipag" + str_input.substring(6, str_input.length());
			root= str_input.substring(6, str_input.length());
		}else if(str_input.indexOf("ini")==0 && (arr[3] =='h'|| arr[3] =='l'|| arr[3] =='y'|| arr[3] =='w') && in2inf_um.vow(arr[4])){
			if(arr[3]==arr[5]  && arr[4]==arr[6]){
				tense="present";
				affix="i- and -ni-";
				str_result= arr[0] + str_input.substring(5, str_input.length());
				root= str_input.substring(5, str_input.length());
			}else{
				tense = "past";
				affix="i- and -ni-";
				str_result= arr[0] + str_input.substring(3, str_input.length());
				root= str_input.substring(3, str_input.length());
			}
		}
		
		System.out.println(str_input+" is in the "+tense+" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
	}
}
