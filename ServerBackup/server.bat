@echo off

javac -d bin -sourcepath src -cp ..\Common\Common.jar src\server\utilities\*
javac -d bin -sourcepath src -cp ..\Common\Common.jar src\server\repository\*
javac -d bin -sourcepath src -cp ..\Common\Common.jar src\server\controllers\*
javac -d bin -sourcepath src -cp ..\Common\Common.jar src\server\services\*

jar cfvm ServerBackup.jar manifest.mf -C bin . 
md misCanciones

cls
java -jar ServerBackup.jar
