package empresa;

public abstract class Empleado extends Persona {
	
	private static int CodEmple=0;
	private double salario;
	
	public Empleado(int num, char letra,int edad,double sal, String ...NombreCompleto){
		super(edad,num,letra,NombreCompleto);
		CodEmple++;
		salario=sal;
		
	}
	
	public Empleado() {
		super();
		CodEmple++;
	}

	public double obtenerSueldo(){
		
		return salario;
		
	}
	
	public String toString(){
		
		return super.toString()+" SU CODIGO DE EMPLEADO ES: "+CodEmple;
	}

	public abstract void trabajar();
}
