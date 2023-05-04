# UnityAndroidApplication
## Before you begin
Make sure you are running the Unity application in version **2019.4.32f1**
## To run
1. Clone repository
2. Open `Assets/Plugins/Android/AndroidManifest.xml`
3. Edit `android:name` to either `com.example.testkotlin.MyUnityPlayerActivity` or `com.example.testUnity`
depending on whether you want to use kotlin or java plugin
4. Go to Playersettings (Click on **File** > **Build Settings**), there will be a button left bottom and check that settings is corrosponding to screenshot
5. Make sure you have emulator or phone connected
6. CLick on build and run to run application

##To update package
1. Delete `Assets/Plugins/Android/libs/MyUnityPlayerActivity/app-debug.aar`
2. Copy new arr build from Android application into `Assets/Plugins/Android/libs/MyUnityPlayerActivity/` folder
