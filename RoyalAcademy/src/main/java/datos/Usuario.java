package datos;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPersona")
public class Usuario extends Persona {
	private String nombreUsuario;
	private String contraseña;
	private boolean activo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "UsuarioPermiso",
    joinColumns = { @JoinColumn(name = "idPersona") },
    inverseJoinColumns = { @JoinColumn(name = "idPermiso") })
	private Set<Permiso> lstPermiso;
	
	public Usuario(Integer idPersona, String nombre, String apellido, String numeroDocumento, String email,
			GregorianCalendar fechaNacimiento, String genero, String celular, String pais, String ciudad,
			TipoDocumento tipoDocumento, String nombreUsuario, String contraseña, boolean activo) {
		super(idPersona, nombre, apellido, numeroDocumento, email, fechaNacimiento, genero, celular, pais, ciudad,
				tipoDocumento);
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.activo = activo;
	}

	public Usuario() {}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<Permiso> getLstPermiso() {
		return lstPermiso;
	}

	public void setLstPermiso(Set<Permiso> lstPermiso) {
		this.lstPermiso = lstPermiso;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + ", activo=" + activo
				+ ", lstPermiso=" + lstPermiso + "]";
	}
}
