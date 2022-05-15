package com.finance.web.repository;

import com.finance.web.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.username like %:username%")
    List<Member> findByUsernameContaining(@Param("username") String username);

    @Query("select m from Member m where m.username = :username")
    Optional<Member> findOneByUsername(@Param("username") String username);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> findOneById(@Param("id") Long id);

    @Query("select m from Member m where m.subscription= :true")
    List<Member> findBySubscription();

    @Query("select m from Member m where m.email =:email")
    List<Member> findListByEmail(@Param("email") String email);
}
