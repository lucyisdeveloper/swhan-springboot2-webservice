package com.swhan.project.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //이메일 검증으로 이미 생성된 사용자인지 확인하기 위해서
    Optional<User> findByEmail(String email);
}
