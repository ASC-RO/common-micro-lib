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

## You only need to import user context config in the main app and all will be setup
```
@SpringBootApplication
@Import({ UserContextConfig.class })
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
```
