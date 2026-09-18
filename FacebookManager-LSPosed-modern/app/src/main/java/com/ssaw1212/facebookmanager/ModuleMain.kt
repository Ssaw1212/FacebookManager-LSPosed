package com.ssaw1212.facebookmanager

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.github.libxposed.api.XposedInterface
import io.github.libxposed.api.XposedModule

class ModuleMain : XposedModule() {
    companion object { private const val TAG = "FacebookManager"; private const val TARGET = "com.facebook.katana" }
    override fun onModuleLoaded(param: ModuleLoadedParam) {
        log(Log.INFO, TAG, "Modern module loaded: ${param.processName}")
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onPackageLoaded(param: PackageLoadedParam) {
        if (param.packageName != TARGET) return
        log(Log.INFO, TAG, "Facebook target loaded: ${param.packageName}")
        try {
            val attach = Application::class.java.getDeclaredMethod("attach", android.content.Context::class.java)
            hook(attach).setId("facebook_application_attach").intercept(XposedInterface.Hooker { chain ->
                val result = chain.proceed()
                log(Log.INFO, TAG, "Facebook Application.attach observed")
                result
            })
            log(Log.INFO, TAG, "Modern base hook installed")
        } catch (t: Throwable) {
            log(Log.ERROR, TAG, "Base hook installation failed", t)
        }
    }
}
