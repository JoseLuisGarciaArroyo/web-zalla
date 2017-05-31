package prueba;

public class Unidad {

	private int PV;
	private int danio;
	private int armadura;
	private String raza;
	private boolean vivo;

	public Unidad(String raza) {
		vivo = true;
		this.raza = raza;
		switch (raza) {
		
		case "Orco":

			this.PV = 4;
			this.danio = 9;
			this.armadura = 4;
			break;
		case "Humano":
			this.PV = 4;
			this.danio = 9;
			this.armadura = 4;
			break;
			
		case "Goblins":

			this.PV=(int)(Math.random()*2)+2;
			this.danio=(int)(Math.random()*3+2);
			this.armadura=(int) (Math.random()*3+3);
			break;
		}
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public int atacar() {

		int danioRealizado = (int) (Math.random() * (this.danio));
		return danioRealizado;

	}

	public int getPV() {
		return PV;
	}

	public void setPV(int pV) {
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

		return "Orco " + this.PV + " armadura " + this.armadura + " da�o " + this.danio;

	}

	public void herir(int damage) {

		if (damage <= 0) {
			return;
		}
		this.setPV(-damage);

	}

}
