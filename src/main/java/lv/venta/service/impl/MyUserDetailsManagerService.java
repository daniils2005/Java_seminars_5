package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lv.venta.helper.MyUserDetails;
import lv.venta.model.MyUser;
import lv.venta.repo.IMyUserRepo;

@Service
public class MyUserDetailsManagerService implements UserDetailsManager {

	@Autowired
	private IMyUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(userRepo.existsByUsername(username)) {
			MyUser userFromDB = userRepo.findByUsername(username);
			MyUserDetails userDetails = new MyUserDetails(userFromDB);
			return userDetails;
		} else {
			throw new UsernameNotFoundException(username + " nav atrasts");
		}
		
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
