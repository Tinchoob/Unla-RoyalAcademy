package datos;

public class NotaAlumno {

	private Alumno alumno;
	private int nota;
	
	
	public NotaAlumno() {

	}
	
	
	public Alumno getPersona() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "NotaAlumno [alumno=" + alumno + ", nota=" + nota + "]";
	}
	
	
}
