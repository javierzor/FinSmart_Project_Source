package com.finsmart.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.finsmart.app.R
import com.finsmart.app.ui.onboarding.OnboardingActivity
import com.finsmart.app.ui.auth.AuthActivity
import com.finsmart.app.ui.main.MainActivity
import com.finsmart.app.util.PreferenceManager

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 2000L // 2 segundos
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        preferenceManager = PreferenceManager(this)
        
        // Después del tiempo de espera, navegar a la siguiente pantalla
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToNextScreen()
        }, SPLASH_DELAY)
    }
    
    private fun navigateToNextScreen() {
        // Determinar a qué pantalla navegar según el estado de la aplicación
        val isFirstLaunch = preferenceManager.isFirstLaunch()
        val isUserLoggedIn = preferenceManager.getUserId() != null
        
        val intent = when {
            isFirstLaunch -> {
                // Primera vez que se abre la app, mostrar onboarding
                Intent(this, OnboardingActivity::class.java)
            }
            !isUserLoggedIn -> {
                // Usuario no ha iniciado sesión, mostrar pantalla de autenticación
                Intent(this, AuthActivity::class.java)
            }
            else -> {
                // Usuario ya ha iniciado sesión, ir directamente a la pantalla principal
                Intent(this, MainActivity::class.java)
            }
        }
        
        startActivity(intent)
        finish() // Cerrar esta actividad para que no se pueda volver atrás
    }
}
