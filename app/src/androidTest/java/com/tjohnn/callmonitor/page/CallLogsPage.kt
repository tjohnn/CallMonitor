package com.tjohnn.callmonitor.page

import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.tjohnn.callmonitor.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

class CallLogsPage {

    fun assertLoaded(): CallLogsPage {
        onView(withId(R.id.call_logs_recycler_view))
            .check(matches(isDisplayed()))
        return this
    }

    fun navigateBack(): HomePage {
        onView(
            allOf(
                instanceOf(AppCompatImageButton::class.java),
                withParent(instanceOf(Toolbar::class.java))
            )
        ).check(matches(isDisplayed()))
            .perform(click())
        return HomePage()
    }
}
