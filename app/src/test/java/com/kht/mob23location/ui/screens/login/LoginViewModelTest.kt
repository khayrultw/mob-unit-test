package com.kht.mob23location.ui.screens.login


import com.kht.mob23location.data.repo.IUserRepo
import com.kht.mob23location.data.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    val repo: IUserRepo = mock()
    val viewModel = LoginViewModel(repo)

    @Before
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        whenever(repo.getUser()).thenReturn("khayrul")
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchUser returns the mock value`() {
        assertEquals("khayrul", viewModel.fetchUser())
    }

    @Test
    fun `greetings should return hello mocked_value`() {
        assertEquals("Hello khayrul", viewModel.greetings())
    }

    @Test
    fun `fetchUser should update the greetings stateflow with greetings`() = runTest {
        viewModel.fetchUser()
        val greetings = viewModel.greetings.first()
        assertEquals("Hello khayrul", greetings)
    }

    @Test
    fun `greet function should update the greetings stateflow with Hello $name`() = runTest {
        viewModel.greet("Khayrul")
        val msg = viewModel.greetings.drop(1).first()
        assertEquals("Hello Khayrul", msg, "Extra info about test")
    }

    @Test
    fun `Validation should fail for email and password`() {
       assert(viewModel.validate("email", "password") != null)
    }

    @Test
    fun `Validation should fail for email@a,com and pass`() {
        assert(viewModel.validate("email@a.com", "pass") != null)
    }

    @Test
    fun `Validation should pass for email@a,com and password`() {
        assert(viewModel.validate("email@a.com", "password") == null)
    }

    @Test
    fun `Validation should pass for email@gmail,com and password`() {
        assert(viewModel.validate("email@gmail.com", "password") != null)
    }
}