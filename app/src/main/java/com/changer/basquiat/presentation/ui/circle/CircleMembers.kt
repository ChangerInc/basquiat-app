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
import com.changer.basquiat.domain.model.UserMember
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleMembersPreview() {
    val memberList = List(10) {
        UserMember(
            id = UUID.randomUUID(),
            nome = "Eu sou um membro",
            fotoPerfil = ""
        )
    }

    CircleMembers(memberList)
}

@Composable
fun CircleMembers(
    members: List<UserMember>?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (members!!.isNotEmpty()) {
            members.forEach { member ->
                item {
                    MemberItem(
                        id = member.id,
                        nome = member.nome,
                        fotoPerfil = member.fotoPerfil
                    )

                    if (members.indexOf(member) != members.size - 1) {
                        Spacer(modifier = Modifier.height(5.dp))
                        HorizontalDivider(color = CinzaClaro)
                    }
                }
            }
        } else {
            item {
                Text(
                    text = "Nenhum membro adicionado",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}