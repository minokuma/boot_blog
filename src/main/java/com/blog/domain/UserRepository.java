package com.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId); // findBy + 조회하고 싶은 필드명 입력(낙타기법)
}
