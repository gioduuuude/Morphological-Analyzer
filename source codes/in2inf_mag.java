
public class in2inf_mag {
	public static void main(String[] args){
		System.out.print("it's infinitive form is -> "+mag("magpadyak"));
	}
	
	public static String mag(String str_input){
		String str_result = " ";
		String affix=" ";
		String root = " ";
		String tense = "past";
		
		if(str_input.startsWith("mag") || str_input.startsWith("mag-")){
			if(str_input.startsWith("mag-")){
				affix="(mag-)-";
				String therest = str_input.replace("-", "");
				therest = therest.substring(3,therest.length());
				if(syllable.getNthSyllable(1, therest).equals(syllable.getNthSyllable(2, therest))){
					tense="future";
					int counter = 0;
					char[] arr1 = therest.toCharArray();
					for(int i=0; i < therest.length(); i++){
						if(in2inf_um.vow(arr1[i])){
							counter++;	
						}
					}
					str_result="mag-";
					for(int j=1; j<=counter;j++)
					{
						
						if(j != 1){
							str_result = str_result + syllable.getNthSyllable(j, therest);
							root=root.replace(" ", "");
							root=root+syllable.getNthSyllable(j, therest);
						}
					}
				}
			}else if(str_input.startsWith("mag")){
				affix="mag-";
				String therest = str_input.substring(3,str_input.length());
				if(syllable.getNthSyllable(1, therest).equals(syllable.getNthSyllable(2, therest))){
					tense="future";
					int counter = 0;
					char[] arr1 = therest.toCharArray();
					for(int i=0; i < therest.length(); i++){
						if(in2inf_um.vow(arr1[i])){
							counter++;	
						}
					}
					str_result="mag";
					for(int j=1; j<=counter;j++)
					{
						
						if(j != 1){
							str_result = str_result + syllable.getNthSyllable(j, therest);
							root=root.replace(" ", "");
							root=root+syllable.getNthSyllable(j, therest);
						}
					}
				}
			}
			
		}else if(str_input.startsWith("nag") || str_input.startsWith("nag-")){
			int hypened=0;
			if(str_input.indexOf("-")==3){
               str_input=str_input.replace("-", "");
               hypened=1;
               str_result="mag-";
               affix="(nag-)-";
			}else{
				affix="nag-";
				str_result="mag";
			}
			
			String therest = str_input.substring(3,str_input.length());
			if(syllable.getNthSyllable(1, therest).equals(syllable.getNthSyllable(2, therest))){
				tense="present";
				int counter = 0;
				char[] arr1 = therest.toCharArray();
				for(int i=0; i < therest.length(); i++){
					if(in2inf_um.vow(arr1[i])){
						counter++;	
					}
				}
				
				for(int j=1; j<=counter;j++)
				{
					
					if(j != 1){
						str_result = str_result + syllable.getNthSyllable(j, therest);
						root=root.replace(" ", "");
						root=root+syllable.getNthSyllable(j, therest);
					}
				}
			}else if(hypened==1){
				str_result="mag-"+therest;
				root=therest;
			}else{
				str_result=str_result+therest;
				root=therest;
			}
			
		}
		
		
		System.out.println(str_input+" is in the "+ tense +" tense form of verb");
		System.out.println("-----------------------------> "+str_result);
		str_result = str_result+"`"+tense+"`"+affix+"`"+root;
		return str_result;
	}
}
