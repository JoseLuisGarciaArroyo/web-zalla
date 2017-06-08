package prueba;

import java.util.ArrayList;

public class Humanos implements Razas {

	ArrayList<Humano> Humanos = new ArrayList<Humano>();
	private int bajas;

	public int getBajas() {
		return bajas;
	}

	public void setBajas(int bajas) {
		this.bajas += bajas;
	}

	public Humanos(int tamanio) {
		Creacion(tamanio);
	}

	@Override
	public ArrayList<Humano> Creacion(int tamanio) {

		for (int i = 0; i < tamanio; i++) {

			Humano soldado = new Humano();

			Humanos.add(soldado);
		}

		return Humanos;

	}

	@Override
	public void combate() {

		
		
		int pos = 0;

		for (Unidad soldadoSelecc : Humanos) {

			if (ejercitoEnemigo.isEmpty()) {

				return;

			}
			if (pos >= ejercitoEnemigo.size())
				pos = 0;

			Unidad enemigo = (Unidad) ejercitoEnemigo.get(pos);

			int vida = (soldadoSelecc.atacar() - enemigo.getArmadura());

			enemigo.herir(vida);

			if (soldadoSelecc.isVivo()) {

				switch (enemigo.getRaza()) {

				case "Humano":

					if (!enemigo.isVivo()) {

						Prueba.Humanos.setBajas(1);
						System.out.println("LLEGO Humanos  " + Prueba.Humanos.getBajas());

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
