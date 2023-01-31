package com.cocosystems.activityfragmentinstrumented

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cocosystems.activityfragmentinstrumented.activities.ProductActivity
import com.cocosystems.activityfragmentinstrumented.extensions.asMoney
import com.cocosystems.activityfragmentinstrumented.models.Product
import org.hamcrest.Matchers.not
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before

@RunWith(AndroidJUnit4::class)
class ProductActivityTest {

    private lateinit var scenario: ActivityScenario<ProductActivity>
    private val product = Product.getFakeData()

    @Before
    fun setUp() {
        val intent = Intent(
            InstrumentationRegistry
                .getInstrumentation().targetContext,
            ProductActivity::class.java
        )
        intent.putExtra("product", product)
        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun validateIncreaseQuantityAction() {
        // Check total button is not visible
        onView(withId(R.id.total))
            .check(matches(not(isDisplayed())))

        // Check decrease button is disabled
        onView(withId(R.id.decrease))
            .check(matches(isNotEnabled()))

        // Perform click to alter view state
        onView(withId(R.id.increase))
            .perform(click())

        // Both actions should be available now
        onView(withId(R.id.decrease))
            .check(matches(isEnabled()))
        onView(withId(R.id.total))
            .check(matches(isDisplayed()))
    }

    @Test
    fun validateDisplayedInfo() {
        // Check both properties are displayed correctly
        onView(withId(R.id.name))
            .check(matches(withText(product.name)))
        onView(withId(R.id.price))
            .check(matches(withText(product.price.asMoney())))
    }

    @Test
    fun validateActionsInitialState() {
        // Check decrease button is disabled
        onView(withId(R.id.decrease))
            .check(matches(isNotEnabled()))
        // Check total button is not visible
        onView(withId(R.id.total))
            .check(matches(not(isDisplayed())))
    }

//    @Test
//    fun validateDecreaseQuantityAction() {
//        // Perform increase action first
//        onView(withId(R.id.increase)).perform(click())
//        onView(withId(R.id.decrease)).perform(click())
//
//        // Check both actions are disabled again
//        onView(withId(R.id.decrease))
//            .check(matches(isNotEnabled()))
//        onView(withId(R.id.total))
//            .check(matches(not(isDisplayed())))
//    }
}