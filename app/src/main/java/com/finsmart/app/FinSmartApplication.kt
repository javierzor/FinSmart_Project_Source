package com.finsmart.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.finsmart.app.util.PreferenceManager

class FinSmartApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Inicializar componentes de la aplicación
        initializeComponents()
        
        // Configurar tema según preferencias del usuario
        setupTheme()
    }
    
    private fun initializeComponents() {
        // Aquí se inicializarían componentes como Dagger/Hilt, Firebase, etc.
        // Por ahora es un placeholder para la estructura
    }
    
    private fun setupTheme() {
        // Configurar el tema (claro/oscuro) según las preferencias guardadas
        val preferenceManager = PreferenceManager(this)
        val themeMode = preferenceManager.getThemeMode()
        
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }
}
