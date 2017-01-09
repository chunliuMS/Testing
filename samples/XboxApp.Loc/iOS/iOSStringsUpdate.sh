#/!/bin/sh

closeprogram() 
{
    echo END CALLED
    exit
}

locconvert()
{
    if [ ! -d $OUTPUTDIR ]; then 
        echo $OUTPUTDIR does not exist, EXITING
	closeprogram
    fi 
    local LANGFOLDER=$OUTPUTDIR/$1.lproj
    if [ ! -d $LANGFOLDER ]; then
	echo $LANGFOLDER does not exist. EXITING
	closeprogram
    fi
 
    local OUTPUTFILE=$LANGFOLDER/Localizable.strings
    if ./iOS/LocalizationToolOSX $FILENAME $OUTPUTFILE; then 
       echo !!! WROTE $FILENAME to $1
    else
       echo <<< ERROR in LocalizationToolOSX
    fi
}

convert() 
{
    if [ $1 = "" ]; then 
	closeprogram
    elif [ $1 = $GITFILEDIR ]; then 
        closeprogram
    fi

    echo ================== converting $1
    local FILENAME="$1"
    local LOCALE=$(echo $FILENAME | cut -d '.' -f 3) 
    # expects to be in LOCFILEDIR
    local LANGUAGE=$(echo $LOCALE | cut -d '-' -f 1)
    
    pushd $GITFILEDIR
      local OUTPUTDIR=$PWD
    popd

    if [ "$LOCALE" = "qps-ploc" ]; then return
    elif [ $LOCALE = "Loc" ]; then LANGUAGE="en"
    elif [ $LOCALE = "en-GB" ]; then LANGUAGE="en-GB"
    elif [ $LOCALE = "es-MX" ]; then LANGUAGE="es-MX"
    elif [ $LOCALE = "zh-CN" ]; then LANGUAGE="zh-Hans"
    elif [ $LOCALE = "zh-TW" ]; then LANGUAGE="zh-Hant"
    elif [ $LOCALE = "pt-BR" ]; then LANGUAGE="pt-BR"
    elif [ $LOCALE = "tr-TR" ]; then locconvert tr-TR
    elif [ $LOCALE = "pt-PT" ]; then locconvert pt-PT
    fi 

    locconvert $LANGUAGE
}

LOCFILEDIR="../"
GITFILEDIR="../SmartGlass/SmartGlass/"

pushd $LOCFILEDIR
  for f in *.xml
  do 
    convert $f
  done
popd
