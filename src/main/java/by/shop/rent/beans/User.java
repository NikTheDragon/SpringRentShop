package by.shop.rent.beans;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component ("user")
public class User {
	
	private int id;
	
	@Size (min = 3, message = "{size.error}")
	private String name;
	
	@Size (min = 3, message = "{size.error}")
	private String surname;
	
	@Size (min = 3, message = "{size.error}")
	private String email;
	
	@Size (min = 3, message = "{size.error}")
	private String phone;
	
	@Size (min = 3, message = "{size.error}")
	private String login;
	
	@Size (min = 3, message = "{size.error}")
	private String password;
	private String status;
	private String message;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessge(String message) {
		this.message = message;
	}
}
