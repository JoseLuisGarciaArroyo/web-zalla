package prueba;

import java.util.ArrayList;

/*Raza creada para ver como lo habias ido haciendo,NO ES FUNCIONAL,aun*/

public class Goblins implements Razas{
	
	ArrayList<Goblin> Goblins = new ArrayList<Goblin>();

	public Goblins(int tamanio) {
		Creacion(tamanio);
	}

	@Override
	public ArrayList<Goblin> Creacion(int tamanio) {

		for (int i = 0; i < tamanio; i++) {

			Goblin soldado = new Goblin();

			Goblins.add(soldado);
		}

		return Goblins;

	}

	@Override
	public int combate() {

		ArrayList<Orco> ejercitoOrco = Prueba.Orcos.Orcos;

		int bajas = 0, pos = 0;

		for (Goblin soldadoSelecc : Goblins) {

			Object enemigo = ejercitoOrco.get(pos);

			Orco orco = (Orco) enemigo;

			switch (orco.raza) {

			case "Orco":

				Orco enemigoOrco = (Orco) enemigo;

				int vida = (soldadoSelecc.atacar() - orco.armadura) - orco.PV;

				if (vida<=0) {

					enemigoOrco.setPV(0);
					bajas++;
					System.out.println("LLEGO4" + bajas);

				} else {

					orco.setPV(vida);

				}
				if (ejercitoOrco.indexOf(enemigo) == ejercitoOrco.size()) {
					pos = 0;

				}
				if (bajas != 0) {

					Ordenar();

				}
				pos++;

			}

		}

		return bajas;

	}

	public void Ordenar() {

		int i;
		Goblin aux;
		for (i = 0; i < Goblins.size() - 1; i++) {
			if (Goblins.get(i + 1).PV < Goblins.get(i).PV) {
				aux = Goblins.get(i + 1);
				Goblins.set(i + 1, Goblins.get(i));
				Goblins.set(i, aux);
			}
		}
		for (i = 0; i < Goblins.size() - 1; i++) {

			System.out.println(Goblins.get(i));
			if (Goblins.get(i).getPV() == 0) {

				Goblins.remove(i);
			}

		}
	}

}
