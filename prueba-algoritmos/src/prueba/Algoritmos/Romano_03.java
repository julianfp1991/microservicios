package prueba.Algoritmos;

import java.util.Scanner;

public class Romano_03 {

	public static void main(String[] args) {
		

		@SuppressWarnings("resource")
		Scanner romano = new Scanner(System.in);
		
		Solution romanoIns =new Solution();
		
		System.out.println("Ingrese el numero romano: ");
		String valorRomano = romano.nextLine();
				
	//	System.out.println("el valor es "+romanoIns.validarV(valorRomano));
		System.out.print("el numero es  "+romanoIns.romanToInt(valorRomano));
		
	}
}


class Solution {
	
	
    public int validarV(String s) {
    	
    	if(s.equals("V")) {
    		
    		return 5;
    	}else {
    		return 0;
    	}
    	
        
    }
    
    public int romanToInt(String s) {
    	
    	/*
    	 * 3=III
    	 * 4=I-V
    	 * 5=V
    	 * 6=V-I
    	 * 58=LV-III
    	 * 1994=M-CM-XC-IV
    	 * 1773=M-DCC-LXX-III
    	 * 3999=MMM-CM-XC-IX
    	 * 
    	 * Symbol       Value
    	 * I             1
    	 * V             5
    	 * X             10
    	 * L             50
    	 * C             100
    	 * D             500
    	 * M             1000
    	 */
    	
        int nums[]=new int[s.length()];
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case 'M':
                    nums[i]=1000;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'X' :
                    nums[i]=10;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'I':
                    nums[i]=1;
                    break;
            }
        }
        int sum=0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1])
                sum-=nums[i];
            else
                sum+=nums[i];
        }
        return sum+nums[nums.length-1];
    }
}