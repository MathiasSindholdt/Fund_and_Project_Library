@echo off

if not exist "%PROGRAMFILES%\Fund and Project library" mkdir "%PROGRAMFILES%\Fund and Project library"
xcopy /s /y /v "%cd%\InstallationFiles" "%PROGRAMFILES%\Fund and Project library\"
cd "%PROGRAMFILES%\Fund and Project library"
createLink.vbs "Fund and Project library" "%cd%\program.jar"
move "Fund and Project library" "%HOMEPATH%\Desktop\"
