package by.bsuir.fitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView

class SecondaryActivity : AppCompatActivity() {
    private lateinit var bodyMassIndexTextView: TextView
    private lateinit var goalRadioGroup: RadioGroup
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        initElements()
        setEventsListeners()
    }

    private fun initElements() {
        bodyMassIndexTextView = findViewById(R.id.bodyMassIndexTextView)
        val bodyMassIndex = intent.extras!!.getFloat("body_mass_index")
        bodyMassIndexTextView.append(" $bodyMassIndex")
        goalRadioGroup = findViewById(R.id.goalRadioGroup)
        continueButton = findViewById(R.id.continueButtonNext)
    }

    private fun setEventsListeners() {
        goalRadioGroup.setOnCheckedChangeListener { _, _ ->
            continueButton.isEnabled = true
        }
    }
}