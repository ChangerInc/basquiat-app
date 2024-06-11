package com.changer.basquiat.common

import com.changer.basquiat.authentication.FirebaseAuthRepository
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
import com.changer.basquiat.presentation.viewmodel.RegisterViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single<IUsuarioRepository> { UsuarioRepositoryImp() }
    single<IArquivoRepository> { ArquivoRepositoryImp() }
    single<IConversionRepository> { ConversionRepositoryImp() }
    single { AboutFile() }
    single { UserPreferences(get()) }
    singleOf(::FirebaseAuthRepository)
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { HistoricoViewModel(get(), get(), get(), get(), get()) }
    viewModel { ConversionViewModel(get(), get(), get(), get(), get())}
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}
