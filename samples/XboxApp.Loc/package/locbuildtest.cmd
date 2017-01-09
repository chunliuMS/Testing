@echo off
pushd ..
for /f "delims=" %%i IN ('dir /b /s *.xml') do call :buildtest "%%i"
popd
@echo on

:buildtest
if "%1"=="" goto end
pushd package
del /q android.txt >nul 2>nul
del /q Localizable.strings >nul 2>nul
del /q windows.txt >nul 2>nul
del /q windows >nul 2>nul
echo ================ testing %1
echo ==== iOS:
LocalizationTool.exe -i %1 Localizable.strings
echo ==== android:
LocalizationTool.exe -a %1 android.txt
echo ==== windows:
LocalizationTool.exe -w %1 windows.txt
popd

:end