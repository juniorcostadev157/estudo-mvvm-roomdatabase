package com.junior.formularioroomdatabase.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.junior.formularioroomdatabase.ui.theme.FormularioRoomDataBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormularioRoomDataBaseTheme {

                Navigation().Create()

                }
            }
        }
    }


