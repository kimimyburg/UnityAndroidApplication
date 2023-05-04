package com.unity3d.player

import android.app.Activity


open class UnityPlayerActivity : Activity(), IUnityPlayerLifecycleEvents {
    protected var mUnityPlayer: UnityPlayer? = null

    // When Unity player unloaded move task to background
    override fun onUnityPlayerUnloaded() {}

    // When Unity player quited kill process
    override fun onUnityPlayerQuitted() {}
}