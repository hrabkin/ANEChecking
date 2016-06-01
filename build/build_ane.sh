#!/bin/sh
# Created by Zestug 
# 05/2016

# Variables

ADT="/Applications/Adobe Flash Builder 4.7/sdks/4.15-air21/bin/adt"
OPTIONS="-package -target ane NativeFeatures.ane extension.xml -swc ASNativeBridge.swc -platform Android-ARM -C android . -platform default -C default ."
SWC_PATH="../ASNativeBridge/bin/ASNativeBridge.swc"
ANDROID_LIB_PATH="../AndroidNative/bin/AndroidNative.jar"

# Clear old

rm -rf android/
rm -rf ios/
rm -rf default/

mkdir android
mkdir ios
mkdir default

# Get Libraries

cp -R ../AndroidNative/libs android/
ls -l $ANDROID_LIB_PATH
cp $ANDROID_LIB_PATH android/

ls -l $SWC_PATH
cp $SWC_PATH ./

# Unzip flash lib

unzip ASNativeBridge.swc

# Prepare platforms data

cp catalog.xml android/
cp library.swf android/
cp library.swf ios/
mv library.swf default/

# Package

ls -l extension.xml
echo ${ADT} $OPTIONS
"${ADT}" ${OPTIONS}

rm -rf catalog.xml
rm -rf ASNativeBridge.swc