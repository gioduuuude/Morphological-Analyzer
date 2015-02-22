import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class syllable {
	public static String getNthSyllable(int syllableRow, String str_input){
		String result = "";
		int counter = 0;
		Queue<String> syllables = new LinkedList<String>();
		char[] arr = str_input.toCharArray();
		for(int i=0; i<str_input.length(); i++){
			if(in2inf_um.vow(arr[i])){
				counter++;	
			}
		}
		String temp = "";
		for(int ctr=0; ctr<str_input.length(); ctr++){
			if(in2inf_um.vow(arr[ctr])){
				if(syllables.size() < counter-1){
					temp = temp + arr[ctr];
					syllables.add(temp);
					temp="";
				}else {
					temp = temp + str_input.substring(ctr, str_input.length());
					syllables.add(temp);
					temp="";
				}
			}else{
				temp = temp + arr[ctr];
			}
		}
		Object[] syllable = syllables.toArray();
		final String[] Syllables = Arrays.copyOf(syllable, syllable.length, String[].class);
		 result = Syllables[syllableRow-1];
		return result;
	}
}
