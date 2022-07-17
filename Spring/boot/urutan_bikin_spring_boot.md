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
	
### 6. Configure Application.properties file (for db connection)
	//located at resource package
	spring.datasource.url=jdbc:postgresql://localhost:5432/student
	spring.datasource.username=latihan
	spring.datasource.password=Asdf0987
	spring.jpa.hibernate.ddl-auto=create-drop
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
	spring.jpa.properties.hibernate.format_sql=true
	server.error.include-message=always
	
### 7. Back to step #3 (Model)
	add notation and some confugration to make the model as a table
	```
	@Entity // untuk hibernate
	@Table
	public class Student{
		@Id
		@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
		)
		@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="student_sequence"
		)
		private Long id;
		private String name;
		.....
	```
### 8. Create an Interface class for Database Repository
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

	import java.util.Optional;

	@Repository
	public interface StudentRepository extends JpaRepository<Student,Long> {
	    @Query("select s from Student s where s.email=?1")
	    Optional<Student> findStudentByEmail(String email);
	}

### 9. Create Configuration (StudentConfig.class)
	// add initial data record
	import org.springframework.boot.CommandLineRunner;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import java.time.LocalDate;
	import java.time.Month;
	import java.util.List;

	@Configuration
	public class StudentConfig {
		@Bean
		CommandLineRunner commandLineRunner(StudentRepository studentRepository){
			return args ->{
				Student jaelani = new Student(
					"Jaelani",
					"Jaelani@gmail.com",
					LocalDate.of(2001, Month.APRIL,21)
				);
				Student jojot = new Student(
					"Jojot",
					"Jojot@gmail.com",
					LocalDate.of(2005, Month.JANUARY,14)
				);
				studentRepository.saveAll(
					List.of(jaelani,jojot)
				);
			};
		}
	}
	
### 10. Back to step #5 (Creating Service)
	modify the StudentService class in order to have some database service
	.....
	@Service  // this @Service notation will be known at @ autowired in cotroller
	public class StudentService {
		private final StudentRepository studentRepository;

		@Autowired
		public StudentService(StudentRepository studentRepository) {
			this.studentRepository = studentRepository;
		}
		public List<Student> getStudents(){
			return studentRepository.findAll();
		}

		public void addNewStudent(Student student) {
			Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
			if(studentByEmail.isPresent())
				throw new IllegalStateException("email sudah ada");
			studentRepository.save(student);
		}

		public void deleteStudent(Long id) {
			boolean b = studentRepository.existsById(id);
			if(!b)
				throw new IllegalStateException("Student with "+id+" not found");
			studentRepository.deleteById(id);
		}
		
		@Transactional
		public void updateStudent(Long id, String name, String email) {
			Student student = studentRepository.findById(id).orElseThrow(()->new IllegalStateException("Student id "+id+" not found"));
			if(name != null && name.length()>0 && !Objects.equals(student.getName(), name))
				student.setName(name);
			if(email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
				Optional optional = studentRepository.findStudentByEmail(email);
				if(optional.isPresent())
					throw new IllegalStateException("Email sudah ada yang pake");
				student.setEmail(email);
			}
		}
	......
