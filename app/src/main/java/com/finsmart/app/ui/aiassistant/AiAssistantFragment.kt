package com.finsmart.app.ui.aiassistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finsmart.app.R
import com.google.android.material.chip.Chip

class AiAssistantFragment : Fragment() {

    private lateinit var viewModel: AiAssistantViewModel
    private lateinit var messagesAdapter: AiMessagesAdapter
    
    // Vistas
    private lateinit var rvMessages: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var chipAnalyzeExpenses: Chip
    private lateinit var chipInvestmentAdvice: Chip
    private lateinit var chipBudgetHelp: Chip
    private lateinit var chipFinancialTerms: Chip

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ai_assistant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializar ViewModel
        viewModel = ViewModelProvider(this).get(AiAssistantViewModel::class.java)
        
        // Inicializar vistas
        initViews(view)
        
        // Configurar RecyclerView
        setupRecyclerView()
        
        // Configurar listeners
        setupListeners()
        
        // Observar cambios en los datos
        observeViewModel()
        
        // Cargar mensajes iniciales
        viewModel.loadInitialMessages()
    }
    
    private fun initViews(view: View) {
        rvMessages = view.findViewById(R.id.rv_messages)
        etMessage = view.findViewById(R.id.et_message)
        btnSend = view.findViewById(R.id.btn_send)
        chipAnalyzeExpenses = view.findViewById(R.id.chip_analyze_expenses)
        chipInvestmentAdvice = view.findViewById(R.id.chip_investment_advice)
        chipBudgetHelp = view.findViewById(R.id.chip_budget_help)
        chipFinancialTerms = view.findViewById(R.id.chip_financial_terms)
    }
    
    private fun setupRecyclerView() {
        messagesAdapter = AiMessagesAdapter(emptyList())
        
        rvMessages.apply {
            layoutManager = LinearLayoutManager(context).apply {
                stackFromEnd = true // Para que los mensajes aparezcan desde abajo
            }
            adapter = messagesAdapter
        }
    }
    
    private fun setupListeners() {
        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isNotEmpty()) {
                viewModel.sendMessage(message)
                etMessage.text.clear()
            }
        }
        
        chipAnalyzeExpenses.setOnClickListener {
            viewModel.sendMessage(getString(R.string.ai_analyze_expenses))
        }
        
        chipInvestmentAdvice.setOnClickListener {
            viewModel.sendMessage(getString(R.string.ai_investment_advice))
        }
        
        chipBudgetHelp.setOnClickListener {
            viewModel.sendMessage(getString(R.string.ai_budget_help))
        }
        
        chipFinancialTerms.setOnClickListener {
            viewModel.sendMessage(getString(R.string.ai_financial_terms))
        }
    }
    
    private fun observeViewModel() {
        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            messagesAdapter.updateMessages(messages)
            // Scroll al último mensaje
            if (messages.isNotEmpty()) {
                rvMessages.smoothScrollToPosition(messages.size - 1)
            }
        }
    }
}
