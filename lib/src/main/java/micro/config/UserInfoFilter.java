package micro.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import micro.context.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UserInfoFilter extends OncePerRequestFilter {
    private final UserContext userContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).ifPresent(authentication -> {
            final boolean isJwtAuth = authentication instanceof JwtAuthenticationToken;
            if (!isJwtAuth) {
                return;
            }
            try {
                final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
                final Map<String, Object> userClaims = jwtAuthenticationToken.getToken().getClaims();
                userContext.updateUserClaims(userClaims);
            } catch (Exception e) {
                this.logger.error("Failed to update user from jwt claims", e.getCause());
            }
        });

        filterChain.doFilter(request, response);
    }
}
