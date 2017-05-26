package prueba;

// La armadura no funciona correctamente. Deberia de reducir el da�o, no sumarse directamente.

public class Humano {

	private int PV, danio, armadura;
	private String raza;
	private boolean vivo;

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Humano() {
		vivo=true;
		raza = "Humano";
		this.PV = (int) (Math.random() * 2) + 2;
		this.danio = (int) (Math.random() * 3) + 8;
		this.armadura = (int) (Math.random()) + 4;
	}

	public int atacar() {

		int danioRealizado = (int) (Math.random() * (this.danio));
		return danioRealizado;

	}

	public int getPV() {
		return PV;
	}

	public void setPV(int pV) {
		PV = pV;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public int getArmadura() {
		return armadura;
	}

	public void setArmadura(int armadura) {
		this.armadura = armadura;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	@Override
	public String toString() {
		return "Humano"+this.PV;
	}

}
