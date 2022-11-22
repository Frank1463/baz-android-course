package com.course.criptomonedas.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.course.criptomonedas.R
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @Test
    fun inputTextShouldBeRetainedAfterActivityRecreation() {

        launchFragmentInContainer<DetailFragment>()
        onView(withId(R.id.nameBook)).check(matches(withText("btc_mxn")))
    }

    @Test
    fun testseca() = {
        assertEquals("1", "1")
    }
}