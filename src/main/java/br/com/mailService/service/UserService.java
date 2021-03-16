package br.com.mailService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mailService.services.exceptions.ObjectNotFoundException;

import br.com.mailService.entity.Users;
import br.com.mailService.entity.enums.Perfil;
import br.com.mailService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public List<Users> findAll() {
		return repository.findAll();
	}
	
	public Users findById(Integer id) {
		Optional<Users> u = repository.findById(id);
		return u.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado usuário com esse id: " + id));
	}
	
	public void insert(Users user) {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		user.setId(null);
		user.setCreatedDate(date);
		user.setAdmin(Perfil.ADMIN);
		user.setSenha(pe.encode(user.getSenha()));
		repository.save(user);
	}
	
	public void registrar(Users user) {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		user.setId(null);
		user.setCreatedDate(date);
		user.setSenha(pe.encode(user.getSenha()));
		user.setAdmin(Perfil.ADMIN);
		repository.save(user);
	}
	
	public void updatePassword(Users user) {
		Optional<Users> u = repository.findById(user.getId());
		u.get().setSenha(pe.encode(user.getSenha()));
		repository.save(u.get());
	}
	
	public Users delete(int id) {
        Users user = findById(id);
        if(user != null){
            repository.delete(user);
        }
        return user;
    }
	
}
