package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.net.InetAddress
import java.net.Socket

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(
                        onClick = {
                            enviar()
                        }
                    ) {
                        Text("Enviar mensaje")
                    }
                }
            }
        }
    }

    private fun enviar() {
        Thread {
            val host = "192.168.1.55"
            val puerto = 6000
            val mensaje = "Adios"

            try {
                val cliente = Socket(host, puerto)
                val outputStream = cliente.getOutputStream()
                outputStream.write(mensaje.toByteArray())
                outputStream.flush()

                cliente.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}