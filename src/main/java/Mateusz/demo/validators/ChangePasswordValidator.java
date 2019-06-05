package Mateusz.demo.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import Mateusz.demo.constants.AppDemoConstants;
import Mateusz.demo.user.User;
import Mateusz.demo.utilities.AppdemoUtils;

public class ChangePasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		@SuppressWarnings("unused") // Adnotacja która usuwa informacje, że jest jakis nieuzywany obiekt
		User u = (User) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "error.userPassword.empty"); // jezeli pole jest pust, wyrzuca odpowiedni komunikat ze to pole nie moze byc puste
		
	}
	
	public void checkPasswords(String newPass, Errors errors) { //dwa argumenty, pierwszy to nowe hasło wprowadzone na formularzu a drugi to ewentualne błedy jesli takie sie pojawia
		
		if (!newPass.equals(null)) { // w tej metodzie sprawdzamy tylko i wylacznie czy haslo nie jest nulem
			boolean isMatch = AppdemoUtils.checkEmailOrPassword(AppDemoConstants.PASSWORD_PATTERN, newPass);
			if(!isMatch) {
				errors.rejectValue("newPassword", "error.userPasswordIsNotMatch");
			}
		}
	}
}