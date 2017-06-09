package empresa;

public class Persona extends DNI {
	
	private String Nombre, Apellidos;
	private int edad;
	private DNI dni;
	
	public Persona(int edad,int numero, char letra ,String ...Palabras){
		super(numero,letra);
		
		Nombre = Palabras[0];
		Apellidos=Palabras[1];
		this.edad=edad;
		
	}
	public Persona(){
		super(0000000, 'L');
		Nombre="NO TENGO NOMBRE";
		Apellidos="NO TENGO APELLIDO";
		edad=00;
	}
	
	
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String toString(){
		
		return "MI NOMBRE COMPLETO ES: "+Nombre+" "+Apellidos+" TENGO "+edad+" Y MI DNI ES: "+super.toString();
	}

	
	
}
