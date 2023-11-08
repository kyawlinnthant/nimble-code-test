package com.kyawlinnthant.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.PrefModule
import com.kyawlinnthant.testrule.CoroutinesTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
@UninstallModules(PrefModule::class)
class PrefDataStoreTest {

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private var pref: PrefDataStoreImpl? = null

    @Inject
    lateinit var ds: DataStore<Preferences>

    @Before
    fun setup() {
        hiltRule.inject()
        pref = PrefDataStoreImpl(
            ds = ds,
            io = coroutinesRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        pref = null
    }

    @Test
    fun initial_data_are_empty_string() = runTest {
        val tokenType = pref!!.pullTokenType().first()
        val isAuthenticated = pref!!.pullIsAuthenticated().first()
        assertThat(tokenType).isEqualTo("")
        assertThat(isAuthenticated).isEqualTo(false)
    }

    @Test
    fun put_and_pull_correctly_work_for_token_type() = runTest {
        val expected = "Bearer"
        pref!!.putTokenType(expected)
        val actual = pref!!.pullTokenType().first()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun put_and_pull_correctly_work_for_is_authenticated() = runTest {
        val expected = false
        pref!!.putIsAuthenticated(expected)
        val actual = pref!!.pullIsAuthenticated().first()
        assertThat(actual).isEqualTo(expected)
    }
}
