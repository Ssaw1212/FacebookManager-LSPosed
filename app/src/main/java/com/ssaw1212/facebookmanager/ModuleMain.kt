package com.ssaw1212.facebookmanager

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.github.libxposed.api.XposedInterface
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.ModuleLoadedParam
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam

class ModuleMain : XposedModule() {

    companion object {
        private const val TAG = "FacebookManager"
        private const val TARGET = "com.facebook.katana"
    }

    override fun onModuleLoaded(param: ModuleLoadedParam) {
        log(
            Log.INFO,
            TAG,
            "Module loaded: ${param.getProcessName()}"
        )
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onPackageLoaded(param: PackageLoadedParam) {
        if (param.getPackageName() != TARGET) return

        log(
            Log.INFO,
            TAG,
            "Facebook loaded: ${param.getPackageName()}"
        )

        try {
            val attach = Application::class.java.getDeclaredMethod(
                "attach",
                Context::class.java
            )

            hook(attach)
                .setId("facebook_application_attach")
                .intercept(
                    XposedInterface.Hooker { chain ->
                        val result = chain.proceed()

                        log(
                            Log.INFO,
                            TAG,
                            "Facebook Application.attach observed"
                        )

                        result
                    }
                )

            log(Log.INFO, TAG, "Base hook installed")

        } catch (t: Throwable) {
            log(
                Log.ERROR,
                TAG,
                "Hook installation failed",
                t
            )
        }
    }
}
