package com.course.criptomonedas

import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.course.criptomonedas.ui.fragment.DetailFragment
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    lateinit var detailFragment: DetailFragment

    @Before
    fun setUp() {
        val activity = Robolectric.buildActivity(FragmentActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        detailFragment = DetailFragment()
        activity.supportFragmentManager.beginTransaction().apply {
            add(detailFragment, "DetailFragment")
            commit()
        }
    }

    @Test
    fun validateTextViewContent() {
        val tvNameBook = detailFragment.activity?.findViewById(R.id.nameBook) as TextView
        assertNotNull("TextView could not be found", tvNameBook)
        assertTrue(
            "Text correct",
            "btc_mxn" == tvNameBook.text.toString()
        )
    }
}