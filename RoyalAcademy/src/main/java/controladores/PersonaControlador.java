package controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import abm.PersonaABM;
import datos.Persona;

@Controller
@RequestMapping(path="/Persona")
public class PersonaControlador {
	@Autowired
	private PersonaABM personaABM;
	
	@PostMapping(path="/add")
	public @ResponseBody String alta(@RequestBody Persona persona) {
		Persona nPersona = new Persona();
		nPersona.setNombre(persona.getNombre());
		nPersona.setApellido(persona.getApellido());
		nPersona.setNumeroDocumento(persona.getNumeroDocumento());
		nPersona.setEmail(persona.getEmail());
		nPersona.setFechaNacimiento(persona.getFechaNacimiento());
		nPersona.setGenero(persona.getGenero());
		nPersona.setCelular(persona.getCelular());
		nPersona.setPais(persona.getPais());
		nPersona.setCiudad(persona.getCiudad());
		nPersona.setTipoDocumento(persona.getTipoDocumento());
		personaABM.save(persona);
		return "Saved";
	}
	
	@PostMapping(path="/delete")
	public @ResponseBody String baja(@RequestBody Persona persona) {
		personaABM.delete(persona);
		return "Deleted";
	}
	
	@PostMapping(path="/update")
	public @ResponseBody String modificacion(@RequestBody Persona persona) {
		personaABM.save(persona);
		return "Updated";
	}
		
	@GetMapping(path="/read")
	public @ResponseBody Persona traer(@RequestBody Persona persona) {
		return personaABM.findById(persona.getIdPersona()).get();
	}
}
