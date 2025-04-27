package com.example.safeai

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var incidentAdapter: IncidentAdapter
    private val allIncidents = mutableListOf(
        Incident(1, "Biased Recommendation Algorithm", "Algorithm consistently favored certain categories leading to biased recommendations.", "Medium", "2025-03-15T10:00:00Z"),
        Incident(2, "Unauthorized Data Access", "Unauthorized access detected in the user data storage, potentially compromising user privacy.", "High", "2025-04-01T12:30:00Z"),
        Incident(3, "Server Downtime", "The AI server experienced unexpected downtime affecting service availability.", "Low", "2025-04-10T14:15:00Z"),
        Incident(4, "Training Data Inaccuracy", "Inaccurate training data was used, leading to lower model performance.", "Medium", "2025-04-20T08:45:00Z")
    )

    private var filteredIncidents = allIncidents.toMutableList()

    private lateinit var fabReportIncident: FloatingActionButton
    private lateinit var severitySpinner: Spinner

    private val reportIncidentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val newIncident = data?.getSerializableExtra("new_incident") as? Incident
            if (newIncident != null) {
                // Assign a new ID
                val newId = (allIncidents.maxOfOrNull { it.id } ?: 0) + 1
                val incidentToAdd = newIncident.copy(id = newId)
                allIncidents.add(incidentToAdd)

                // Re-filter the list to update the displayed incidents
                filterIncidents()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        incidentAdapter = IncidentAdapter(filteredIncidents) { incident ->
            navigateToDetailActivity(incident)
        }
        recyclerView.adapter = incidentAdapter

        fabReportIncident = findViewById(R.id.fab)
        fabReportIncident.setOnClickListener {
            navigateToReportIncidentActivity()
        }

        // Initialize Spinner for filtering by Severity
        severitySpinner = findViewById(R.id.spinnerSeverity)
        val severities = listOf("All", "Low", "Medium", "High")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, severities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        severitySpinner.adapter = adapter

        severitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                filterIncidents(severities[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun filterIncidents(severity: String = "All") {
        filteredIncidents = if (severity == "All") {
            allIncidents.toMutableList()
        } else {
            allIncidents.filter { it.severity == severity }.toMutableList()
        }

        incidentAdapter.updateData(filteredIncidents)
    }

    private fun navigateToDetailActivity(incident: Incident) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("incident", incident as Parcelable) // 👈 Add "as Parcelable"
        startActivity(intent)
    }

    private fun navigateToReportIncidentActivity() {
        val intent = Intent(this, ReportIncidentActivity::class.java)
        reportIncidentLauncher.launch(intent)
    }
}
