package micro.config;

import micro.context.UserContext;
import micro.context.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Configuration
@Profile("production")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class UserContextConfig {
    public static final String SYSTEM_USER = "SYSTEM";

    @Bean
    @RequestScope
    public AuditorAware<String> auditorAware() {
        final String username = Optional.ofNullable(userContext().getUser())
                .map(UserInfo::getUsername)
                .orElse(SYSTEM_USER);
        return () -> Optional.of(username);
    }

    @Bean
    @RequestScope
    public UserContext userContext() {
        return new UserContext();
    }

    @Bean
    public UserInfoFilter userInfoFilter() {
        return new UserInfoFilter(userContext());
    }

}
