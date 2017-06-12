package prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * El programa funciona correctamente
 * 
 * Hacer funcionar goblins. Esta �ltima parte te la dejo a ti Jon Ander.
 **/

public class Prueba {

	static int bajasJug, bajasMaq;
	static List<Unidad> muertosDefinitivos;

	public static void main(String args[]) {

		List<Unidad> ejercMaqu = new ArrayList<Unidad>();
		List<Unidad> ejercJugador = new ArrayList<Unidad>();
		List<Unidad> bajas = new ArrayList<Unidad>();

		Scanner teclado = new Scanner(System.in);

		int eleccion, tamanio;

		System.out.println("Elige uno de los bandos:");
		System.out.println("1.- Humanos");
		System.out.println("2.- Orcos");
		System.out.println("3.- Los poderosos Goblins[Aun en fase Alpha]");
		System.out.println("Recuerdo que las aldeas se defienden con un minimo:");
		System.out.println("Humanos ---> 20 soldados");
		System.out.println("Orcos ---> 50 soldados");
		System.out.println("Goblins ---> 80 soldados");

		eleccion = teclado.nextInt();

		System.out.println("Elige la raza ENEMIGA");
		System.out.println("1.- Humanos");
		System.out.println("2.- Orcos");

		int eleccEnemiga = teclado.nextInt();

		System.out.print("Introduce la cantidad de soldados de tu ejercito: ");
		tamanio = teclado.nextInt();
		try {

			switch (eleccion) {

			case 1:
				// Humanos
				crearEjercito("Humano", tamanio, ejercJugador);
				break;

			case 2:

				crearEjercito("Orco", tamanio, ejercJugador);
				break;

			case 3:
				// GOBLINS
				crearEjercito("Goblin", tamanio, ejercJugador);

				break;

			default:
				System.out.println("Opci�n no valida");

			}
			switch (eleccEnemiga) {
			case 1:
				// Humanos
				if (tamanio > 20)
					crearEjercito("Humano", tamanio, ejercMaqu);
				else
					crearEjercito("Humano", 20, ejercMaqu);
				break;

			case 2:// ORCOS
				if (tamanio > 50)
					crearEjercito("Orco", tamanio, ejercMaqu);
				else
					crearEjercito("Orco", 50, ejercMaqu);
				break;

			case 3:
				// GOBLINS
				if (tamanio > 80)
					crearEjercito("Goblin", tamanio, ejercMaqu);
				else
					crearEjercito("Goblin", 80, ejercMaqu);

				break;

			default:
				System.out.println("Opci�n no valida");

			}
			do {

				bajasMaq = combate(ejercJugador, ejercMaqu, bajas, bajasMaq);
				bajasJug = combate(ejercMaqu, ejercJugador, bajas, bajasJug);

				System.out.println(bajasJug);
				System.out.println(bajasMaq);
				System.out.println("TOTAL " + (bajasMaq + bajasJug));

			} while (!ejercMaqu.isEmpty() && !ejercJugador.isEmpty());

			mensajesFinal(ejercMaqu, ejercJugador);
			eventos(ejercMaqu, ejercJugador, bajas);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		teclado.close();
	}

	public static void crearEjercito(String raza, int tamanio, List<Unidad> ejercito) {
		Unidad soldado;

		for (int i = 0; i < tamanio; i++) {

			switch (raza) {
			case "Humano":
				soldado = new Humano();
				ejercito.add(soldado);
				break;
			case "Orco":
				soldado = new Orco();
				ejercito.add(soldado);
				break;
			case "Goblin":
				soldado = new Goblin();
				ejercito.add(soldado);
				break;
			}

		}

	}

	public static int combate(List<Unidad> ejercAtac, List<Unidad> ejercDefen, List<Unidad> arrayBajas, int bajas) {

		int pos = 0;

		for (Unidad unidadAtac : ejercAtac) {

			if (ejercDefen.isEmpty()) {

				return bajas;

			}
			if (pos >= ejercDefen.size())
				pos = 0;

			Unidad enemigo = (Unidad) ejercDefen.get(pos);

			if (unidadAtac.isVivo()) {

				int danioRealizado = (unidadAtac.atacar() - enemigo.getArmadura());

				enemigo.herir(danioRealizado);

				if (!enemigo.isVivo()) {

					bajas++;
					System.out.println("LLEGO " + unidadAtac.getRaza() + " " + bajas);
					arrayBajas.add(enemigo);
					ejercDefen.remove(ejercDefen.indexOf(enemigo));

					if (ejercDefen.indexOf(enemigo) == ejercDefen.size()) {
						pos = 0;

					}
					pos++;
				}
			}
		}
		return bajas;
	}

	@SafeVarargs
	public static void mensajesFinal(List<Unidad>... ejercitos) {

		if (!ejercitos[0].isEmpty()) {

			Unidad auxUnid = ejercitos[0].get(0);
			System.out.println("Ganador es la m�quina con el ejercito de " + auxUnid.getRaza());

		} else {

			Unidad auxUnid = ejercitos[1].get(0);
			System.out.println("Ganador es la jugador con el ejercito de " + auxUnid.getRaza());

		}
	}

	@SafeVarargs
	public static void eventos(List<Unidad>... ejercitos) {

		List<Unidad> ejercitoSobreviviente;
		String eleccion;
		Scanner teclado = new Scanner(System.in);

		if (!ejercitos[0].isEmpty())
			ejercitoSobreviviente = ejercitos[0];
		else
			ejercitoSobreviviente = ejercitos[1];

		int evento = 1;// (int) Math.floor(Math.random() * (2) + 1);
		switch (evento) {

		case 1:
			System.out.println("�OH! Los muertos piden venganza. � Qu� hacemos?");
			System.out.println("Escribe CONTINUAR para entrar en combate por los vivos.");
			eleccion = teclado.nextLine();

			if (eleccion.equals("CONTINUAR") || eleccion.equals("continuar")) {
				// ALZAMIENTO DE LOS MUERTOS.RESURRECI�N
				crearEjercito("Humano", 10, ejercitoSobreviviente);
				crearEjercito("Orco", 25, ejercitoSobreviviente);
				crearEjercito("Goblin", 40, ejercitoSobreviviente);

				for (int pos = 0; pos < ejercitos[2].size() - 1; pos++) {

					Unidad resucitando = ejercitos[2].get(pos);

					resucitando.resucitar();

				}

				int bajas = 0;

				do {

					bajas += combate(ejercitoSobreviviente, ejercitos[2], muertosDefinitivos, 0);
					bajas += combate(ejercitos[2], ejercitoSobreviviente, muertosDefinitivos, 0);

				} while (!ejercitoSobreviviente.isEmpty() && !ejercitos[2].isEmpty());

				if (ejercitoSobreviviente.isEmpty())

					System.out.println("PERDISTE");

				else

					System.out.println("La victoria es para los vivos");

				return;
			}

			System.out.println("RUN NOOB, RUUUUUUN");
			break;
		default:
			System.out.println("NO pas� nada. La lluvia cae en el campo de combate.");
		}

	}
}
