package br.com.mailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mailService.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	@Transactional(readOnly=true)
	Users findByEmail(String email);
}
