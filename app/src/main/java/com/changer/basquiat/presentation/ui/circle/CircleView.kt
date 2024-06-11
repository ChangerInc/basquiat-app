package com.changer.basquiat.presentation.ui.circle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.UserMember
import com.changer.basquiat.presentation.ui.components.LinearProgress
import com.changer.basquiat.presentation.ui.components.TopAppBarLoginCadastro
import com.changer.basquiat.presentation.ui.historic.Historic
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Cinza
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import java.math.BigDecimal
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleViewPreview() {
    CircleView(
        navigationToCircles = {},
        nome = "Teste",
        idCircle = UUID.randomUUID(),
        arquivos = listOf(),
        membros = listOf()

    )
}

@Composable
fun CircleView(
    navigationToCircles: () -> Unit,
//    vm: HistoricoViewModel,
    idCircle: UUID,
    nome: String,
    arquivos: List<Arquivo>,
    membros: List<UserMember>
) {
    var showDocuments by remember {
        mutableStateOf(true)
    }

    var showMembers by remember {
        mutableStateOf(false)
    }

    val colorDocuments = if (showDocuments) Azul else CinzaClaro
    val colorMembers = if (showMembers) Azul else CinzaClaro

    val lista = List(10) {
        Arquivo(
            idArquivo = UUID.randomUUID(),
            nome = "Nome do arquivo",
            criacao = "2021-10-10T00:00:00.000000",
            tamanho = BigDecimal(100),
            extensao = "pdf",
            urlArquivo = "https://www.google.com"
        )
    }

    val memberList = List(10) {
        UserMember(
            id =  UUID.randomUUID(),
            nome =  "Eu sou um membro",
            fotoPerfil = ""
        )
    }

    Scaffold(
        topBar = {
            TopAppBarLoginCadastro(
                titulo = nome,
                navigateToPage = { navigationToCircles() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(23.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                showDocuments = true
                                showMembers = false
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Arquivos",
                            color = colorDocuments,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(colorDocuments)
                                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                showMembers = true
                                showDocuments = false
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Membros",
                            color = colorMembers,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(colorMembers)
                                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.small)
                        )
                    }
                }

                AnimatedVisibility(visible = false) {
                    LinearProgress()
                }

                CircleHistoric(
                    items = lista,
                    downloadFile = { _, _ -> },
                    deleteFile = {}
                )
            }
        }
    }
}