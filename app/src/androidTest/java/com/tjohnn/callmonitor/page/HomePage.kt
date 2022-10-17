package com.tjohnn.callmonitor.page

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.tjohnn.callmonitor.R

class HomePage {

    fun assertLoaded(): HomePage {
        onView(withId(R.id.server_information_title))
            .check(matches(isDisplayed()))
        return this
    }

    fun clickStartServer(): HomePage {
        onView(withId(R.id.start_server_button))
            .check(matches(isDisplayed()))
            .perform(click())
        return this
    }

    fun assertServerStarted(): HomePage {
        onView(withText(R.string.stop_server_label))
            .check(matches(isDisplayed()))
        return this
    }

    fun assertServerStopped(): HomePage {
        onView(withText(R.string.start_server_label))
            .check(matches(isDisplayed()))
        return this
    }

    fun navigateToCallLogs(): CallLogsPage {
        onView(withId(R.id.view_call_logs_button))
            .check(matches(isDisplayed()))
            .perform(click())
        return CallLogsPage()
    }
}
