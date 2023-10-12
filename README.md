# Common microservice library

1. Provides common exception handler for REST calls
2. Utils for REST headers
3. Repository, Service, Criteria, Domain standard implementations
4. User context implementations

## Import the library
To import this library you need to declare this github repository as gradle repository
```
repositories {
    maven { url "https://github.com/ASC-RO/common-micro-lib/raw/maven" }
}

dependencies {
    implementation 'com.ascro:microframework:1.0.0'
}
```
Note: Adjust the version accordingly

## Option 1: Import user context config in the main app
```
@SpringBootApplication
@Import({ UserContextConfig.class })
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
```

### Option 2: Remove it from @SpringBootApplication and extend it in a config file
This is an example where auditorAware is overriden to allow auditor work without a request session.
```
@Configuration
public class ContextConfig extends UserContextConfig {
    @Bean
    @Lazy
    @Override
    public AuditorAware<String> auditorAware() {
        final String username = getUserContext()
                .map(UserContext::getUser)
                .map(UserInfo::getUsername)
                .orElse(SYSTEM_USER);
        return () -> Optional.of(username);
    }

    private Optional<UserContext> getUserContext() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return Optional.empty();
        }
        return Optional.of(userContext());
    }
}
```
