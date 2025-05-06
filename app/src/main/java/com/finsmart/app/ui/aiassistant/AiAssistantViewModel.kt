package com.finsmart.app.ui.aiassistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finsmart.app.data.model.AIAssistantMessage
import com.finsmart.app.data.model.AIMessageAttachment
import com.finsmart.app.data.model.AIAttachmentType

class AiAssistantViewModel : ViewModel() {

    // Lista de mensajes
    private val _messages = MutableLiveData<List<AIAssistantMessage>>()
    val messages: LiveData<List<AIAssistantMessage>> = _messages
    
    // Lista mutable para mantener los mensajes
    private val messagesList = mutableListOf<AIAssistantMessage>()
    
    // Cargar mensajes iniciales
    fun loadInitialMessages() {
        // En una implementación real, estos mensajes vendrían de una base de datos
        // o de un historial guardado
        
        // Mensaje de bienvenida del asistente
        val welcomeMessage = AIAssistantMessage(
            id = "welcome",
            content = "¡Hola! Soy tu asistente financiero personal. Puedo ayudarte con preguntas sobre tus finanzas, presupuestos, inversiones y más. ¿En qué puedo ayudarte hoy?",
            isUserMessage = false
        )
        
        messagesList.add(welcomeMessage)
        _messages.value = messagesList.toList()
    }
    
    // Enviar un mensaje
    fun sendMessage(content: String) {
        // Crear y añadir el mensaje del usuario
        val userMessage = AIAssistantMessage(
            id = System.currentTimeMillis().toString(),
            content = content,
            isUserMessage = true
        )
        
        messagesList.add(userMessage)
        _messages.value = messagesList.toList()
        
        // En una implementación real, aquí se enviaría la consulta a un servicio de IA
        // y se procesaría la respuesta
        
        // Por ahora, generamos respuestas predefinidas según el contenido
        generateResponse(content)
    }
    
    // Generar una respuesta basada en el contenido del mensaje
    private fun generateResponse(content: String) {
        // En una implementación real, esto vendría de un servicio de IA
        // Por ahora, usamos respuestas predefinidas para demostración
        
        val responseContent = when {
            content.contains("analizar", ignoreCase = true) || 
            content.contains("gastos", ignoreCase = true) -> {
                "He analizado tus gastos del último mes. Tus principales categorías de gasto son:\n\n" +
                "1. Alimentación: $450 (36%)\n" +
                "2. Vivienda: $350 (28%)\n" +
                "3. Transporte: $200 (16%)\n" +
                "4. Entretenimiento: $150 (12%)\n" +
                "5. Otros: $100 (8%)\n\n" +
                "Comparado con el mes anterior, has reducido tus gastos en entretenimiento en un 10%, ¡buen trabajo!"
            }
            content.contains("inversión", ignoreCase = true) || 
            content.contains("invertir", ignoreCase = true) -> {
                "Basado en tu perfil financiero y objetivos, te recomendaría considerar:\n\n" +
                "1. Fondos indexados: Ofrecen diversificación con comisiones bajas\n" +
                "2. ETFs: Proporcionan exposición a sectores específicos\n" +
                "3. Bonos corporativos: Para equilibrar tu cartera\n\n" +
                "Recuerda que toda inversión conlleva riesgos. ¿Te gustaría más información sobre alguna de estas opciones?"
            }
            content.contains("presupuesto", ignoreCase = true) -> {
                "Para mejorar tu presupuesto, te recomiendo la regla 50/30/20:\n\n" +
                "- 50% para necesidades (vivienda, alimentación, servicios básicos)\n" +
                "- 30% para deseos (entretenimiento, comidas fuera, etc.)\n" +
                "- 20% para ahorro e inversión\n\n" +
                "Según tus gastos actuales, estás destinando un 65% a necesidades, 25% a deseos y 10% a ahorro. Podrías intentar reducir algunos gastos de necesidades para aumentar tu tasa de ahorro."
            }
            content.contains("términos", ignoreCase = true) || 
            content.contains("financieros", ignoreCase = true) -> {
                "Aquí tienes algunos términos financieros básicos:\n\n" +
                "- Interés compuesto: Interés que se calcula sobre el capital inicial más los intereses acumulados\n" +
                "- Diversificación: Estrategia de distribuir inversiones para reducir riesgos\n" +
                "- Fondo de emergencia: Ahorro para imprevistos, idealmente 3-6 meses de gastos\n" +
                "- Inflación: Aumento general de precios que reduce el poder adquisitivo\n\n" +
                "¿Hay algún término específico que te gustaría que te explique con más detalle?"
            }
            else -> {
                "Gracias por tu mensaje. Para ayudarte mejor, ¿podrías darme más detalles sobre tu consulta financiera? Puedo ayudarte con análisis de gastos, consejos de inversión, gestión de presupuesto o explicación de términos financieros."
            }
        }
        
        // Crear y añadir la respuesta del asistente
        val assistantMessage = AIAssistantMessage(
            id = System.currentTimeMillis().toString(),
            content = responseContent,
            isUserMessage = false
        )
        
        // Simular un pequeño retraso para que parezca que el asistente está "pensando"
        // En una implementación real, este retraso vendría del tiempo de procesamiento de la IA
        android.os.Handler().postDelayed({
            messagesList.add(assistantMessage)
            _messages.value = messagesList.toList()
        }, 1000)
    }
}
