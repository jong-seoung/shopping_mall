package shopping.shopping_mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping_mall.model.User;

import java.util.Optional;

// CRUD 함수를 JpaRepository가 가지고 있음
// @Repository라는 어노테이션이 없어도 Ioc가능 -> JpaRepository를 상속했기 때문에
public interface UserRepositroy extends JpaRepository<User, Integer> {
    // findBy 규칙 -> Username 문법
    // select * from user where username = ?
    public User findByUsername(String username); // 자세한 내용은 JPA Query methods 함수

    // SELECT * FROM user WHERE provider = ?1 and providerId = ?2
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
