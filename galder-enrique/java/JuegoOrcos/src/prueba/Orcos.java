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

		ArrayList<Humano> ejercitoHumanos = Prueba.Humanos.Humanos;

		int pos = 0;

		for (Orco soldadoSelecc : Orcos) {

			if (ejercitoHumanos.isEmpty()) {

				return;

			}
			if (pos >= ejercitoHumanos.size())
				pos = 0;

			Object enemigo = ejercitoHumanos.get(pos);

			Humano humano = (Humano) enemigo;

			if (soldadoSelecc.isVivo()) {

				switch (humano.getRaza()) {

				case "Humano":

					int vida = (soldadoSelecc.atacar() - humano.getArmadura());

					humano.herir(vida);

					if (!humano.isVivo()) {

						Prueba.Humanos.setBajas(1);
						System.out.println("LLEGO ORCOS  " + Prueba.Humanos.getBajas());

						Humano auxHumano = ejercitoHumanos.get(ejercitoHumanos.size() - 1);
						ejercitoHumanos.set(ejercitoHumanos.size() - 1, humano);
						ejercitoHumanos.remove(ejercitoHumanos.size() - 1);

						if (ejercitoHumanos.indexOf(enemigo) == ejercitoHumanos.size()) {
							pos = 0;

						}
						pos++;
					}
				}

			}

		}
	}
}
