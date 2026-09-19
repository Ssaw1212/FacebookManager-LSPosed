package com.ssaw1212.facebookmanager

import android.app.Application
import android.content.Context
import android.util.Log
import io.github.libxposed.api.XposedInterface
import io.github.libxposed.api.XposedModule

class ModuleMain : XposedModule() {

    companion object {
        private const val TAG = "FacebookManager"
        private const val TARGET = "com.facebook.katana"
    }

    override fun onModuleLoaded(param: ModuleLoadedParam) {
        log(Log.INFO, TAG, "Module loaded: ${param.processName}")
    }

    override fun onPackageLoaded(param: PackageLoadedParam) {
        if (param.packageName != TARGET) return

        log(Log.INFO, TAG, "Facebook loaded")

        try {
            val method = Application::class.java.getDeclaredMethod(
                "attach",
                Context::class.java
            )

            hook(method)
                .setId("facebook_application_attach")
                .intercept(XposedInterface.Hooker { chain ->
                    val result = chain.proceed()
                    log(Log.INFO, TAG, "Facebook Application.attach observed")
                    result
                })

            log(Log.INFO, TAG, "Base hook installed")

        } catch (t: Throwable) {
            log(Log.ERROR, TAG, "Hook installation failed", t)
        }
    }
}
