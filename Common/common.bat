@echo off

pwsh -command "iwr -uri https://repo1.maven.org/maven2/org/jaudiotagger/2.0.3/jaudiotagger-2.0.3.jar -OutFile jaudiotagger-2.0.3.jar" > NUL 2>&1 && javac -d bin -sourcepath src src\common\entities\* && javac -d bin -sourcepath src src\common\interfaces\* && javac -d bin -sourcepath src -cp jaudiotagger-2.0.3.jar src\common\utilities\* && jar cvfm Common.jar manifest.mf -C bin .
