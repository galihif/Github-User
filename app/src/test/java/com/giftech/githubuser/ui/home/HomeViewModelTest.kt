package com.giftech.githubuser.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.giftech.githubuser.data.MainRepository
import com.giftech.githubuser.data.User
import com.giftech.githubuser.utils.UsersData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer:Observer<List<User>>

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(mainRepository)
    }

    @Test
    fun getSearchedUser() {
        val dummyListUser = UsersData.listData

        val listUser = MutableLiveData<List<User>>()
        listUser.value = dummyListUser

        `when`(mainRepository.getSearchedUser("")).thenReturn(listUser)

        val listUserRes = homeViewModel.getSearchedUser("").value

        verify(mainRepository).getSearchedUser("")
        assertNotNull(listUserRes)
        assertEquals(dummyListUser.size, listUserRes?.size)

        homeViewModel.getSearchedUser("").observeForever(observer)
        verify(observer).onChanged(dummyListUser)
    }
}