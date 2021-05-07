package com.ghostapps.placapp.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.contrib.RecyclerViewActions
import com.ghostapps.placapp.R
import com.ghostapps.placapp.ui.gameRecords.adapter.RecordsListViewHolder
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFlowTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun mainFlowTest() {
        val homeTeamName = "Time A"
        val awayTeamName = "Time B"
        val homeTeamScore = (0 until 5).random()
        val awayTeamScore = (0 until 5).random()

        onView(withId(R.id.homeNewGameButton)).perform(click())

        onView(withId(R.id.preGameHomeTeamInput)).perform(replaceText(homeTeamName))
        onView(withId(R.id.preGameAwayTeamInput)).perform(
            replaceText(awayTeamName),
            closeSoftKeyboard()
        )

        onView(withId(R.id.preGameStartGameButton)).perform(click())

        // Increase home team score
        for (i in (0 until homeTeamScore)) {
            onView(withId(R.id.gameScoreHomeIncrease)).perform(click())
        }
        onView(withId(R.id.gameScoreHomeTeamName)).check(matches(withText(homeTeamName)))

        // Increase away team score
        for (i in (0 until awayTeamScore)) {
            onView(withId(R.id.gameScoreAwayIncrease)).perform(click())
        }
        onView(withId(R.id.gameScoreAwayTeamName)).check(matches(withText(awayTeamName)))

        onView(withId(R.id.gameScoreExitButton)).perform(click())

        Thread.sleep(500)

        onView(withId(R.id.homeRecordsButton)).perform(click())

        Thread.sleep(500)

        onView(withId(R.id.gameRecordsList))
            .check(
                matches(
                    RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                        R.id.itemGameRecordHomeTeamName,
                        0,
                        withText(homeTeamName)
                    )
                )
            )

        onView(withId(R.id.gameRecordsList))
            .check(
                matches(
                    RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                        R.id.itemGameRecordHomeTeamScore,
                        0,
                        withText(homeTeamScore.toString())
                    )
                )
            )

        onView(withId(R.id.gameRecordsList))
            .check(
                matches(
                    RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                        R.id.itemGameRecordAwayTeamScore,
                        0,
                        withText(awayTeamScore.toString())
                    )
                )
            )

        onView(withId(R.id.gameRecordsList))
            .check(
                matches(
                    RecyclerViewChildActions.childOfViewAtPositionWithMatcher(
                        R.id.itemGameRecordAwayTeamName,
                        0,
                        withText(awayTeamName)
                    )
                )
            )

        onView(withId(R.id.gameRecordsList))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecordsListViewHolder>(
                0,
                RecyclerViewChildActions.actionOnChild(click(), R.id.itemGameRecordDelete)
            ))

        onView(withId(android.R.id.button1)).perform(click())

        Thread.sleep(5000)
    }
}
