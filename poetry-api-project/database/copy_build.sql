
-------------- D R O P  /  C R E A T E  T A B L E -----------------
DROP TABLE IF EXISTS poetry_foundation CASCADE;
CREATE TABLE poetry_foundation (
	id serial PRIMARY KEY,
	some_number int,
	title varchar,
	poem varchar,
	poet varchar,
	tags varchar
)
	-->Note: I had to edit the CSV to include some_number because the way they did the IDs was totally messed up and started over
	-->every, like, 180 poems. id is now a valid, unique primary key

--------C O P Y  B U I L D-------------------
-- 1) log in on psql CLI with : psql --host=<db-instance-endpoint> --port=5432 --username=<your-username> --password --dbname=<your-database-name>
-- 2) Run:
--psql-- \copy poetry_foundation(some_number, title, poem, poet, tags) FROM 'C:\Users\admin\heapingScoops\muse-machine\poetry-api-project\database\PoetryFoundationData_3.csv' WITH (FORMAT csv, HEADER true, DELIMITER ',', ENCODING 'WIN1252');


-- NOTE : Below will NOT work for rebuilding the poetry_foundation db on AWS because only the psql command line (ABOVE has access to both AWS and my local machine)
-- COPY poetry_foundation (some_number, title, poem, poet, tags) 
-- FROM 'C:\Users\Student\ian-moeckel-side-projects\poetry_foundation\database\PoetryFoundationData_2.csv'
-- 	--> note. If I want to re run build, I have to change the above thing and probably trouble shoot 
-- 	--> all the bullshit of building it again. I can't remember what i did :/
-- 	--> oh wait, I remember, it just had to be in pgAdmin 12
-- DELIMITER ','
-- CSV HEADER;
	-->Note: including the column names in the COPY directive allowed it to treat some_number as just some random-ass number

-- SELECT * FROM poetry_foundation WHERE id = 10000