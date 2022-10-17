package com.tjohnn.callmonitor

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import com.tjohnn.callmonitor.page.HomePage
import com.tjohnn.callmonitor.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class StartStopServerTest {

    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val ruleChain: RuleChain = RuleChain.outerRule(
        GrantPermissionRule.grant(
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS
        )
    ).around(activityTestRule)

    @Test
    fun startServerTest() {
        HomePage()
            .assertLoaded()
            .assertServerStopped()
            .clickStartServer()
            .assertServerStarted()
            .clickStartServer()
            .assertServerStopped()
    }
}
