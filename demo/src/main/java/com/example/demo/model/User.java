package com.example.demo.model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uid;

	


	private String name;
	@Column(unique = true)
	private String mobno;
	@Transient // @Transient does not allow decryptedPassword coloumn to be
	// created inside db
	String decryptedPassword = "";
//	

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = p.matcher(mobno);

		if (m.find() && m.group().equals(mobno) == true) {
			System.out.println("valid no");
			this.mobno = mobno;
		} else {
			System.out.println("wrong no");
		}
	}

	@Column(unique = true)
	private String email;
	private String pass;

	public String getPass() {

		pass = decryptedPassword;
		return pass;
		// return decryptedPassword;
	}

	public void setPass(String pass) throws UnsupportedEncodingException, GeneralSecurityException, IOException {

		this.pass = pass;
		//System.out.println("password in user model="+pass);
		byte[] salt = new String("12345678").getBytes();
//
//        // Decreasing this speeds down startup time and can be useful during testing, but it also makes it easier for brute force attackers
		int iterationCount = 40000;
//        // Other values give me java.security.InvalidKeyException: Illegal key size or default parameters
		int keyLength = 128;
		SecretKeySpec key = createSecretKey(pass.toCharArray(), salt, iterationCount, keyLength);

		 String originalPassword = "secret";
		System.out.println("Original password: " + pass);
		String encryptedPassword = encrypt(pass, key);
		this.pass = encryptedPassword;
		System.out.println("Encrypted password: " + encryptedPassword);
		decryptedPassword = decrypt(encryptedPassword, key);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (pat.matcher(email).matches() == true) {
			this.email = email;
		}
	}

	public Long getId() {
		return uid;
	}

	public void setId(Long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	}

	private static String encrypt(String property, SecretKeySpec key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
	}

	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
		String iv = string.split(":")[0];
		String property = string.split(":")[1];
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws IOException {
		return Base64.getDecoder().decode(property);
	}

}
