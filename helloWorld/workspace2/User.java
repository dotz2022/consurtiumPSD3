package sprint1;

public class User {
	private int GUID;
	private String password;
	private String role;
	private String name;
	
	public User (int GUID, String role, String name) {
		this.GUID = GUID;
		password = "password"; // when creating, set default password as "password"
		this.role = role;
		this.name = name;
	}

	public int getGUID() {
		return GUID;
	}

	public void setGUID(int gUID) {
		GUID = gUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
