package prueba;

// La armadura no funciona correctamente. Deberia de reducir el da�o, no sumarse directamente.

public class Humano {

	int PV, danio, armadura;
	String raza;

	public Humano() {
		raza = "Humano";
		this.PV = (int) (Math.random() * 4) + 2;
		this.danio = (int) (Math.random() * 3) + 4;
		this.armadura = (int) (Math.random() * 4) + 4;
	}

	public int atacar() {

		int danioRealizado = (int) (Math.random() * (this.danio));
		return danioRealizado;

	}

	@Override
	public String toString() {
		return "Humano";
	}

}
