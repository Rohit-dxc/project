package com.example.demo.dto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class UserDto {

	private Long uid;

	private String name;

	private String mobno;

	//String decryptedPassword = "";

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {

		this.mobno = mobno;

	}

	private String email;
	private String pass;

	public String getPass() {
		//System.out.println(pass);
		 //pass = decryptedPassword;
		return pass;
		
	 //return decryptedPassword;
	}

	public void setPass(String pass) throws UnsupportedEncodingException, GeneralSecurityException, IOException {

		// this.pass = pass;

		this.pass = pass;
		System.out.println(pass);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;

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

}