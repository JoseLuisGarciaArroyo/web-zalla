package prueba;

import java.util.ArrayList;

public class Humanos implements Razas {

	ArrayList<Humano> Humanos = new ArrayList<Humano>();

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
	public int combate(ArrayList<?> Enemigos) {

		int bajas = 0, pos = 0;

		for (Humano soldadoSelecc : Humanos) {

			Object enemigo = Enemigos.get(pos);		
			
			Orco orco = (Orco) enemigo;

			switch (orco.raza) {

			case "Orco":

				Orco enemigoOrco = (Orco) enemigo;

				int saludTotal = enemigoOrco.armadura + enemigoOrco.PV;

				if (soldadoSelecc.atacar() >= saludTotal) {

					Enemigos.remove(enemigo);
					bajas++;
					System.out.println("LLEGO4" + bajas);

				}
				if (Enemigos.indexOf(enemigo) == Enemigos.size()) {
					pos = 0;

				}
				pos++;

			}

		}

		return bajas;

	}
}
