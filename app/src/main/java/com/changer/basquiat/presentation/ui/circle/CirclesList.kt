package com.changer.basquiat.presentation.ui.circle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.domain.model.UserMember
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import java.math.BigDecimal
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CirclesListPreview() {
    val staticHistoric = List(10) {
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

    val staticList = List(5) {
        Circulo(
            id = UUID.randomUUID(),
            nomeCirculo = "Changer",
            dono = UUID.randomUUID(),
            arquivos = staticHistoric,
            membros = memberList
        )
    }

    CirclesList(groups = staticList)
}

@Composable
fun CirclesList(
    groups: List<Circulo>?
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (groups!!.isNotEmpty()) {
            groups.forEach { circle ->
                item {
                    CircleItem(
                        nome = circle.nomeCirculo,
                        arquivos = circle.arquivos
                    )
                    if (groups.indexOf(circle) != groups.size - 1) {
                        Spacer(modifier = Modifier.height(5.dp))
                        HorizontalDivider(color = CinzaClaro)
                    }
                }
            }
        } else {
            item {
                Text(
                    text = "Você não possui nenhum círculo",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}