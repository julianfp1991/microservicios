package prueba.Algoritmos;

import java.util.Scanner;

public class EnCaso {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner dato = new Scanner(System.in);
		
		Caso inicio = new Caso();
		
		System.out.print("Ingrese algo: ");
		int valorRecibido = dato.nextInt();
		
		// System.out.print("El valor en letras es: " +inicio.Suiche(valorRecibido));
		
		System.out.print(inicio.mientrasQue(valorRecibido));
		
	}

}


class Caso{
	
	int dato=0;
	
	public String Suiche(int v) {
		
		String value="";
		
		switch (v) {
		
		case 1:
			value="Uno";
			break;
			
		case 2:
			value="Dos";
			break;
		case 3:
			value="Tres";
			break;
		}
		
		return value;
		
	}
	
	public String mientrasQue(int v) {
		
		int contador = 0;
		String resultado = "";
		
		do {
			
		//	resultado = "Mensajes tras mensaje" +contador+"\n";
			resultado += "Mensajes tras mensaje" +contador+"\n";
			contador++;
			
		} while(contador <= v);
		return resultado;
	}

}
