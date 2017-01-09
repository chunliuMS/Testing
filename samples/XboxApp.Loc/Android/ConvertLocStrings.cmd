@echo off
REM
REM Usage - just run ConvertLocStrings.cmd in the loc folder.  the xml string files in TFS will be
REM converted to Android string files and copied to the right locations in the values* directories.
REM
echo.

set GITBASEPATH=..\..\smartglass2\src\main\res
set TFSBASEPATH=..\

pushd %TFSBASEPATH%\
set CONVERTEDSTRINGDIR=%cd%\temp
set LOCTOOL=%cd%\package\localizationtool.exe
popd

if not exist %TFSBASEPATH% goto NoTFSPath
if not exist %LOCTOOL% goto NoLocTool

pushd %TFSBASEPATH%\
md %CONVERTEDSTRINGDIR% 2>nul

for /f "delims=" %%i IN ('dir /b /w *.xml') do call :ConvertString %%i
popd

pushd %GITBASEPATH%\values
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.Loc.xml strings_master.xml
popd

pushd %GITBASEPATH%\values-da-rDK
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.da-DK.loc.xml
popd

pushd %GITBASEPATH%\values-de
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.de-DE.Loc.xml
popd

pushd %GITBASEPATH%\values-en-rGB
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.en-GB.Loc.xml
popd

pushd %GITBASEPATH%\values-es
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.es-ES.Loc.xml
popd

pushd %GITBASEPATH%\values-es-rMX
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.es-MX.Loc.xml
popd

pushd %GITBASEPATH%\values-fi-rFI
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.fi-FI.loc.xml
popd

pushd %GITBASEPATH%\values-fr
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.fr-FR.Loc.xml
popd

pushd %GITBASEPATH%\values-it
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.it-IT.Loc.xml
popd

pushd %GITBASEPATH%\values-ja-rJP
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.ja-JP.loc.xml
popd

pushd %GITBASEPATH%\values-ko-rKR
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.ko-KR.loc.xml
popd

pushd %GITBASEPATH%\values-nb-rNO
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.nb-NO.loc.xml
popd

pushd %GITBASEPATH%\values-nl-rNL
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.nl-NL.loc.xml
popd

pushd %GITBASEPATH%\values-pl-rPL
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.pl-PL.loc.xml
popd

pushd %GITBASEPATH%\values-pt-rBR
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.pt-BR.Loc.xml
popd

pushd %GITBASEPATH%\values-pt-rPT
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.pt-PT.loc.xml
popd

pushd %GITBASEPATH%\values-ru-rRU
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.ru-RU.loc.xml
popd

pushd %GITBASEPATH%\values-sv-rSE
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.sv-SE.loc.xml
popd

pushd %GITBASEPATH%\values-tr-rTR
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.tr-TR.loc.xml
popd

pushd %GITBASEPATH%\values-zh-rCN
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.zh-CN.loc.xml
popd

pushd %GITBASEPATH%\values-zh-rTW
xcopy /y %CONVERTEDSTRINGDIR%\XboxApp.iOSAndroid.zh-TW.loc.xml
popd

del %CONVERTEDSTRINGDIR%\*.xml
rmdir %CONVERTEDSTRINGDIR%

goto :eof

:NoTFSPath
echo Your TFS path %TFSBASEPATH% does not exist. Make sure you point TFSBASEPATH to the right location in the script.
goto :eof
:end

:NoLocTool
echo %LOCTOOL% cannot be found. Make sure you define TFSBASEPATH in the script.
goto :eof
:end

:ConvertString
echo converting %1...
%LOCTOOL% -a %1 temp\%1
REM strip "x-invariant" from the converted file
perl -pi.org -e "s/\s*xml:lang=\"x-invariant\"//g;" temp\%1
del temp\%1.org
:end

echo.
