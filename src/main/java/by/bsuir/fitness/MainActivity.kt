package by.bsuir.fitness

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var usernameTextInput: TextInputEditText
    private lateinit var ageTextInput: TextInputEditText
    private lateinit var heightTextInput: TextInputEditText
    private lateinit var weightTextInput: TextInputEditText
    private lateinit var lifestyleRadioGroup: RadioGroup
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initElements()
        setEventListeners()
    }

    private fun initElements() {
        usernameTextInput = findViewById(R.id.usernameTextInput)
        ageTextInput = findViewById(R.id.ageTextInput)
        heightTextInput = findViewById(R.id.heightTextInput)
        weightTextInput = findViewById(R.id.weightTextInput)
        lifestyleRadioGroup = findViewById(R.id.lifestyleRadioGroup)
        continueButton = findViewById(R.id.continueButton)
    }

    private fun setEventListeners() {
        arrayOf(usernameTextInput, ageTextInput, heightTextInput, weightTextInput).forEach {
            it.doOnTextChanged { _, _, _, _ ->
                continueButton.isEnabled = isButtonEnabled()
            }
        }

        lifestyleRadioGroup.setOnCheckedChangeListener { _, _ ->
            continueButton.isEnabled = isButtonEnabled()
        }

        continueButton.setOnClickListener {
            if (checkTextFields()) {
                val intent = Intent(this, SecondaryActivity::class.java)
                val bodyMassIndex = computeBodyMassIndex()
                intent.putExtra("body_mass_index", bodyMassIndex)
                startActivity(intent)
            }
        }
    }

    private fun isButtonEnabled(): Boolean {
        val areTextFieldsFilled = arrayOf(usernameTextInput, ageTextInput, heightTextInput, weightTextInput).map {
            it.length() > 0
        }.reduce(Boolean::and)
        val isRadioButtonSelected = lifestyleRadioGroup.checkedRadioButtonId != -1
        return areTextFieldsFilled && isRadioButtonSelected
    }

    private fun checkTextFields(): Boolean {
        return if (!Regex("\\w+").matches(usernameTextInput.text.toString())) {
            usernameTextInput.error = "Поле должно содержать следующие символы: A-Z, a-z, 0-9, _"
            false
        } else {
            true
        }
    }

    private fun computeBodyMassIndex(): Float {
        val height = heightTextInput.text.toString().toFloat() / 100
        val weight = weightTextInput.text.toString().toFloat()
        return weight / (height * height)
    }
}