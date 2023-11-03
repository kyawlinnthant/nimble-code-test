package com.kyawlinnthant.database.dao


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.database.db.SurveyDatabase
import com.kyawlinnthant.database.entity.SurveyEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SurveyDaoTest {
    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var dao: SurveyDao

    @Inject
    lateinit var db: SurveyDatabase

    @Before
    fun setup() {
        testRule.inject()
        dao = db.dao()
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun successfully_insert_individual_survey() = runTest {
        val dummy = SurveyEntity(
            id = "id1",
            name = "name1",
            description = "description1",
            image = "image1"
        )

        dao.insertSurvey(dummy)
        val actual = dao.readSurveys().first()
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual.first().id).isEqualTo(dummy.id)
        assertThat(actual.first().image).isEqualTo(dummy.image)
        assertThat(actual.first().name).isEqualTo(dummy.name)
        assertThat(actual.first().description).isEqualTo(dummy.description)
    }


    @Test
    fun successfully_insert_list_of_surveys() = runTest {
        val dummy = listOf(
            SurveyEntity(
                id = "id1",
                name = "name1",
                description = "description1",
                image = "image1"
            ),
            SurveyEntity(
                id = "id2",
                name = "name2",
                description = "description2",
                image = "image2"
            ),
            SurveyEntity(
                id = "id3",
                name = "name3",
                description = "description3",
                image = "image3"
            )
        )


        dao.insertSurveys(dummy)
        val actual = dao.readSurveys().first()
        assertThat(actual.size).isEqualTo(dummy.size)
    }

    @Test
    fun insert_again_with_same_primary_key_should_be_replaced() = runTest {
        //first time insertion
        val dummy1 = SurveyEntity(
            id = "id1",
            name = "name1",
            description = "description1",
            image = "image1"
        )
        dao.insertSurvey(dummy1)

        //second time insertion with same id
        val dummy2 = SurveyEntity(
            id = "id1",
            name = "name1",
            description = "description1",
            image = "image1"
        )
        dao.insertSurvey(dummy2)

        val actual = dao.readSurveys().first()
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual.first().id).isEqualTo(dummy2.id)
        assertThat(actual.first().image).isEqualTo(dummy2.image)
        assertThat(actual.first().name).isEqualTo(dummy2.name)
        assertThat(actual.first().description).isEqualTo(dummy2.description)
    }

    @Test
    fun no_data_in_db_should_return_empty() = runTest {
        val result = dao.readSurveys().first()
        assertThat(result).isEmpty()
    }

}