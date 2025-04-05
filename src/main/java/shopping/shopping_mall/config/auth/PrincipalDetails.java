package shopping.shopping_mall.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shopping.shopping_mall.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** 시큐리티가 /login을 낚아채서 로그인을 진행 시킨다.
 * 로그인을 진행히 완료과 되면 session을 만들어준다. (Security ContextHolder)
 * 오브젝트 => Authentication 타입의 객체를 넘겨줘야함
 * Authentication 안에 User 정보가 있어야 함
 * User 오브젝트 => UserDetail 타입 객체여야 함 **/
public class PrincipalDetails implements UserDetails {

    private User user; // 콤포지션

    public PrincipalDetails(User user){
        super();
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        /** 우리 사이트에서 1년동안 회원이 로그인을 안하면, 휴면 계정으로 하기로 함
         * 현재 시간 - 로그인 시간 => 1년을 초과하면 return false로 설정하면 됨 **/
        return true;
    }
}
