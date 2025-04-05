package shopping.shopping_mall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping_mall.model.User;

// CRUD 함수를 JpaRepository가 가지고 있음
// @Repository라는 어노테이션이 없어도 Ioc가능 -> JpaRepository를 상속했기 때문에
public interface UserRepositroy extends JpaRepository<User, Integer> {
}
