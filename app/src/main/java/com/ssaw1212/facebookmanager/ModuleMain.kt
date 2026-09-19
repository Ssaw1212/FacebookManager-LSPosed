package com.ssaw1212.facebookmanager

import android.util.Log
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.ModuleLoadedParam
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam

class ModuleMain : XposedModule() {

    companion object {
        private const val TAG = "FacebookManager"
        private const val TARGET = "com.facebook.katana"
    }

    override fun onModuleLoaded(param: ModuleLoadedParam) {
        log(Log.INFO, TAG, "Facebook Manager loaded")
    }

    override fun onPackageLoaded(param: PackageLoadedParam) {
        if (param.getPackageName() != TARGET) return

        log(Log.INFO, TAG, "Facebook detected")

        try {
            val process = Runtime.getRuntime().exec(
                arrayOf("su", "-c", "id")
            )

            val output = process.inputStream.bufferedReader().readText()
            val exitCode = process.waitFor()

            if (exitCode == 0) {
                log(Log.INFO, TAG, "ROOT granted: $output")
            } else {
                log(Log.WARN, TAG, "ROOT not granted")
            }

        } catch (e: Exception) {
            log(Log.ERROR, TAG, "ROOT request failed", e)
        }
    }
}
