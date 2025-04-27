package com.example.safeai

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safeai.R

// IncidentAdapter.kt
class IncidentAdapter(private var incidents: List<Incident>, private val onClick: (Incident) -> Unit) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    private var allIncidents: List<Incident> = incidents // Store the original list for filtering

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = incidents[position]
        holder.title.text = incident.title
        holder.severity.text = "Severity: ${incident.severity}"
        holder.date.text = "Date: ${incident.reported_at}"

        // Set background color based on severity
        when (incident.severity) {
            "Low" -> holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.green))
            "Medium" -> holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.yellow))
            "High" -> holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.red))
        }

        holder.itemView.setOnClickListener { onClick(incident) }
    }

    override fun getItemCount(): Int {
        return incidents.size
    }

    // Filter incidents by severity
    fun filterBySeverity(severity: String?) {
        incidents = if (severity.isNullOrEmpty() || severity == "All") {
            allIncidents // If no filter or "All" is selected, show all incidents
        } else {
            allIncidents.filter { it.severity == severity }
        }
        notifyDataSetChanged() // Notify the adapter to update the RecyclerView
    }

    // Update data in the adapter
    fun updateData(filteredIncidents: List<Incident>) {
        this.incidents = filteredIncidents
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    class IncidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.incidentTitle)
        val severity: TextView = view.findViewById(R.id.incidentSeverity)
        val date: TextView = view.findViewById(R.id.incidentDate)
    }
}
