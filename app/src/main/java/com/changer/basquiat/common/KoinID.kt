package com.changer.basquiat.common

import com.changer.basquiat.common.data.UserPreferences
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.common.domain.UsuarioRepositoryImp
import com.changer.basquiat.ui.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IUsuarioRepository> { UsuarioRepositoryImp() }
    single { UserPreferences(get()) }
    viewModel { LoginViewModel(get(), get()) }
}
