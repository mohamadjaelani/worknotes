
-- SELECT SUBSTRING_INDEX('Mohamad- -Jaelani- -albogori- -programmer- -java- -delapan', '- -', 1) AS fname
-- execute siberger('jaelani')
-- create procedure siberger( IN parseData varchar(100))
-- drop procedure siberger
SET SQL_SAFE_UPDATES = 0;
select 'a' '''' 'string';
select concat("aing",'''',"maneh");
SELECT LENGTH("SQL Tutorial") AS LengthOfString;
SELECT POSITION("3" IN "W3Schoo3ls.com") AS MatchPosition;
select SUBSTRING("SQL Tutorial", 5, 3) AS ExtractString;
SELECT REPLACE("SQL Tutorial", "Tut", "HTML");
SELECT REPLACE("Kolom1<:>NilaiKolom1", "<;>", "");
SELECT SUBSTRING_INDEX('Kolom1<:>NilaiKolom1<;>Kolom2<:>NilaiKolom2<;>Kolom3<:>NilaiKolom3<;>Kolom4<:>NilaiKolom4', '<;>', 2) AS fname;
SELECT SUBSTRING_INDEX('<;>Kolom2<:>NilaiKolom2<;>Kolom3<:>NilaiKolom3<;>Kolom4<:>NilaiKolom4', '<;>', 1) AS fname;
call siberger('TEST001','Column1<:>NilaiKolom1<;>Column2<:>NilaiKolom2<;>Column3<:>NilaiKolom3<;>Column4<:>NilaiKolom4<;>Column5<:>NilaiKolom5');

-- drop procedure siberger;

DELIMITER $$
	create procedure siberger( IN namaTable varchar(100),IN parseData Text)
    BEGIN
    /************************************************************************
    ************************** Untuk Alinur Shihab***************************
    ************************************************************************/
        declare temp varchar(300);
        declare temp1 varchar(300);
        declare kolom varchar(300);
        declare nilai varchar(300);
        declare hasilnya Text(65535);
        declare delimiterValue varchar(3);
        declare delimiterColumn varchar(3);
        declare columnNames varchar(300);
        declare columnValues varchar(300);
        declare dbName varchar(100);
        set delimiterColumn = '<;>';
        set delimiterValue = '<:>';
        set columnNames = '';
        set columnValues = '';
        set hasilnya = '';
		bolak_balik:  LOOP
			set temp = SUBSTRING_INDEX(parseData,delimiterColumn, 1);
            set temp1 = CONCAT(temp,delimiterColumn);
            set parseData = REPLACE(parseData,temp1,"");
            set kolom = SUBSTRING_INDEX(temp,delimiterValue, 1);
            set nilai = SUBSTRING_INDEX(temp,delimiterValue, -1);
            if(temp=parseData) then
				set columnNames = CONCAT(columnNames,kolom);
                set columnValues =  (SELECT Concat(columnValues,'''',nilai,''''));
				LEAVE bolak_balik;
			else
				set columnValues =  (SELECT CONCAT(columnValues,'''',nilai,'''',','));
				set columnNames = CONCAT(columnNames,kolom,',');
			END IF;
		END LOOP;
		set @hasilnya = concat("INSERT INTO ",namaTable," (",columnNames,") values(", columnValues,");");
        prepare persiapan from @hasilnya;
        execute persiapan;
        deallocate prepare persiapan;
    END $$
DELIMITER ;
