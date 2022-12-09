package telran.java45.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserProfile extends User implements Serializable{

	private static final long serialVersionUID = -8407742619715488658L;
	private boolean passwordNotExpired;

	public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean passwordNotExpired) {
		super(username, password, authorities);
		this.passwordNotExpired = passwordNotExpired;
	}
	
	public boolean isPasswordNotExpired() {
		return passwordNotExpired;
	}

}
