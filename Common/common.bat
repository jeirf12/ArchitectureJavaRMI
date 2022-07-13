@echo off

javac -d bin -sourcepath src src\common\entities\*
javac -d bin -sourcepath src src\common\interfaces\*
javac -d bin -sourcepath src -cp jaudiotagger-2.0.2.jar src\common\utilities\*

jar cvfm Common.jar manifest.mf -C bin .
