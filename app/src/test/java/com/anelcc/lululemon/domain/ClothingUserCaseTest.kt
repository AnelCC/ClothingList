package com.anelcc.lululemon.domain

import com.anelcc.lululemon.core.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ClothingUserCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var userCase: ClothingUserCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userCase = ClothingUserCase(repository)
    }

    @Test
    fun shouldCallDatabaseAndReturnEmptyList() = runBlocking {
        coEvery { repository.getAllFromDatabase() } returns emptyList()

        userCase.getClothingList()

        coVerify {
            repository.getAllFromDatabase()
        }
    }

    @Test
    fun shouldReturnListFromDatabase() = runBlocking {
        val databaseClothingList = listOf(Clothing("Jeans", "1234567890"), Clothing("Jeans", "1234567890"))
        coEvery { repository.getAllFromDatabase() } returns databaseClothingList

        val userCaseClothingList = userCase.getClothingList()

        coVerify(exactly = 1) { repository.getAllFromDatabase() }
        assert(userCaseClothingList.size == databaseClothingList.size)
    }

    @Test
    fun shouldInsertClothesInTheListOfDatabase() = runBlocking {
        val databaseClothingList = listOf(Clothing("Jeans", "1234567890"), Clothing("Coat", "1234567891"))
        coEvery { repository.getAllFromDatabase() } returns databaseClothingList

        var userCaseClothingList = userCase.getClothingList()

        coVerify(exactly = 1) { repository.getAllFromDatabase() }
        coVerify(exactly = 0) { repository.insertClothes(any()) }
        assert(userCaseClothingList.size == databaseClothingList.size)

        userCase.onInsertClothes(Clothing("Skirt", "1234567892"))
        coVerify(exactly = 1) { repository.insertClothes(any()) }
    }
}