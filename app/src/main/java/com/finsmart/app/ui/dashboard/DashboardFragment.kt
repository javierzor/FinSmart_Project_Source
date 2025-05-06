package com.finsmart.app.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finsmart.app.R
import com.finsmart.app.data.model.Transaction
import com.finsmart.app.data.model.TransactionType
import com.google.android.material.card.MaterialCardView

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var transactionsAdapter: TransactionsAdapter
    
    // Vistas
    private lateinit var tvGreeting: TextView
    private lateinit var tvBalanceAmount: TextView
    private lateinit var tvBalanceChange: TextView
    private lateinit var tvIncome: TextView
    private lateinit var tvExpenses: TextView
    private lateinit var tvSavings: TextView
    private lateinit var rvRecentTransactions: RecyclerView
    private lateinit var tvTipContent: TextView
    private lateinit var cardQuickBudget: MaterialCardView
    private lateinit var cardQuickInvest: MaterialCardView
    private lateinit var cardQuickAr: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        
        // Inicializar vistas
        initViews(view)
        
        // Configurar RecyclerView
        setupRecyclerView()
        
        // Configurar listeners
        setupListeners()
        
        // Observar cambios en los datos
        observeViewModel()
        
        // Cargar datos iniciales
        viewModel.loadDashboardData()
    }
    
    private fun initViews(view: View) {
        tvGreeting = view.findViewById(R.id.tv_greeting)
        tvBalanceAmount = view.findViewById(R.id.tv_balance_amount)
        tvBalanceChange = view.findViewById(R.id.tv_balance_change)
        tvIncome = view.findViewById(R.id.tv_income)
        tvExpenses = view.findViewById(R.id.tv_expenses)
        tvSavings = view.findViewById(R.id.tv_savings)
        rvRecentTransactions = view.findViewById(R.id.rv_recent_transactions)
        tvTipContent = view.findViewById(R.id.tv_tip_content)
        cardQuickBudget = view.findViewById(R.id.card_quick_budget)
        cardQuickInvest = view.findViewById(R.id.card_quick_invest)
        cardQuickAr = view.findViewById(R.id.card_quick_ar)
    }
    
    private fun setupRecyclerView() {
        transactionsAdapter = TransactionsAdapter(emptyList()) { transaction ->
            // Manejar clic en transacción
            // Navegar al detalle de la transacción
        }
        
        rvRecentTransactions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionsAdapter
        }
    }
    
    private fun setupListeners() {
        cardQuickBudget.setOnClickListener {
            // Navegar a la sección de presupuesto
        }
        
        cardQuickInvest.setOnClickListener {
            // Navegar a la sección de inversiones
        }
        
        cardQuickAr.setOnClickListener {
            // Navegar a la sección de Realidad Financiera Aumentada
        }
    }
    
    private fun observeViewModel() {
        // Observar cambios en el balance
        viewModel.totalBalance.observe(viewLifecycleOwner) { balance ->
            tvBalanceAmount.text = String.format("$%,.2f", balance)
        }
        
        // Observar cambios en el cambio de balance
        viewModel.balanceChange.observe(viewLifecycleOwner) { change ->
            val prefix = if (change >= 0) "+" else ""
            tvBalanceChange.text = String.format("%s$%,.2f (%.1f%%)", prefix, change, viewModel.balanceChangePercent.value ?: 0.0)
        }
        
        // Observar cambios en ingresos
        viewModel.totalIncome.observe(viewLifecycleOwner) { income ->
            tvIncome.text = String.format("$%,.2f", income)
        }
        
        // Observar cambios en gastos
        viewModel.totalExpenses.observe(viewLifecycleOwner) { expenses ->
            tvExpenses.text = String.format("$%,.2f", expenses)
        }
        
        // Observar cambios en ahorros
        viewModel.totalSavings.observe(viewLifecycleOwner) { savings ->
            tvSavings.text = String.format("$%,.2f", savings)
        }
        
        // Observar cambios en transacciones recientes
        viewModel.recentTransactions.observe(viewLifecycleOwner) { transactions ->
            transactionsAdapter.updateTransactions(transactions)
        }
        
        // Observar cambios en el consejo financiero
        viewModel.financialTip.observe(viewLifecycleOwner) { tip ->
            tvTipContent.text = tip.content
        }
        
        // Observar cambios en el nombre de usuario
        viewModel.userName.observe(viewLifecycleOwner) { name ->
            tvGreeting.text = getString(R.string.welcome, name)
        }
    }
}
