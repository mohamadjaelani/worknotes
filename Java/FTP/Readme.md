## FTP Works
### Plain FTP 21
|no|Client|Server|
|--|------|------|
|1.|Start connecting to server|send greeting message ```220 Selamat Datang di FTP Server Uncal``` when accept a connection from client|
|2.|Accept greeting message with code 220 from server then send ```AUTH TLS```|accept ```AUTH TLS``` from client. since this is a Plain FTP, then send ```502 Explicit TLS authentication not allowed``` to client|
|3.|Accept message with ```520``` as prefix from server, then send ```AUTH SSL```|accept ```AUTH SSL``` from client. since this is a Plain FTP, then send ```502 Explicit SSL authentication not allowed``` to client|
|4.|Accept message with ```520``` as prefix from server, then send ```USER <username>```|accept ```USER <username>``` from client. keep the ```<username>``` for authetications, then send ```331 Password required for <username>``` requesting password for the username|
|5.|Accept message with ```331``` as prefix from server, then send ```PASS <********>```|accept ```PASS <********>``` from client. checking authentication with usernama and password|
|6.|if success login, server will send ```230 Logged on``` if failed ```530 Login or password incorrect!```||
