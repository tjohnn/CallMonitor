package com.tjohnn.callmonitor

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.tjohnn.callmonitor.page.HomePage
import com.tjohnn.callmonitor.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigateToCallLogsTest {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun navigateToCallLogsTest() {
        HomePage()
            .assertLoaded()
            .navigateToCallLogs()
            .assertLoaded()
            .navigateBack()
            .assertLoaded()
    }
}
