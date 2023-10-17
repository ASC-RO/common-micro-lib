package micro.context;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Data
public class UserContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private Map<String, Object> tokenClaims;
    private UserInfo user;

    @Override
    public void setApplicationContext(@NonNull final ApplicationContext context) throws BeansException {
        setAppContext(context);
    }

    public static void setAppContext(final ApplicationContext context) {
        applicationContext = context;
    }

    public static UserContext get() {
        return (UserContext) applicationContext.getBean("userContext");
    }

    public static String getUserName() {
        return UserContext.get().username();
    }
    public static UUID getTenantId() {
        return UserContext.get().tenantId();
    }
    public UUID tenantId() {
        return this.getUser().getTenantId();
    }
    public String username() {
        return this.getUser().getUsername();
    }
    public void updateUserClaims(final Map<String, Object> tokenClaims) {
        this.tokenClaims = tokenClaims;
        final Map<String, String> userClaims = (Map<String, String>) tokenClaims.get("user");
        this.user = UserInfo.builder()
                .id(userClaims.get("id"))
                .email(userClaims.get("email"))
                .username(userClaims.get("username"))
                .name(userClaims.get("name"))
                .image(userClaims.get("image"))
                .role(userClaims.get("role"))
                .tenantId(UUID.fromString(userClaims.get("tenantId")))
                .customerId(Optional.of(userClaims.get("customerId")).map(UUID::fromString).orElse(null))
                .build();
        this.user.setCreatedAt(userClaims.get("createdAt"));
        this.user.setUpdatedAt(userClaims.get("updatedAt"));
    }

}

