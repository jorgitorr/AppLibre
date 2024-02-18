package com.example.applibre.ui.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.applibre.ui.model.LoginViewModel

@Composable
fun TabsView(navController: NavController, loginVM: LoginViewModel) {
    // DCS - Definición de las pestañas y la lógica para cambiar entre vistas de inicio de sesión y registro.

    val tabs = listOf("Iniciar Sesion", "Registrarse")

    Column {

        TabRow(selectedTabIndex = loginVM.selectedTab,
            contentColor = Color.Black,
            indicator = { tabPosition ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPosition[loginVM.selectedTab])
                )
            }
        )
        {
            tabs.forEachIndexed { index, title ->
                Tab(selected = loginVM.selectedTab == index,
                    onClick = { loginVM.changeSelectedTab(index) },
                    text = { Text(text = title) }
                )
            }
        }

        when(loginVM.selectedTab){
            //0 -> LoginView(navController, loginVM)
           // 1 -> RegisterView(navController, loginVM)
        }

    }

}