package com.example.safeai

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ReportIncidentActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var severitySpinner: Spinner
    private lateinit var saveButton: Button

    private lateinit var selectedSeverity: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_incident)

        titleEditText = findViewById(R.id.editTextTitle)
        descriptionEditText = findViewById(R.id.editTextDescription)
        severitySpinner = findViewById(R.id.spinnerSeverity)
        saveButton = findViewById(R.id.buttonSubmit)

        val severities = listOf("Low", "Medium", "High")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, severities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        severitySpinner.adapter = adapter

        severitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                selectedSeverity = severities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedSeverity = "Low"
            }
        }

        saveButton.setOnClickListener {
            submitIncident()
        }
    }

    private fun submitIncident() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val currentDateTime = getCurrentDateTime()

        val newIncident = Incident(
            id = 0, // Temporary, MainActivity will assign real ID
            title = title,
            description = description,
            severity = selectedSeverity,
            reported_at = currentDateTime
        )

        val resultIntent = Intent()
        resultIntent.putExtra("new_incident", newIncident as Parcelable) // ✅ fixed
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        return sdf.format(Date())
    }
}
