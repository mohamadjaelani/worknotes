## FTP Works
### Plain FTP 21
|no|Client|Server|
|--|------|------|
|1.|Start connecting to server|send greeting message ```220 Selamat Datang di FTP Server Uncal``` when accept a connection from client|
|2.|Accept greeting message with code 220 from server then send ```AUTH TLS```|accept ```AUTH TLS``` from client. since this is a Plain FTP, the send ```502 Explicit TLS authentication not allowed``` to client|
