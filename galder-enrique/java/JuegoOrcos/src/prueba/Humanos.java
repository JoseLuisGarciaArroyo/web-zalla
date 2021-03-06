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

		ArrayList<Orco> ejercitoOrco = Prueba.Orcos.Orcos;

		int pos = 0;

		for (Humano soldadoSelecc : Humanos) {

			if(ejercitoOrco.isEmpty()){
				
				return;
				
			}
			
			if (pos >= ejercitoOrco.size())
				pos = 0;

			Object enemigo = ejercitoOrco.get(pos);

			Orco orco = (Orco) enemigo;

			if (soldadoSelecc.isVivo()) {

				switch (orco.getRaza()) {

				case "Orco":

					int vida = (soldadoSelecc.atacar() - orco.getArmadura() );

					orco.herir(vida);

					if (!orco.isVivo()) {

						Prueba.Orcos.setBajas(1);
						System.out.println("LLEGO HUMANOS   " + Prueba.Orcos.getBajas());

						Orco auxOrco = ejercitoOrco.get(ejercitoOrco.size() - 1);
						ejercitoOrco.set(ejercitoOrco.size() - 1, orco);
						ejercitoOrco.remove(ejercitoOrco.size() - 1);

					}
					if (ejercitoOrco.indexOf(enemigo) == ejercitoOrco.size()) {
						pos = 0;

					}
					pos++;

				}
			}

		}

	}
}
