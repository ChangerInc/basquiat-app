package com.changer.basquiat.common

import com.changer.basquiat.common.data.UserPreferences
import com.changer.basquiat.common.domain.ArquivoRepositoryImp
import com.changer.basquiat.common.domain.IArquivoRepository
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.common.domain.UsuarioRepositoryImp
import com.changer.basquiat.ui.historic.data.HistoricoViewModel
import com.changer.basquiat.ui.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IUsuarioRepository> { UsuarioRepositoryImp() }
    single<IArquivoRepository> { ArquivoRepositoryImp() }
    single { UserPreferences(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HistoricoViewModel(get(), get()) }
}
