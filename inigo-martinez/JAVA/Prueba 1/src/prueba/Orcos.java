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

		int bajas = 0, pos = 0;

		for (Orco soldadoSelecc : Orcos) {

			if (pos >= ejercitoHumanos.size())
				pos = 0;

			Object enemigo = ejercitoHumanos.get(pos);

			Humano humano = (Humano) enemigo;

			if (soldadoSelecc.isVivo()) {

				switch (humano.getRaza()) {

				case "Humano":

					Humano enemigoHumano = (Humano) enemigo;
					int vida = (humano.getArmadura() - soldadoSelecc.atacar());

					if (vida <= 0) {

						enemigoHumano.setPV(0);
						Prueba.Humanos.setBajas(1);
						enemigoHumano.setVivo(false);
						System.out.println("LLEGO3" + Prueba.Humanos.getBajas());

					} else {

						humano.setPV(vida);

					}
					if (ejercitoHumanos.indexOf(enemigo) == ejercitoHumanos.size()) {
						pos = 0;

					}
					if (Prueba.Humanos.getBajas() != 0) {

						Prueba.Humanos.Ordenar();

					}
					pos++;
				}
			}

		}

	}

	public void Ordenar() {

		int i;
		Orco aux;
		for (i = 0; i < Orcos.size() - 1; i++) {
			if (Orcos.get(i + 1).isVivo() && !Orcos.get(i).isVivo()) {
				aux = Orcos.get(i + 1);
				Orcos.set(i + 1, Orcos.get(i));
				Orcos.set(i, aux);
			}
		}
		for (i = 0; i < Orcos.size() - 1; i++) {

			System.out.println(Orcos.get(i));
			System.out.println(Orcos.size());

			if (!Orcos.get(i).isVivo())

				Orcos.remove(i);

		}
	}

}
