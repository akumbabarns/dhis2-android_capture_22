package org.dhis2.usescases.event

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.dhis2.R
import org.dhis2.common.BaseRobot
import org.dhis2.common.matchers.hasCompletedPercentage
import org.dhis2.usescases.event.entity.EventDetailsUIModel
import org.hamcrest.Matchers.allOf

fun eventRegistrationRobot(eventRegistrationRobot: EventRegistrationRobot.() -> Unit) {
    EventRegistrationRobot().apply {
        eventRegistrationRobot()
    }
}

class EventRegistrationRobot : BaseRobot() {

    fun openMenuMoreOptions() {
        onView(withId(R.id.moreOptions)).perform(click())
    }

    fun clickOnDelete() {
        onView(withText(R.string.delete)).perform(click())
    }

    fun clickOnDetails() {
        onView(withText(R.string.event_overview)).perform(click())
    }

   fun checkEventDetails(eventDetails: EventDetailsUIModel) {
       onView(withId(R.id.programStageName)).check(matches(withText(eventDetails.programStage)))
       onView(withId(R.id.completion)).check(matches(hasCompletedPercentage(eventDetails.completedPercentage)))
       onView(withId(R.id.date_layout)).check(matches(hasDescendant(allOf(withId(R.id.date), withText(eventDetails.eventDate)))))
       onView(withId(R.id.org_unit_layout)).check(matches(hasDescendant(allOf(withId(R.id.org_unit), withText(eventDetails.orgUnit)))))
   }

    fun clickOnShare() {
        onView(withId(R.id.shareContainer)).perform(click())
    }

    private fun clickOnNextQR() {
        onView(withId(R.id.next)).perform(click())
    }

    fun clickOnAllQR(listQR: Int) {
        var qrLength = 1

        while (qrLength < listQR) {
            clickOnNextQR()
            qrLength++
        }
    }

    fun clickOnDeleteDialog() {
        onView(withId(R.id.possitive)).perform(click())
    }
}