package com.cocosystems.activityfragmentinstrumented.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.cocosystems.activityfragmentinstrumented.R
import com.cocosystems.activityfragmentinstrumented.extensions.isEmail
import com.cocosystems.activityfragmentinstrumented.extensions.validPassword
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {
    lateinit var email: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var emailError: TextView
    lateinit var passwordError: TextView
    lateinit var access: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false).also {
            email = it.findViewById(R.id.email)
            password = it.findViewById(R.id.password)
            emailError = it.findViewById(R.id.emailError)
            passwordError = it.findViewById(R.id.passwordError)
            access = it.findViewById(R.id.access)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupValidations()
        validateFirstState()
    }

    private fun validateFirstState() {
        validateEmailError()
        validatePasswordError()
        validateAccessAvailability()
    }

    private fun setupValidations() {
        email.doOnTextChanged { _, _, _, _ ->
            validateEmailError()
            validateAccessAvailability()
        }

        password.doOnTextChanged { _, _, _, _ ->
            validatePasswordError()
            validateAccessAvailability()
        }
    }

    private fun validateEmailError(){
        val text = email.text.toString()
        emailError.visibility = if (text.isEmail() || text.isEmpty())
            View.GONE
        else
            View.VISIBLE
    }

    private fun validatePasswordError() {
        val text = password.text.toString()
        passwordError.visibility = if (text.validPassword() || text.isEmpty())
            View.GONE
        else
            View.VISIBLE
    }

    private fun validateAccessAvailability() {
        val emailText = email.text.toString()
        val passwordText = password.text.toString()
        val shouldProceed = emailText.isEmail() && passwordText.validPassword()
        access.isEnabled = shouldProceed
    }
}