package geom3d;

import geom2d.Circulo;

public class Cilindro extends Figura3D {

	public Cilindro(Circulo base, double altura) {
		super(base, altura);
	}

	@Override
	public double areaBase() {
		return base.area();
	}

}
