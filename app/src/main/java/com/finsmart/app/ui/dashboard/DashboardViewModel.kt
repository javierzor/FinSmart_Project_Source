package com.finsmart.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finsmart.app.data.model.FinancialTip
import com.finsmart.app.data.model.Transaction
import com.finsmart.app.data.model.TransactionType
import com.finsmart.app.data.model.ContentDifficulty

class DashboardViewModel : ViewModel() {

    // Balance total
    private val _totalBalance = MutableLiveData<Double>()
    val totalBalance: LiveData<Double> = _totalBalance

    // Cambio en el balance
    private val _balanceChange = MutableLiveData<Double>()
    val balanceChange: LiveData<Double> = _balanceChange

    // Porcentaje de cambio en el balance
    private val _balanceChangePercent = MutableLiveData<Double>()
    val balanceChangePercent: LiveData<Double> = _balanceChangePercent

    // Ingresos totales
    private val _totalIncome = MutableLiveData<Double>()
    val totalIncome: LiveData<Double> = _totalIncome

    // Gastos totales
    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> = _totalExpenses

    // Ahorros totales
    private val _totalSavings = MutableLiveData<Double>()
    val totalSavings: LiveData<Double> = _totalSavings

    // Transacciones recientes
    private val _recentTransactions = MutableLiveData<List<Transaction>>()
    val recentTransactions: LiveData<List<Transaction>> = _recentTransactions

    // Consejo financiero del día
    private val _financialTip = MutableLiveData<FinancialTip>()
    val financialTip: LiveData<FinancialTip> = _financialTip

    // Nombre de usuario
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    // Cargar datos del dashboard
    fun loadDashboardData() {
        // En una implementación real, estos datos vendrían de un repositorio
        // Por ahora, usamos datos de ejemplo para la demostración
        
        // Cargar nombre de usuario
        _userName.value = "Usuario"
        
        // Cargar balance y cambios
        _totalBalance.value = 12345.67
        _balanceChange.value = 234.56
        _balanceChangePercent.value = 1.9
        
        // Cargar ingresos, gastos y ahorros
        _totalIncome.value = 3500.0
        _totalExpenses.value = 1245.30
        _totalSavings.value = 2254.70
        
        // Cargar transacciones recientes
        _recentTransactions.value = listOf(
            Transaction(
                id = "1",
                amount = 120.50,
                description = "Supermercado",
                category = "Alimentación",
                date = System.currentTimeMillis() - 86400000, // Ayer
                type = TransactionType.EXPENSE
            ),
            Transaction(
                id = "2",
                amount = 1500.00,
                description = "Salario",
                category = "Ingresos",
                date = System.currentTimeMillis() - 259200000, // Hace 3 días
                type = TransactionType.INCOME
            ),
            Transaction(
                id = "3",
                amount = 45.99,
                description = "Netflix",
                category = "Entretenimiento",
                date = System.currentTimeMillis() - 432000000, // Hace 5 días
                type = TransactionType.EXPENSE,
                isRecurring = true
            )
        )
        
        // Cargar consejo financiero
        _financialTip.value = FinancialTip(
            id = "1",
            content = "Considera automatizar tus ahorros. Configura transferencias automáticas a tu cuenta de ahorros el día que recibes tu sueldo para asegurar que ahorras antes de gastar.",
            category = "Ahorro",
            difficulty = ContentDifficulty.BEGINNER
        )
    }
}
