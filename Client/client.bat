@echo off

javac -d bin -sourcepath src -cp .\jaudiotagger-2.0.3.jar;..\Common\Common.jar src\client\utilities\*
javac -d bin -sourcepath src -cp .\jaudiotagger-2.0.3.jar;..\Common\Common.jar src\client\view\*
javac -d bin -sourcepath src -cp .\jaudiotagger-2.0.3.jar;..\Common\Common.jar src\client\services\*

jar cvfm Client.jar manifest.mf -C bin\ .

cls
java -jar Client.jar
