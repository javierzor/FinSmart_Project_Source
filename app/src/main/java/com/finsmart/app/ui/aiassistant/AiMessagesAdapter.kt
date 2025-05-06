package com.finsmart.app.ui.aiassistant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finsmart.app.R
import com.finsmart.app.data.model.AIAssistantMessage

class AiMessagesAdapter(private var messages: List<AIAssistantMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER_MESSAGE = 1
        private const val VIEW_TYPE_ASSISTANT_MESSAGE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER_MESSAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_message, parent, false)
                UserMessageViewHolder(view)
            }
            VIEW_TYPE_ASSISTANT_MESSAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_ai_message, parent, false)
                AssistantMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Tipo de vista desconocido: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        
        when (holder) {
            is UserMessageViewHolder -> holder.bind(message)
            is AssistantMessageViewHolder -> holder.bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUserMessage) {
            VIEW_TYPE_USER_MESSAGE
        } else {
            VIEW_TYPE_ASSISTANT_MESSAGE
        }
    }

    fun updateMessages(newMessages: List<AIAssistantMessage>) {
        this.messages = newMessages
        notifyDataSetChanged()
    }

    inner class UserMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tv_message_content)

        fun bind(message: AIAssistantMessage) {
            tvMessage.text = message.content
        }
    }

    inner class AssistantMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tv_message_content)

        fun bind(message: AIAssistantMessage) {
            tvMessage.text = message.content
        }
    }
}
