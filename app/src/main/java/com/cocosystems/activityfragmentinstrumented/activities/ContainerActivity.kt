package com.cocosystems.activityfragmentinstrumented.activities

import android.os.Bundle
import com.cocosystems.activityfragmentinstrumented.R
import com.cocosystems.activityfragmentinstrumented.fragments.LoginFragment

class ContainerActivity : BaseActivity() {
    lateinit var fragment: LoginFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        fragment = LoginFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.containerView, fragment)
        transaction.commit()
    }
}