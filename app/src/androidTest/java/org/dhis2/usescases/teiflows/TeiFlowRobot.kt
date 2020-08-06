package org.dhis2.usescases.teiflows

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.dhis2.R
import org.dhis2.common.BaseRobot
import org.dhis2.common.viewactions.clickChildViewWithId
import org.dhis2.common.viewactions.typeChildViewWithId
import org.dhis2.usescases.searchTrackEntity.adapters.SearchTEViewHolder
import org.dhis2.usescases.teiDashboard.dashboardfragments.teidata.DashboardProgramViewHolder
import org.dhis2.usescases.teidashboard.robot.EnrollmentRobot
import org.dhis2.usescases.teidashboard.robot.enrollmentRobot
import org.dhis2.usescases.teidashboard.robot.teiDashboardRobot
import org.hamcrest.CoreMatchers.allOf

fun teiFlowRobot(teiFlowRobot: TeiFlowRobot.() -> Unit) {
    TeiFlowRobot().apply {
        teiFlowRobot()
    }
}

class TeiFlowRobot : BaseRobot() {


    fun registerTei() {
        //move to searchrobot
        Espresso.onView(ViewMatchers.withId(R.id.form_recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(0, typeChildViewWithId("hola", R.id.input_editText)))
        Espresso.onView(ViewMatchers.withId(R.id.form_recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(1, typeChildViewWithId("mundo", R.id.input_editText)))
        Espresso.onView(ViewMatchers.withId(R.id.form_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(2, clickChildViewWithId(
                R.id.inputEditText)
            ))

        Espresso.onView(ViewMatchers.withId(R.id.widget_datepicker))
            .perform(PickerActions.setDate(2020, 5, 4))
        Espresso.onView(ViewMatchers.withId(R.id.acceptButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.enrollmentButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.enrollmentButton)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.widget_datepicker))
            .perform(PickerActions.setDate(2020, 5, 4))
        Espresso.onView(ViewMatchers.withId(R.id.acceptButton)).perform(ViewActions.click())

        enrollmentRobot { clickOnSaveEnrollment() }
    }

    fun completeEnrollment() {
        teiDashboardRobot {
            clickOnMenuMoreOptions()
            clickOnTimelineEvents()
            clickOnMenuMoreOptions()
            clickOnMenuComplete()
            checkCanNotAddEvent()
            checkAllEventsAreClosed(3)
        }
    }

    fun enrollToProgramAgain() {
        teiDashboardRobot {
            clickOnMenuMoreOptions()
            clickOnMenuProgramEnrollments()
        }

        /*onView(withId(R.id.recycler)).perform(RecyclerViewActions.scrollTo<DashboardProgramViewHolder>(
            allOf(
                hasDescendant(withText("MNCH / PNC (Adult Woman)")),
                hasDescendant(withText("ENROLL")),
                hasDescendant(allOf(withId(R.id.action_button), isDisplayed()))
            ))
        )*/
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItem<DashboardProgramViewHolder>(
            allOf(
                hasDescendant(withText("MNCH / PNC (Adult Woman)")),
                hasDescendant(withText("ENROLL"))
            ),
            clickChildViewWithId(R.id.action_button)
        ))
    }

}