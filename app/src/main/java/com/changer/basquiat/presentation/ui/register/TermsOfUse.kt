package com.changer.basquiat.presentation.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Preto

@Composable
fun TermsOfUse(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
    ) {
        item {
            Text(
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Justify,
                color = Preto,
                text = "O presente Termo de Adequação à LGPD (Termo) tem como objeto garantir a adequação da " +
                        "empresa Changer à Lei Geral de Proteção de Dados (Lei 13.709/2018).\n\n" +
                        "2. Declarações e Compromissos\n\n" +
                        "2.1. A empresa declara que:\n" +
                        "É uma pessoa jurídica, de direito privado, que realiza o tratamento de dados pessoais em \n" +
                        "nome do(a) Controlador(a).\n" +
                        "Adotará todas as medidas necessárias para assegurar a observância à Lei Geral de \n" +
                        "Proteção de Dados.\n" +
                        "Atua no ramo de atividade de tecnologia, onde dados sensíveis/pessoais são coletados, \n" +
                        "tendo como objetivo o melhor relacionamento com os seus clientes e parceiros, sempre \n" +
                        "com respeito à privacidade dos dados recepcionados.\n\n" +
                        "2.2. A Empresa se compromete a:\n" +
                        "Manter a confidencialidade e a integridade de todos os dados pessoais mantidos ou \n" +
                        "consultados/transmitidos eletronicamente.\n" +
                        "Tratar os dados pessoais a que tiver acesso somente com as respectivas permissões dos \n" +
                        "titulares desses dados.\n" +
                        "Assegurar que todos os seus colaboradores, prepostos, sócios, diretores, representantes \n" +
                        "ou terceiros contratados que tenham acesso aos dados pessoais estejam cientes de suas \n" +
                        "obrigações e responsabilidades.\n" +
                        "Não revelar dados pessoais a terceiros, salvo nas hipóteses previstas na Lei Geral de \n" +
                        "Proteção de Dados.\n\n" +
                        "3. Medidas de Segurança\n" +
                        "A Empresa implementará as seguintes medidas de segurança para proteger os dados \n" +
                        "pessoais sob sua responsabilidade:\n" +
                        "Controle de acesso físico e lógico aos dados.\n" +
                        "Criptografia de dados.\n" +
                        "Treinamento de colaboradores sobre a Lei Geral de Proteção de Dados.\n" +
                        "Monitoramento e auditoria dos sistemas de tratamento de dados.\n\n" +
                        "4. Direitos dos Titulares dos Dados\n" +
                        "Os titulares dos dados pessoais têm os seguintes direitos:\n" +
                        "Saber quais dados pessoais são tratados pela Empresa.\n" +
                        "Acessar seus dados pessoais.\n" +
                        "Corrigir seus dados pessoais.\n" +
                        "Solicitar a exclusão de seus dados pessoais.\n" +
                        "Solicitar a portabilidade de seus dados pessoais.\n" +
                        "Opor-se ao tratamento de seus dados pessoais.\n\n" +
                        "5. Canais de Comunicação\n" +
                        "Os titulares dos dados pessoais podem exercer seus direitos por meio dos seguintes canais \n" +
                        "de comunicação:\n" +
                        "E-mail:\n" +
                        "Telefone:\n" +
                        "Endereço:\n" +
                        "6. Disposições Finais\n" +
                        "O presente Termo é válido por tempo indeterminado, podendo ser revogado ou modificado \n" +
                        "a qualquer tempo pela Empresa, mediante comunicação prévia aos titulares dos dados \n" +
                        "pessoais."
            )
        }
    }
}

@Preview
@Composable
private fun TermsOfUsePreview() {
    BasquiatTheme {
        TermsOfUse()
    }
}