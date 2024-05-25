package com.changer.basquiat.ui.historic.data

import java.util.Date

data class HistoricItem (
    internal val criacao: Date,
    internal val extensao: String,
    internal val idArquivo: String,
    internal val nome: String,
    internal val tamanho: Long,
    internal val urlArquivo: String,
)