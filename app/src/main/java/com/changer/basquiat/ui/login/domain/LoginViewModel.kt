package com.changer.basquiat.ui.login.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.ui.login.data.UserForm
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (
    private val repository: IUsuarioRepository
): ViewModel() {
    /*private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.18.36.135:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(UsuarioService::class.java)

    private val _apiResponse = MutableLiveData<Response<UsuarioToken?>>()
    val apiResponse: LiveData<Response<UsuarioToken?>> = _apiResponse


    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(form: UserForm) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = service.login(form)
                _apiResponse.postValue(response)
            }
        } catch () {

        }
    }*/

    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(form: UserForm) {
        try {
            viewModelScope.launch {
                val response = repository.getUser(form)
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
    }
}