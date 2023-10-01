# Common microservice library

## User context must be declared in a configuration file as RequestScope
```
@Configuration
public class SecurityConfig {
    @Bean
    @RequestScope
    public UserContext userContext() {
        return new UserContext();
    }
}
```
## The user context must be populated in a filter chain. For JWT authentication you can use the following snippet
```
@Component
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
```
