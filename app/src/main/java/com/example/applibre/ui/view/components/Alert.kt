package com.example.applibre.ui.view.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applibre.R
import com.example.applibre.ui.theme.Shrikhand

@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val scroll = rememberScrollState(0)

    AlertDialog(onDismissRequest = { onDismissClick() },
        title = {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.gotham),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier.padding(start = 100.dp).size(60.dp)
                )
            }
            Text(text = title,
            style = TextStyle(fontFamily = Shrikhand, fontSize = 15.sp)) },

        text = {
            Text(
                text = message,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll),
                style = TextStyle(fontFamily = Shrikhand, fontSize = 15.sp)
            )
        },
        confirmButton = {
            Button(onClick = { onConfirmClick() }) {
                Text(text = confirmText,
                    style = TextStyle(fontFamily = Shrikhand, fontSize = 20.sp))
            }
        }
    )
}