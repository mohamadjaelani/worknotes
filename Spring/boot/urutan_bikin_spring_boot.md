## FLows how to build an apps using spring boot

1. Accessing https://start.spring.io/ to create project starter
2. Add dependencies 
   * Spring Web
   * Spring Data JPA
   * PostgreSQL Driver (this course use this type of db)
3. Create class Model
    ```
    public class Student{
        private Long id;
        private String name;
        private String email;
        private LocalDate dob;
        private int age;
        
        public Long getId(){
            return id;
        }
        public String getName(){
            return name;
        }
        public String getEmail(){
            return email;
        }
        public int getAge(){
          return age;
        }
    }
    ```

