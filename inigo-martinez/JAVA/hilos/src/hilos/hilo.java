package hilos;

public class hilo extends Thread {

	private double litros;
	private String nombre;

	public hilo(String nombre) {
		super();
		this.nombre = nombre;
		this.litros = 0.00;
	}

	public double verLitros() {

		return litros;
	}

	public void run() {

		double balde =(Math.random() * 3)+1;
		
		do {
			try {
				sleep(1000);
				litros += balde;
				System.out.println("EL CONCURSANTE " + nombre + " TIENE YA " + litros);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		} while (litros < 100);
		System.out.println("TERMIN�");
		interrupted();
	}
}
