.model small
.stack 100h
.data
arr db 2 dup('$')
str1 db  "Hello World","$"
.code
MOV AX,@data
MOV DS,AX
MOV DX, Offset str1
MOV AH,09
MOV AH,4CH
INT 21h
END
