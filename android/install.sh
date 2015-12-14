echo 'Uninstalling org.wiredwozards.ludum.dare.android'
adb -d uninstall 'org.wiredwozards.ludum.dare.android'
echo 'Installing org.wiredwozards.ludum.dare.android'
adb -d install '/home/caninelizard/git/LudumDare/android/build/outputs/apk/outfile.apk'
