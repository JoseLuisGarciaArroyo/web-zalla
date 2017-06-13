package prueba;

import java.util.ArrayList;

public class Orcos implements Razas {

	// CLASES
	ArrayList<Orco> Orcos = new ArrayList<Orco>();
	private int bajas;

	public int getBajas() {
		return bajas;
	}

	public void setBajas(int bajas) {
		this.bajas += bajas;
	}

	public Orcos(int tamanio) {
		Creacion(tamanio);
	}

	public ArrayList<Orco> Creacion(int tamanio) {

		for (int i = 0; i < tamanio; i++) {

			Orco soldado = new Orco();

			Orcos.add(soldado);
		}

		return Orcos;

	}

	@Override
	public void combate() {

		ArrayList<Razas> ejercitoEnemigo = (ArrayList<Razas>) Prueba.ejercMaqu;
		
		if (!this.Orcos.get(0).equals(ejercitoEnemigo.get(0))) {
			
			ejercitoEnemigo = (ArrayList<Razas>) Prueba.ejercJugador;
			
		}

		int pos = 0;

		for (Orco soldadoSelecc : Orcos) {

			if (ejercitoEnemigo.isEmpty()) {

				return;

			}
			if (pos >= ejercitoEnemigo.size())
				pos = 0;

			Unidad enemigo = (Unidad) ejercitoEnemigo.get(pos);

			if (soldadoSelecc.isVivo()) {

				switch (enemigo.getRaza()) {

				case "Humano":

					int vida = (soldadoSelecc.atacar() - enemigo.getArmadura());

					enemigo.herir(vida);

					if (!enemigo.isVivo()) {

						Prueba.Humanos.setBajas(1);
						System.out.println("LLEGO ORCOS  " + Prueba.Humanos.getBajas());

						ejercitoEnemigo.remove(ejercitoEnemigo.indexOf(enemigo));

						if (ejercitoEnemigo.indexOf(enemigo) == ejercitoEnemigo.size()) {
							pos = 0;

						}
						pos++;
					}
				}

			}

		}
	}
}