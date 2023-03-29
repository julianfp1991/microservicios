package prueba.Algoritmos;

import java.util.Arrays;
import java.util.Scanner;

public class Prefijo {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner dato = new Scanner(System.in);
		Solution2 pref = new Solution2();
		
	//	System.out.print("Ingrese algo: ");
	//	 = dato.next();
		
		String[] valoresRecibidos = new String[3];

		for (int i = 0; i < 3; i++) {
			System.out.print("Ingrese algo: ");
		    valoresRecibidos[i] = dato.next();
		    
		}
		System.out.println(pref.longestCommonPrefix(valoresRecibidos));
		
	}

}


class Solution2 {
	
	/*
	 * Input: strs = ["flower","flow","flight"]
	 * Output: "fl"
	 * 
	 * Input: strs = ["dog","racecar","car"]
	 * Output: ""
	 * Explanation: There is no 
	 * common prefix among the input strings.
	 */			
	
	
    public String longestCommonPrefix(String[] strs) {
        
        if (strs == null || strs.length == 0)
            return "";
        
        Arrays.sort(strs);
        String primero = strs[0];
        String ultimo = strs[strs.length - 1];
        int control = 0;
        while(control < primero.length())
        {
            if (primero.charAt(control) == ultimo.charAt(control))
                control++;
            else
                break;
        }
        return control == 0 ? "" : primero.substring(0, control);
    }
}
