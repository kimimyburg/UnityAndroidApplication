# UnityAndroidApplication
## Before you begin
Make sure you are running the Unity application in version **2019.4.32f1**
## To run
1. Clone repository
2. Open `Assets/Plugins/Android/AndroidManifest.xml`
3. Edit `android:name` to either `com.example.testkotlin.MyUnityPlayerActivity` or `com.example.testUnity`
depending on whether you want to use kotlin or java plugin
4. Go to Playersettings (Click on **File** > **Build Settings**), there will be a button left bottom and check that settings 
is corrosponding to screenshots:
Cancel changes
<img width="1440" alt="Screenshot 2023-05-04 at 09 27 49" src="https://user-images.githubusercontent.com/124045663/236138035-d874f04d-bc59-4dfe-ba35-f862d8c80ecc.png">

<img width="1084" alt="Screenshot 2023-05-04 at 09 28 37" src="https://user-images.githubusercontent.com/124045663/236138186-4b2987b0-ddb6-4fb0-90fc-5d9948bf7494.png">

5. Make sure you have emulator or phone connected
6. CLick on build and run to run application

##To update package
1. Delete `Assets/Plugins/Android/libs/MyUnityPlayerActivity/app-debug.aar`
2. Copy new arr build from Android application into `Assets/Plugins/Android/libs/MyUnityPlayerActivity/` folder
