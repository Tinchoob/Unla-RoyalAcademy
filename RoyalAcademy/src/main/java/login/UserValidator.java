package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import datos.Usuario;

public class UserValidator implements Validator {
	
	@Autowired
    private UserService userService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Usuario.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (usuario.getNombreUsuario().length() < 6 || usuario.getNombreUsuario().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(usuario.getNombreUsuario()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (usuario.getContraseña().length() < 8 || usuario.getContraseña().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!usuario.getPasswordConfirm().equals(usuario.getContraseña())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
	}

}
