package datos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Permiso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPermiso;
	private String nombre;
	private String descripcion;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lstPermiso")
	private Set<Usuario> lstUsuario;
	
	public Permiso(int idPermiso, String nombre, String descripcion) {
		super();
		this.idPermiso = idPermiso;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Permiso() {}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(Set<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", lstUsuario=" + lstUsuario + "]";
	}
}
