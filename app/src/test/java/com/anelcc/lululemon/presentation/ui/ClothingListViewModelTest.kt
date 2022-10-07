package com.anelcc.lululemon.presentation.ui

import com.anelcc.lululemon.data.ClothingSortType
import com.anelcc.lululemon.domain.Clothing
import com.anelcc.lululemon.domain.ClothingUserCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ClothingListViewModelTest {
    @RelaxedMockK
    private lateinit var userCase: ClothingUserCase
    private lateinit var viewModel: ClothingListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ClothingListViewModel(userCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun shouldReturnEmptyClothingList() = runTest {
        viewModel.fetchData()
        viewModel.clothingList.value?.isEmpty()?.let { assert(it) }
    }

    @Test
    fun shouldReturnClothingList() = runTest {
        val job = launch {
            val list = listOf(
                Clothing("Jeans", "1234567890"),
                Clothing("Skirt", "1234567891"),
                Clothing("Coat", "1234567892"))
            coEvery { userCase.getClothingList() } returns list

            viewModel.fetchData()
            viewModel.clothingList.value
            assert(viewModel.clothingList.value?.size == list.size)
        }

        job.cancel()
    }

    @Ignore
    @Test
    fun shouldReturnAlphaSortList() = runTest {

        val list = listOf(
            Clothing("Jeans", "1234567890"),
            Clothing("Skirt", "1234567891"),
            Clothing("Coat", "1234567892")
        )
        val expectation1 = Clothing("Coat", "1234567892")
        val expectation2 = Clothing("Jeans", "1234567890")
        val expectation3 = Clothing("Skirt", "1234567891")
        coEvery { userCase.getClothingList() } returns list

        val job = launch {
            viewModel.fetchData()
            viewModel.sortByType(ClothingSortType.ALPHA)
        }

        assert(viewModel.clothingList.value?.get(0) == expectation1)
        assert(viewModel.clothingList.value?.get(1) == expectation2)
        assert(viewModel.clothingList.value?.get(2) == expectation3)

        job.cancel()
    }

    @Ignore
    @Test
    fun shouldReturnTimeSortList() = runTest {

        val list = listOf(
            Clothing("Jeans", "1234567890"),
            Clothing("Skirt", "1234567891"),
            Clothing("Coat", "1234567892")
        )
        val expectation1 = Clothing("Coat", "1234567892")
        val expectation2 = Clothing("Jeans", "1234567890")
        val expectation3 = Clothing("Skirt", "1234567891")
        coEvery { userCase.getClothingList() } returns list


        val job = launch {
            viewModel.fetchData()
            viewModel.sortByType(ClothingSortType.TIME)
        }

        assert(viewModel.clothingList.value?.get(0) == expectation1)
        assert(viewModel.clothingList.value?.get(1) == expectation2)
        assert(viewModel.clothingList.value?.get(2) == expectation3)
        job.cancel()
    }

}