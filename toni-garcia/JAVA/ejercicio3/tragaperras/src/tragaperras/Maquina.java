package tragaperras;

import java.util.Scanner;

public class Maquina {

	private static int dev = 0, jugadas = 0;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		String arrayFrutas[] = { "fresa", "sand�a", "pl�tano", "melocot�n", "pera" };
		String resultados[] = new String[3];
		jugadas = insertarSaldo();

		do {

			if (jugadas > 0) {

				resultados[0] = arrayFrutas[(int) (Math.random() * 4)];
				resultados[1] = arrayFrutas[(int) (Math.random() * 4)];
				resultados[2] = arrayFrutas[(int) (Math.random() * 4)];

				System.out.println(resultados[0] + "  " + resultados[1] + "   " + resultados[2]);

				System.out.println(tragaperras.Premio.resolver(resultados));

				jugadas--;
				salir();

			}

		} while (jugadas > 0);

		System.out.println("Sobran " + dev + "�");

	}

	public static boolean salir() {

		System.out.print("Pulsa Y si desea continuar y N si desea finalizar.");
		System.out.println("DESEAS CONTINUAR: ");
		char selecc = teclado.next().charAt(0);
		
		if (selecc == 'N') {
			dev = jugadas * 20;
			System.out.println(dev + " " + jugadas);
			jugadas = 0;
			return false;
		}
		return true;

	}

	public static int insertarSaldo() {

		do {
			System.out.println("INSERTA MONEDAS (CUENTA EN CENTIMOS)");
			int dinero = teclado.nextInt();

			dev = dinero % 20;

			System.out.println("INTENTOS GANADOS: " + (int) dinero / 20);

			if ((dinero / 20) > 0)
				return (int) dinero / 20;

		} while (true);

	}

}
