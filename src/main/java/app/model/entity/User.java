package app.model.entity;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String passwordDigest;
	private Boolean admin;
	
	public User() {}
	
	public User(Integer id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = null;
	}
	
	public User(String name, String email, String password) {
		this.id = null;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	// getByEmail
	public User(Integer id, String name, String email, String passwordDigest, Boolean admin) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwordDigest = passwordDigest;
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordDigest() {
		return passwordDigest;
	}

	public void setPasswordDigest(String passwordDigest) {
		this.passwordDigest = passwordDigest;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String hashedPassword() {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
//	public String hashedPassword() {
//		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(16);
//		return bcrypt.encode(password);
//	}

}

