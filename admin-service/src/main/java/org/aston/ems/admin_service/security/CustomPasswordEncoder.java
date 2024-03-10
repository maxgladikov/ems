package org.aston.ems.admin_service.security;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder{
	
	private final TextEncryptor encryptor;
	
	public CustomPasswordEncoder() {
		String salt = "954dfbf3456bea59";
//		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		encryptor = Encryptors.text(password, salt);
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return encryptor.encrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		log.info("method matches invoked");
		return encryptor.decrypt(encodedPassword).equals(rawPassword.toString());
	}
	
	@PostConstruct
	void info() {
		String s = "secret";
		String encrypted = "cc4d70b5d22b80e75dcba61686ceb06975ab54069076488d04ae7cafc2fbf000";
//		String encrypted = encryptor.encrypt(s);
		
		log.debug("***************************");
		log.debug("initial value: {}",s);
//		log.info("salt: {}",KeyGenerators.string().generateKey());
		log.debug("encrypted value: {}", encrypted);
		log.debug("decrypted value: {}", encryptor.decrypt(encrypted));
	}

}
