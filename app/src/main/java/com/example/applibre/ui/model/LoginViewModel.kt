package com.example.applibre.ui.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel(){

    //private val auth: FirebaseAuth = com.google.firebase.ktx.Firebase.auth
    //private val firestore = com.google.firebase.ktx.Firebase.firestore

    var showAlert by mutableStateOf(false)
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var userName by mutableStateOf("")
        private set
    var selectedTab by mutableIntStateOf(0)
        private set


    /*fun login(onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            onSuccess()
                        }else{
                            Log.d("ERROR DE FB","ERROR")
                            showAlert = true
                        }
                    }
            }catch (e:Exception){
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }

        }
    }*/

    /**
     * Cierra el diálogo de alerta de error mostrada en la UI.
     */
    fun closeAlert(){
        showAlert = false
    }

    /**
     * Actualiza el email del usuario.
     *
     * @param email Nuevo email a establecer.
     */
    fun changeEmail(email: String) {
        this.email = email
    }

    /**
     * Actualiza la contraseña del usuario.
     *
     * @param password Nueva contraseña a establecer.
     */
    fun changePassword(password: String) {
        this.password = password
    }

    /**
     * Actualiza el nombre de usuario.
     *
     * @param userName Nuevo nombre de usuario a establecer.
     */
    fun changeUserName(userName: String) {
        this.userName = userName
    }

    /**
     * Cambia la pestaña seleccionada en la UI.
     *
     * @param selectedTab Índice de la nueva pestaña seleccionada.
     */
    fun changeSelectedTab(selectedTab: Int) {
        this.selectedTab = selectedTab
    }




}