### 1. accessing start.spring.io
### 2. set defendencies
### 3. generate the project
### 4. open as maven project in intellij or in eclipse
### 5. create configuration class mostly for database configurations
    - add @Configuration
    - add @EnableJpaAuditing (for JDBC)
    - add @EnableRedisRepositories (for Redis)
### 6. Create Entity class for table creations
    @Data  // automatic getter and setter by Lombok
    @Entity // bikin table
    public class Account {
        @Id
        @GeneratedValue
        private Long id;
        @Column(nullable = false)
        private String email;
        @Column(nullable = false)
        private String password;
    }
### 7. Create Controller
    @Log4j2
    @RestController
    public class AccountController {

        private final AccountService accountService;
        public AccountController(AccountService accountService) {
            this.accountService = accountService;
        }
        @PostMapping("/check")
        public ResponseEntity<?> registerCheck(@RequestBody RegisterCheckDto registerCheckDto){
            log.debug("register{}",registerCheckDto);
            return accountService.registerCheck(registerCheckDto);
        }
    }
