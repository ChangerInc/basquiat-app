package com.changer.basquiat.common

import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.domain.UsuarioRepositoryImp
import com.changer.basquiat.common.domain.ArquivoRepositoryImp
import com.changer.basquiat.common.data.repository.IArquivoRepository
import com.changer.basquiat.common.data.repository.IConversionRepository
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.common.domain.ConversionRepositoryImp
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import com.changer.basquiat.presentation.viewmodel.LoginViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IUsuarioRepository> { UsuarioRepositoryImp() }
    single<IArquivoRepository> { ArquivoRepositoryImp() }
    single<IConversionRepository> { ConversionRepositoryImp() }
    single { AboutFile() }
    single { UserPreferences(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HistoricoViewModel(get(), get(), get()) }
    viewModel { ConversionViewModel(get(), get(), get())}
}
