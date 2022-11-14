package cz.cvut.fit.tjv.social_network.dao.jpa;

import cz.cvut.fit.tjv.social_network.dao.UserRepository;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends UserRepository, JpaRepository<User, String> {
}
