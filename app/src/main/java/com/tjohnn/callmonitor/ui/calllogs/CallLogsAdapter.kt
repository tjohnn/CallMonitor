package com.tjohnn.callmonitor.ui.calllogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tjohnn.callmonitor.R
import com.tjohnn.callmonitor.ui.calllogs.model.CallLogUiModel

class CallLogsAdapter : RecyclerView.Adapter<CallLogsAdapter.ViewHolder>() {

    private val items: MutableList<CallLogUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_call_log, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: Collection<CallLogUiModel>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val phoneNumberText get() = itemView.findViewById<TextView>(R.id.call_item_phone_number)
        private val callerNameText get() = itemView.findViewById<TextView>(R.id.call_item_call_name)
        private val durationText get() = itemView.findViewById<TextView>(R.id.call_item_duration)

        fun bind(item: CallLogUiModel) {
            val context = itemView.context
            callerNameText.text = item.callerName.ifBlank { context.getString(R.string.unknown_caller_name) }
            phoneNumberText.text = item.phoneNumber
            durationText.text = context.getString(R.string.call_duration_seconds_text, item.durationInSeconds)
        }
    }
}
