@echo off
set pwd="%~dp0"
set sddir=%1

echo ================ begin conversion
pushd %pwd%\..
for /f "delims=" %%i IN ('dir /b /s *.xml') do call :convert "%%i"
popd
@echo on

:convert
if "%1"=="" goto :end
if "%1"=="%sddir%" goto :end
pushd package
del /q Localizable.strings >nul 2>nul
echo ================ converting %1
LocalizationTool.exe -i %1 Localizable.strings
set filename=%1
set locale=%filename:~-14%
set locale=%locale:~0,5%

if "%locale%"=="-ploc" popd & goto :end
if "%locale%"=="glass" set locale=en & goto :ready
if "%locale%"=="zh-CN" set locale=zh-Hans & goto :ready
if "%locale%"=="zh-TW" set locale=zh-Hant & goto :ready

REM the following locales have exra copy with just the region
if "%locale%"=="tr-TR" call :copy tr & goto :ready
if "%locale%"=="pt-PT" call :copy pt & goto :ready

REM except "es-MX", "pt-BR", and "en-GB" ("pt-PT" and "tr-TR" already handled above), the rest of the copies have just the region (first 2 characters)
if NOT "%locale%"=="es-MX" if NOT "%locale%"=="en-GB" if NOT "%locale%"=="pt-BR" set locale=%locale:~0,2%

:ready
call :copy %locale%

popd

goto end

:copy
pushd %sddir%

set source=%pwd%\Localizable.strings
set folder=%1.lproj
set dst=%folder%\Localizable.strings

echo ================ copying Localizable.strings to %dst%
copy %source% %dst%

popd

goto end

:end