package com.example.safeai

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// DetailActivity.kt
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get the incident details from the Intent
        val incident = intent.getParcelableExtra<Incident>("incident")


        // Find the views to display the incident details
        val titleTextView: TextView = findViewById(R.id.incidentDetailTitle)
        val severityTextView: TextView = findViewById(R.id.incidentDetailSeverity)
        val dateTextView: TextView = findViewById(R.id.incidentDetailDate)
        val descriptionTextView: TextView = findViewById(R.id.incidentDetailDescription)

        // Set the incident details into the views
        incident?.let {
            titleTextView.text = it.title
            severityTextView.text = "Severity: ${it.severity}"
            dateTextView.text = "Date: ${it.reported_at}"
            descriptionTextView.text = "Description: ${it.description}"
        }

        // Set up back navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Handle back navigation
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
