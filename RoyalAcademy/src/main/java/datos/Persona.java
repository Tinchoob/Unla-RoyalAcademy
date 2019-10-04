package datos;

import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPersona;
	private String nombre;
	private String apellido;
	private String numeroDocumento;
	private String email;
	private GregorianCalendar fechaNacimiento;
	private String genero;
	private String celular;
	private String pais;
	private String ciudad;
	
	@ManyToOne
    @JoinColumn(name = "idTipoDocumento")
	private TipoDocumento tipoDocumento;
	
	public Persona(Integer idPersona, String nombre, String apellido, String numeroDocumento, String email,
			GregorianCalendar fechaNacimiento, String genero, String celular, String pais, String ciudad,
			TipoDocumento tipoDocumento) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroDocumento = numeroDocumento;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.celular = celular;
		this.pais = pais;
		this.ciudad = ciudad;
		this.tipoDocumento = tipoDocumento;
	}

	public Persona() {
		super();
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", numeroDocumento=" + numeroDocumento + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento
				+ ", genero=" + genero + ", celular=" + celular + ", pais=" + pais + ", ciudad=" + ciudad
				+ ", tipoDocumento=" + tipoDocumento + "]";
	}
}
