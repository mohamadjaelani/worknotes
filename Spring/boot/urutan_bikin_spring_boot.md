## FLows how to build an apps using spring boot

### 1. Accessing https://start.spring.io/ to create project starter
### 2. Add dependencies 
   * Spring Web
   * Spring Data JPA (if use database)
   * PostgreSQL Driver (this course use this type of db)
### 3. Create class Model
	
	public class Student{
		private Long id;
		private String name;
		private String email;
		private LocalDate dob;
		private int age;
		
		public Student() {
		
		}
		
		public Student(Long id, String name, String email, LocalDate dob, int age) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.dob = dob;
			this.age = age;
		}

		public Student(Long id) {
			this.id = id;
		}

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
		public void setAge(int age){
			this.age = age;
		}
		public void setName(String name){
			this.name = name;
		}
		public void setId(int id){
			this.id = id;
		}
	}
	
### 4. Create Controller
	
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
	import java.time.LocalDate;
	import java.time.Month;
	import java.util.List;

	@RestController
	@RequestMapping(path = "api/v1/student")
	public class StudentController {
	
		private final StudentService studentService;
		
		@Autowired  // injected @Service into this class
		public StudentController(StudentService studentService) {
			this.studentService = studentService;
		}
		@GetMapping
		public List<Student> getStudents(){
			return List.of(new Student(
				1L,
				"Jaelani",
				"j43lani@gmail.com",
				LocalDate.of(1990, Month.APRIL,15),
				30
				));
		}
	}
	
### 5. Create Service
	
	import org.springframework.stereotype.Component;
	import java.time.LocalDate;
	import java.time.Month;
	import java.util.List;

	@Service  // this @Service notation will be known at @ autowired in cotroller
	public class StudentService {
		public List<Student> getStudents(){
			return List.of(new Student(
				1L,
				"Jaelani",
				"j43lani@gmail.com",
				LocalDate.of(1990, Month.APRIL,15),
				30
			));
		}
	}
	
### 5. Create File Properties (for db connection)
	
