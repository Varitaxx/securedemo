package eu.asgardschmiede.securedemo.validator;


import eu.asgardschmiede.securedemo.annotation.PasswordMatch;

@PasswordMatch
public interface WithConfirmedPassword {

    String getPassword();

    String getPasswordConfirmation();
}
