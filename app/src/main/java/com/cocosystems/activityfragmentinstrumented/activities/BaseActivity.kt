package com.cocosystems.activityfragmentinstrumented.activities

import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    fun <T : View> findView(id: Int): T {
        return findViewById(id)
    }
}