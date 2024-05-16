package com.changer.basquiat.common

import com.changer.basquiat.common.data.UsuarioService
import com.changer.basquiat.common.domain.ApiConfig
import com.changer.basquiat.ui.login.domain.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UsuarioService> {
        ApiConfig
            .getIntance()
            .create(UsuarioService::class.java)
    }

    viewModel {
        LoginViewModel(get())
    }
}
