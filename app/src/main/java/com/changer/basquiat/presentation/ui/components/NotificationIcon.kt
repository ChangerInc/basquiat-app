package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.presentation.ui.theme.Branco

@Composable
fun NotificationIcon(notification: Int) {
    if (notification == 0) {
        IconButton(onClick = { }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notificações",
                tint = Branco
            )
        }
    } else {
        BadgedBox(
            badge = {
                Badge(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(25.dp)
                ) {
                    val badgeNumber = notification.toString()
                    Text(
                        text = badgeNumber,
                        maxLines = 1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(25.dp)
                            .semantics {
                                contentDescription = "$badgeNumber new notifications"
                            }
                    )
                }
            }) {
            IconButton(onClick = { }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notificações",
                    tint = Branco
                )
            }
        }
    }
}