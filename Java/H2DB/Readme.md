### H2 In memory Database How to
#### 1. Run H2 Server with command below
      java -jar lib/h2-2.1.214.jar
   ![Gambar H2DB](https://github.com/mohamadjaelani/javanotes/blob/main/H2DB/h2dbimg.JPG) \
   if you run the H2 server for the first time, then just click connect leave password textbox blank. After login into H2 Web Console, then setup the ***sa*** user as follow ***ALTER USER sa SET PASSWORD 'your_password'***, this will be our username and password for the next login. for the url we use **jdbc:h2:~/test**
#### 2. Create Table
   use this SQL to create table and record as example
   
      CREATE TABLE Murid(id INT PRIMARY KEY AUTO_INCREMENT, Nama VARCHAR(255), Kelas INT);
      INSERT INTO Murid(Nama, Kelas) VALUES('Jajat', 1);
      INSERT INTO Murid(Nama, Kelas) VALUES('Lili', 2);
      INSERT INTO Murid(Nama, Kelas) VALUES('Herman', 3);
      INSERT INTO Murid(Nama, Kelas) VALUES('Yoga', 4);
      INSERT INTO Murid(Nama, Kelas) VALUES('Lukman', 3);
      INSERT INTO Murid(Nama, Kelas) VALUES('Ivan', 2);
      INSERT INTO Murid(Nama, Kelas) VALUES('Bule', 4);
      INSERT INTO Murid(Nama, Kelas) VALUES('Dado', 2);
   
   Execution sql command above \
   ![Gambar H2DB](https://github.com/mohamadjaelani/javanotes/blob/main/H2DB/h2dbimg1.JPG) \
   Query from the table \
   ![Gambar H2DB](https://github.com/mohamadjaelani/javanotes/blob/main/H2DB/h2dbimg2.JPG)
#### 3. Java Code
	public static void main(String...strings) throws SQLException {
		String url = "jdbc:h2:~/test";
		String user = "sa";
		String passwd = "test1234";
		Connection con = DriverManager.getConnection(url, user, passwd);
		Statement stm = con.createStatement();
		ResultSet res = stm.executeQuery("SELECT * FROM MURID ");
		while(res.next()) {
			System.out.println(res.getString("Nama"));
		}

        
	}
#### for customization we can use
```/dbnya/dbnya.db``` for custome db path. so in our code just like below \
```String url = "jdbc:h2:file:./dbnya/dbnya.db";``` \
db file will be at ```<project-base-path>/dbnya/dbnya.db``` \
the complete code is : \
```
	String url = "jdbc:h2:file:./dbnya/dbnya";
	String user = "sa";
	String passwd = "test1234";
	Connection con = DriverManager.getConnection(url, user, passwd);
	Statement stm = con.createStatement();
	stm.execute("CREATE TABLE Murid(id INT PRIMARY KEY AUTO_INCREMENT, Nama VARCHAR(255), Kelas INT);");
	stm.execute("INSERT INTO Murid(Nama, Kelas) VALUES('Jajat', 1);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Lili', 2);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Herman', 3);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Yoga', 4);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Lukman', 3);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Ivan', 2);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Bule', 4);\r\n" + 
			"  INSERT INTO Murid(Nama, Kelas) VALUES('Dado', 2);");
	stm.close();
```
it is create db file at the first time and create tables and its records. \
for query :
```
	String url = "jdbc:h2:file:./dbnya/dbnya";
	String user = "sa";
	String passwd = "test1234";
	Connection con = DriverManager.getConnection(url, user, passwd);
	Statement stm = con.createStatement();
	ResultSet res = stm.executeQuery("SELECT * FROM MURID ");
	while(res.next()) {
		System.out.println(res.getString("Nama"));
	}
```
#### IMPORTANT!!! 
	We can't run H2 databse service at the sametime
