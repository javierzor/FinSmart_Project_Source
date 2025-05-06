package com.finsmart.app.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

class PreferenceManager(context: Context) {
    
    companion object {
        private const val PREF_NAME = "finsmart_preferences"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_FIRST_LAUNCH = "first_launch"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_BIOMETRIC_ENABLED = "biometric_enabled"
        private const val KEY_NOTIFICATION_ENABLED = "notification_enabled"
    }
    
    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    
    // Gestión del tema
    fun getThemeMode(): Int {
        return preferences.getInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
    fun setThemeMode(mode: Int) {
        preferences.edit().putInt(KEY_THEME_MODE, mode).apply()
    }
    
    // Verificación de primer lanzamiento
    fun isFirstLaunch(): Boolean {
        return preferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }
    
    fun setFirstLaunchCompleted() {
        preferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }
    
    // Gestión de información del usuario
    fun saveUserInfo(userId: String, name: String, email: String) {
        preferences.edit()
            .putString(KEY_USER_ID, userId)
            .putString(KEY_USER_NAME, name)
            .putString(KEY_USER_EMAIL, email)
            .apply()
    }
    
    fun getUserId(): String? {
        return preferences.getString(KEY_USER_ID, null)
    }
    
    fun getUserName(): String? {
        return preferences.getString(KEY_USER_NAME, null)
    }
    
    fun getUserEmail(): String? {
        return preferences.getString(KEY_USER_EMAIL, null)
    }
    
    fun clearUserInfo() {
        preferences.edit()
            .remove(KEY_USER_ID)
            .remove(KEY_USER_NAME)
            .remove(KEY_USER_EMAIL)
            .apply()
    }
    
    // Configuración de seguridad
    fun isBiometricEnabled(): Boolean {
        return preferences.getBoolean(KEY_BIOMETRIC_ENABLED, false)
    }
    
    fun setBiometricEnabled(enabled: Boolean) {
        preferences.edit().putBoolean(KEY_BIOMETRIC_ENABLED, enabled).apply()
    }
    
    // Configuración de notificaciones
    fun isNotificationEnabled(): Boolean {
        return preferences.getBoolean(KEY_NOTIFICATION_ENABLED, true)
    }
    
    fun setNotificationEnabled(enabled: Boolean) {
        preferences.edit().putBoolean(KEY_NOTIFICATION_ENABLED, enabled).apply()
    }
    
    // Método para limpiar todas las preferencias (logout)
    fun clearAllPreferences() {
        // Mantener algunas configuraciones como el tema
        val themeMode = getThemeMode()
        val notificationEnabled = isNotificationEnabled()
        
        preferences.edit().clear().apply()
        
        // Restaurar configuraciones que deben mantenerse
        setThemeMode(themeMode)
        setNotificationEnabled(notificationEnabled)
        setFirstLaunchCompleted() // Ya no es primer lanzamiento
    }
}
