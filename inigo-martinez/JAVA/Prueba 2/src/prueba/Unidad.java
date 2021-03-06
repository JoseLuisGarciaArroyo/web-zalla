package prueba;

public class Unidad {

	private int PV, danio, armadura;
	private String raza;
	private boolean vivo;

	public Unidad(String raza, int PV, int danio, int armadura) {
		vivo = true;
		this.raza = raza;

		this.PV = PV;
		this.danio = danio;
		this.armadura = armadura;

	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int getPV() {
		return PV;
	}

	public void setPV(int pV) {
		this.PV = pV;
	}
	public void daniar(int pV) {
		this.PV += pV;

		if (this.PV <= 0) {
			this.setVivo(false);
		}
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

		return this.raza + " " + this.PV + " armadura " + this.armadura + " da�o " + this.danio;

	}

	public void herir(int damage) {

		if (damage <= 0) {
			return;
		}
		this.daniar(-damage);

	}

	public int atacar() {

		int danioARealizado = (int) Math.floor((Math.random() * (this.danio) + 1));
		return danioARealizado;

	}

	public void resucitar() {

		int pV = 0, danio = 0, armadura = 0;
		switch (this.raza) {

		case "Orco":

			pV = (int) Math.floor(Math.random() * (25) + 1);
			danio = (int) Math.floor(Math.random() * (12) + 1);
			armadura = 1;
			break;
		case "Humano":

			pV = (int) Math.floor(Math.random() * (10) + 1);
			danio = (int) Math.floor(Math.random() * (12) + 1);
			armadura = (int) Math.floor(Math.random() * (2) + 1);
			break;
		case "Goblin":

			pV = (int) Math.floor(Math.random() * (10) + 2);
			danio = (int) Math.floor(Math.random() * (20) + 2);
			break;

		}

		this.setPV(pV);
		this.setDanio(danio);
		this.setArmadura(armadura);
		this.setVivo(true);

	}

}
