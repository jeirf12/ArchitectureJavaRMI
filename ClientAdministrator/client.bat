@echo off

javac -d bin -sourcepath src -cp bin;..\Common\Common.jar src\client\utilities\*
javac -d bin -sourcepath src -cp bin;..\Common\Common.jar src\client\controllers\*
javac -d bin -sourcepath src -cp bin;..\Common\Common.jar src\client\services\*

jar cvfm ClientAdmin.jar manifest.mf -C bin .

cls
java -jar ClientAdmin.jar
