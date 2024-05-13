package com.changer.basquiat.ui.login.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.UsuarioService
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.ui.echo.data.EchoService
import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.login.data.UsuarioToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel: ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.107:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UsuarioService::class.java)

    private val _apiResponse = MutableLiveData<UsuarioToken?>()
    val apiResponse: LiveData<UsuarioToken?> = _apiResponse

    fun testLogin(form: UserForm) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.login(form)
            val body = response.body()
            _apiResponse.postValue(body)
        }

        println(apiResponse.value.toString())
    }

    /*var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(login: UserForm) {
        try {
            viewModelScope.launch {
                val response = repository.getUser(login)
                state.value = LoginScreenState.Loading

                if (response.isSuccessful) {
                    state.value = response.body()?.let {
                        LoginScreenState.Success(
                            data = it
                        )
                    }
                } else {
                    throw Exception("Erro desconhecido")
                }
            }
        } catch (e: HttpException) {
            val message = when (e.code()) {
                400 -> "Campos em branco"
                404 -> "Email ou senha incorretos"
                else -> "Erro desconhecido"
            }

            state.value = LoginScreenState.Error(message)
        }
    }*/
}