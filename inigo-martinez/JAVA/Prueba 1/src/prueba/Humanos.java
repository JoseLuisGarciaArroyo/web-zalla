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

			
			if(pos>=ejercitoOrco.size())pos=0;
			
			Object enemigo = ejercitoOrco.get(pos);

			Orco orco = (Orco) enemigo;

			if (!orco.isVivo()) {

				//for (int i = ejercitoOrco.lastIndexOf(orco); i < ejercitoOrco.size() - 1 && !orco.isVivo(); i++) {

					//orco = ejercitoOrco.get(i);

				//}
				
				orco=ejercitoOrco.get(0);

			}

			if (soldadoSelecc.isVivo()) {

				switch (orco.getRaza()) {

				case "Orco":

					Orco enemigoOrco = (Orco) enemigo;

					int vida = (orco.getArmadura() - soldadoSelecc.atacar());

					if (vida <= 0) {

						enemigoOrco.setPV(0);
						Prueba.Orcos.setBajas(1);
						enemigoOrco.setVivo(false);
						System.out.println("LLEGO4" + Prueba.Humanos.getBajas());

					} else {

						orco.setPV(vida);

					}
					if (ejercitoOrco.indexOf(enemigo) == ejercitoOrco.size()) {
						pos = 0;

					}
					if (Prueba.Orcos.getBajas()!= 0) {

						Prueba.Orcos.Ordenar();

					}
					pos++;

				}
			}

		}

	}

	public void Ordenar() {

		int i;
		Humano aux;
		for (i = 0; i < Humanos.size() - 1; i++) {
			if (Humanos.get(i + 1).isVivo() && !Humanos.get(i).isVivo()) {
				aux = Humanos.get(i + 1);
				Humanos.set(i + 1, Humanos.get(i));
				Humanos.set(i, aux);
			}
		}
		for (i = 0; i < Humanos.size() - 1; i++) {

			System.out.println(Humanos.get(i));
			System.out.println(Humanos.size());
			if (!Humanos.get(i).isVivo()) {

				Humanos.remove(i);
			}

		}
	}

}
