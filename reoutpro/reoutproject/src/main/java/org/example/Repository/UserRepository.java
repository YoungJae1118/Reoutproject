package org.example.Repository;

import org.example.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
/*
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
    //옵셔널 0개 일수도 있고 1개 일수도 있고 이름은 중복이 되니깐 리스트로 받은거야
*/
}

