package study.eatgo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import study.eatgo.config.JwtConfig;
import study.eatgo.domain.user.dao.UserRepository;
import study.eatgo.domain.user.domain.User;

//얘를 무조건 거쳐감.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtTokenProvider jwtUtil;

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(
        AuthenticationManager authenticationManager, JwtTokenProvider jwtUtil,
        UserRepository userRepository) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        //토큰의 유효성 검증
        String jwtToken = getToken(request);

        if (Objects.nonNull(jwtToken) && jwtUtil.validateToken(jwtToken)) {
            //토큰이 맞다면 등록해준다.
            Claims claims = jwtUtil.getClaims(jwtToken);

            Authentication authentication = getAuthentication(claims);
            final SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }

        //chain.doFilter하면 필터를 계속 타게 된다.
        chain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        final String jwtToken = request.getHeader(JwtConfig.HEADER);

        if (Objects.isNull(jwtToken) || !jwtToken.startsWith(JwtConfig.TOKEN_PREFIX)) {
            return null;
        }
        return jwtToken.replace(JwtConfig.TOKEN_PREFIX, "");
    }

    private Authentication getAuthentication(Claims claims) {
        //principal, credential, authorities 순이므로
        //Authenticaion.getPrincipal() 하면 Claims가 반환된다.

        final String l = claims.get("userId").toString();
        final long userId = Long.parseLong(l);
        final User user = userRepository.findById(userId)
            .orElseThrow(() -> new JwtException("없는 아이디입니다"));

        return new UsernamePasswordAuthenticationToken(user, null);
    }

}
