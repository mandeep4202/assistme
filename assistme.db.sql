BEGIN TRANSACTION;
DROP TABLE IF EXISTS "L_LINK_TB";
CREATE TABLE IF NOT EXISTS "L_LINK_TB" (
	"L_LINK_ID"	INTEGER NOT NULL,
	"L_NAME"	TEXT NOT NULL UNIQUE,
	"L_PATH"	TEXT NOT NULL,
	"L_TYPE"	TEXT,
	"L_FAVORITE"	INTEGER,
	PRIMARY KEY("L_LINK_ID" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "L_LOT_TB";
CREATE TABLE IF NOT EXISTS "L_LOT_TB" (
	"L_LOT_ID"	INTEGER NOT NULL UNIQUE,
	"L_LOT_NAME"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("L_LOT_ID" AUTOINCREMENT)
);
DROP TABLE IF EXISTS "LOT_LINK_JUNCTION";
CREATE TABLE IF NOT EXISTS "LOT_LINK_JUNCTION" (
	"L_LINK_ID"	INTEGER,
	"L_LOT_ID"	INTEGER,
	"L_JUN_ID"	INTEGER NOT NULL,
	FOREIGN KEY("L_LOT_ID") REFERENCES "L_LOT_TB"("L_LOT_ID"),
	FOREIGN KEY("L_LINK_ID") REFERENCES "L_LINK_TB"("L_LINK_ID"),
	PRIMARY KEY("L_JUN_ID" AUTOINCREMENT)
);
INSERT INTO "L_LINK_TB" ("L_LINK_ID","L_NAME","L_PATH","L_TYPE","L_FAVORITE") VALUES (1,'Design Image','C:\Users\Mandeep Singh\Desktop\design 1.jpg','APP',1),
 (2,'Azure Developer','C:\Users\Mandeep Singh\Desktop\Azure developer.docx','DOC',1),
 (3,'Assignment','C:\Users\Mandeep Singh\Desktop\Assign 12.docx','DOC',1),
 (5,'VLC Player','C:\Users\Public\Desktop\VLC media player.lnk','APP',1),
 (7,'Search Everything','C:\Users\Mandeep Singh\Desktop\Search Everything.lnk','APP',1),
 (8,'Notepad++','C:\Users\Public\Desktop\Notepad++.lnk','APP',1),
 (10,'Working','C:\Users\Mandeep Singh\Desktop\design 1.jpg','OTHER',1),
 (11,'Udeler','C:\Users\Mandeep Singh\Desktop\Udeler.lnk','APP',1),
 (12,'Beyond Comparator','C:\Users\Public\Desktop\Beyond Compare 3.lnk','APP',1),
 (13,'Chat Day One','C:\Users\Mandeep Singh\Desktop\Chat Day 1','DOC',1),
 (14,'MS Edge','C:\Users\Public\Desktop\Microsoft Edge.lnk','APP',0),
 (15,'DS Doc','C:\Users\Mandeep Singh\Desktop\Data Structure.docx - Shortcut.lnk','DOC',0),
 (16,'Malware Byte','C:\Users\Public\Desktop\Malwarebytes.lnk','OTHER',0),
 (17,'Few Test','C:\Users\Public\Desktop\Bitwarden.lnk','APP',0),
 (19,'SEARCH EVERYTHING','C:\Users\Mandeep Singh\Desktop\Search Everything.lnk','APP',0),
 (20,'INSTANT EYEDROPPER','C:\Users\Mandeep Singh\Desktop\Instant Eyedropper.lnk','APP',0),
 (23,'BITWARDEN','C:\Users\Public\Desktop\Bitwarden.lnk','APP',0),
 (26,'DESKTOP DATA 2','C:\Users\Mandeep Singh\Desktop\desktop data','DIR',0),
 (27,'ASSISTME WS','D:\Software\Repository\Java Fx\assistantWS\AssistMe','DIR',0),
 (28,'HELPFUL CONTENT','D:\Software\Repository\Java Fx\assistantWS\Helpful content','DIR',0),
 (29,'LENOVO DIAG','C:\Users\Public\Desktop\Lenovo Diagnostics Tool .lnk','APP',0),
 (30,'MICROSERVICES CODE OFF','D:\Software\Repository\Java Microservices Code OFF','DIR',0),
 (31,'VLC NEW METHOD','C:\Program Files\VideoLAN\VLC\vlc.exe','APP',0),
 (32,'STS','D:\Software\Java Related\spring-tool-suite-3.9.11.RELEASE-e4.14.0-win32-x86_64\sts-bundle\sts-3.9.11.RELEASE\STS.exe','APP',0),
 (33,'STS-3 FOLDER','D:\Software\Java Related\spring-tool-suite-3.9.11.RELEASE-e4.14.0-win32-x86_64\sts-bundle\sts-3.9.11.RELEASE','DIR',0),
 (34,'ASSISTME TEST','D:\Software\Repository\Java Fx\assistantWS\AssistMe','DIR',0),
 (35,'NIHARIKA','C:\Users\Mandeep Singh\Desktop\niharika.jpg','DOC',0),
 (36,'HANDBRAKE','C:\Program Files\HandBrake\HandBrake.exe','APP',0);
INSERT INTO "L_LOT_TB" ("L_LOT_ID","L_LOT_NAME") VALUES (5,'Test Name'),
 (6,'LOT 2'),
 (7,'LOT 3'),
 (8,'LOT 4'),
 (10,'AT HOME');
INSERT INTO "LOT_LINK_JUNCTION" ("L_LINK_ID","L_LOT_ID","L_JUN_ID") VALUES (2,5,5),
 (12,5,6),
 (3,6,8),
 (7,6,9),
 (12,6,10),
 (13,6,11),
 (15,6,12),
 (26,7,14),
 (23,7,15),
 (19,7,16),
 (7,8,17),
 (8,8,18),
 (13,8,786),
 (13,5,809),
 (14,5,810),
 (16,5,811),
 (28,8,812),
 (5,10,813),
 (11,10,814);
COMMIT;
