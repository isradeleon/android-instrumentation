package com.cocosystems.activityfragmentinstrumented

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cocosystems.activityfragmentinstrumented.fragments.LoginFragment
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    lateinit var fragmentScenario: FragmentScenario<LoginFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer(
            themeResId = R.style.Theme_InstrumentedTests
        )
    }

    @After
    fun tearDown() { fragmentScenario.close() }

    @Test
    fun validateInitialErrorsVisibility() {
        onView(withId(R.id.emailError))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.passwordError))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun validateInitialAccessButtonState() {
        onView(withId(R.id.access))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun validateLoginBehavior(){
        validateInitialAccessButtonState()
        validateEmailErrorBehavior()
        validatePasswordErrorBehavior()

        onView(withId(R.id.access)).check(matches(isEnabled()))
    }

    @Test
    fun validateEmailErrorBehavior() {
        // Write invalid email
        onView(withId(R.id.email))
            .perform(typeText("1234abcd"))

        // Check email error appears
        onView(withId(R.id.emailError))
            .check(matches(isDisplayed()))

        // Write a valid email
        onView(withId(R.id.email)).perform(clearText())
        onView(withId(R.id.email))
            .perform(typeText("valid@email.com"))

        // Validate email error is gone
        onView(withId(R.id.emailError))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun validatePasswordErrorBehavior() {
        // Write invalid email
        onView(withId(R.id.password))
            .perform(typeText("123"))

        // Check email error appears
        onView(withId(R.id.passwordError))
            .check(matches(isDisplayed()))

        // Write a valid email
        onView(withId(R.id.password)).perform(clearText())
        onView(withId(R.id.password))
            .perform(typeText("12345678"))

        // Validate email error is gone
        onView(withId(R.id.passwordError))
            .check(matches(not(isDisplayed())))
    }
}