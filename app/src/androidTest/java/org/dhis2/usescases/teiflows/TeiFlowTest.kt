package org.dhis2.usescases.teiflows

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.dhis2.usescases.BaseTest
import org.dhis2.usescases.searchTrackEntity.SearchTEActivity
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
        teiFlowRobot {
            registerTei()
            completeEnrollment()
            enrollToProgramAgain()
        }

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