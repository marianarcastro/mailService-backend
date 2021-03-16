package br.com.mailService.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mailService.entity.Users;
import br.com.mailService.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserEndPoint {

	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Users> listUsers = service.findAll();
		return ResponseEntity.ok().body(listUsers);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Users u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public ResponseEntity<?> cadastrarNovoUsuario(@RequestBody Users user) {
		service.insert(user);
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/deletar/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public ResponseEntity<?> registrarUsuario(@RequestBody Users user) {
		service.registrar(user);
		return ResponseEntity.ok().body("");
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public ResponseEntity<?> alterar(@RequestBody Users user) {
		service.updatePassword(user);
		return ResponseEntity.ok().body("");
	}
}
