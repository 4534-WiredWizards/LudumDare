#Build
echo 'Building...'
'/home/caninelizard/git/LudumDare/gradlew' assembleRelease --stacktrace
read -p "Press [Enter] key to sign..."
#Sign
echo 'Signing...'
jarsigner -verbose -storepass 'Chip0100' -sigalg SHA1withRSA -digestalg SHA1 -keystore '/home/caninelizard/git/LudumDare/android/key.keystore' '/home/caninelizard/git/LudumDare/android/build/outputs/apk/android-release-unsigned.apk' 'The Infinite Stone'
read -p "Press [Enter] key to Align..."
#Align
echo 'Aligning...'
cd '/home/caninelizard/Documents/android-sdk-linux/build-tools/23.0.1'
./zipalign -f -v 4 '/home/caninelizard/git/LudumDare/android/build/outputs/apk/android-release-unsigned.apk' '/home/caninelizard/git/LudumDare/android/build/outputs/apk/outfile.apk'
#echo 'Installing...'
