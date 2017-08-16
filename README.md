# PhoneBook
   Для запуска приложения требуеться указать путь к файлу properties в качестве аргументов JVM машине 

(-Dlardi.conf=/path/to/file.properties)

  Приложение может быть запущено в одном из двух вариантов для сохранения данных в базу данных или в файл csv.
# Для первого варианта нужно в файле properties нужно указать:
  -active profile = dataBase 
-настройки для базы данных 
# Пример такого файла 
   spring.jpa.hibernate.ddl-auto=update

   spring.datasource.url=jdbc:mysql://localhost:3306/phonebook

   spring.datasource.username=root

   spring.datasource.password=root

   spring.profiles.active=dataBase
   
# Для второго варианта нужно в файле properties нужно указать:
  -active profile = csvFile 

  -расположение и имя файлов в которые хотим сохранять данные
# Пример такого файла 

 spring.profiles.active=csvFile

 UserPath=F:/user.csv

 ContactPath=F:/contact.csv

# Для создания баз данных используйте этот sql код
```sql
create table contact (
 	id int auto_increment
	primary key,
	user_id int not null,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	patronymic varchar(15) not null,
	mobilephone varchar(15) not null,
	phone varchar(15) null,
	address varchar(50) null,
	email varchar(30) null
);

create table user (
	id int auto_increment
		primary key,
	first_name varchar(15) not null,
	last_name varchar(15) not null,
	login varchar(15) not null,
	password varchar(15) null,
	patronymic varchar(15) null
);
```
