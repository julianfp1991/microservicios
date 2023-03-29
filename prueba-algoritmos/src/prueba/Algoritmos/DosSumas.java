package prueba.Algoritmos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DosSumas {

	public static void main(String[] args) {

		DosSumas2 dosValores = new DosSumas2();
		
        int[] nums = {2, 7, 11, 15};
        int target = 22;
        int[] result = dosValores.twoSum(nums, target);
        if (result != null) {
            System.out.println("Los índices son: " + Arrays.toString(result));
        } else {
            System.out.println("No se encontraron pares que sumen el objetivo");
        }
		
		

	}

}

class DosSumas2{
	
    public int[] twoSum(int[] nums, int target) {
    	
    	
    	/*
    	 * Input: nums = [2,7,11,15], target = 9
    	 * Output: [0,1]
    	 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
    	 */
    	
        // Crear un mapa para almacenar el valor y su índice correspondiente
        Map<Integer, Integer> mapa = new HashMap<>();
        
        // Recorrer el array
        for (int i = 0; i < nums.length; i++) {
        	
       /*			
        * 			20		=	22	-	2[0]
        *			15		=	22	-	7[1]*
        *			11		=	22	-	11[2]
        *			7		=	22	-	15[3]*
        */
            int complemento = target - nums[i];
            

            // Verificar si el complemento ya está en el mapa
            if (mapa.containsKey(complemento)) {
            	
                // Si el complemento está en el mapa, retornar el índice actual y el índice almacenado en el mapa
                return new int[] {mapa.get(complemento), i};
            }
            
            
            //		2,	0
            //		7,	1
            //		11,	2
            //		15,	3
            // Si el complemento no está en el mapa, agregar el valor actual y su índice al mapa
            mapa.put(nums[i], i);
        }
        
        // Si no se encontraron pares que sumen el objetivo, retornar null
        return null;
    	
    }
}
