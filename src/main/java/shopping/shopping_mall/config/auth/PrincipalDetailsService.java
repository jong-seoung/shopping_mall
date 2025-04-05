package shopping.shopping_mall.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shopping.shopping_mall.model.User;
import shopping.shopping_mall.repository.UserRepositroy;

/** 시큐리티 설정에서 loginProcessingUrl의 요청이 오면
 * 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행 **/
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositroy userRepositroy;

    /** 시큐리티 세션 => Authentication => UserDetails **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepositroy.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
        return new PrincipalDetails(userEntity);
    }
}
