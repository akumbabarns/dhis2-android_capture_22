package org.dhis2.usescases.teiflows

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.activity_search.view.form_recycler
import org.dhis2.R
import org.dhis2.common.viewactions.clickChildViewWithId
import org.dhis2.common.viewactions.typeChildViewWithId
import org.dhis2.usescases.BaseTest
import org.dhis2.usescases.searchTrackEntity.SearchTEActivity
import org.dhis2.usescases.searchTrackEntity.adapters.SearchTEViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeiFlowTest: BaseTest() {

    @get:Rule
    val ruleSearch = ActivityTestRule(SearchTEActivity::class.java, false, false)

    @Test
    fun shouldEnrollTeiOnSameProgram() {
        setupCredentials()

        prepareWomanProgrammeIntentAndLaunchActivity(ruleSearch)
        onView(withId(R.id.form_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(0, typeChildViewWithId("hola", R.id.input_editText)))
        onView(withId(R.id.form_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(1, typeChildViewWithId("mundo", R.id.input_editText)))
        onView(withId(R.id.form_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchTEViewHolder>(2, clickChildViewWithId(R.id.inputEditText)))
    }



    private fun prepareWomanProgrammeIntentAndLaunchActivity(ruleSearch: ActivityTestRule<SearchTEActivity>) {
        Intent().apply {
            putExtra(PROGRAM_UID, WOMAN_PROGRAM_UID_VALUE)
            putExtra(TE_TYPE, WOMAN_TE_TYPE_VALUE)
        }.also { ruleSearch.launchActivity(it) }
    }

    companion object {
        const val PROGRAM_UID = "PROGRAM_UID"
        const val TE_TYPE = "TRACKED_ENTITY_UID"
        const val WOMAN_PROGRAM_UID_VALUE = "uy2gU8kT1jF"
        const val WOMAN_TE_TYPE_VALUE = "nEenWmSyUEp"
    }

}