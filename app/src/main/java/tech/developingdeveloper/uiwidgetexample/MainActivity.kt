package tech.developingdeveloper.uiwidgetexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ScrollView
import androidx.core.view.get
import tech.developingdeveloper.uiwidgetexample.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*

            For simplicity, View binding is used in this project

            To enable View binding,
                - open app gradle ( Module: appname.app), there are two gradle one for project and one for module
                -
                    android {
                        ...

                        buildFeatures {
                            viewBinding true
                        }
                    }

        */

        // set data in the location spinner (drop down menu)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.locations)
        )
        binding.list.setAdapter(adapter)

        binding.submitButton.setOnClickListener {
            val name = binding.nameEditText.text

            val password = binding.passwordEditText.text

            /*

            multiple ways to get the value from the radio group
            1.
                val gender = when (binding.genderRadioGroup.checkedRadioButtonId) {
                    binding.genderRadioGroup[0].id-> "Male"
                    binding.genderRadioGroup[1].id -> "Female"
                    else -> "Nil"
                }

            2.
                val gender = when (binding.genderRadioGroup.checkedRadioButtonId) {
                    R.id.male_radio_button -> "Male"
                    R.id.female_radio_button -> "Female"
                    else -> "Nil"
                }

            3.
                val gender = if(binding.maleRadioButton.isChecked) "Male" else "Female"

            */

            val gender = when (binding.genderRadioGroup.checkedRadioButtonId) {
                R.id.male_radio_button -> "Male"
                R.id.female_radio_button -> "Female"
                else -> "Nil"
            }

            val languagesBuilder = StringBuilder()

            if (binding.cSharpCheckBox.isChecked)
                languagesBuilder.append("C#")

            if (binding.cppCheckBox.isChecked) {
                if (languagesBuilder.isNotEmpty())
                    languagesBuilder.append(", ")

                languagesBuilder.append("Cpp")
            }

            if (binding.kotlinCheckBox.isChecked) {
                if (languagesBuilder.isNotEmpty())
                    languagesBuilder.append(", ")

                languagesBuilder.append("Kotlin")
            }

            if (languagesBuilder.isEmpty())
                languagesBuilder.append("No language selected")

            val location = binding.list.text

            val rating = binding.ratingBar.rating

            val sliderRating = binding.slider.value

            val isDarkModeEnabled = binding.darkModeSwitch.isChecked

            binding.outputTextView.text = ("""Name: $name
                |Password: $password
                |Gender: $gender
                |Languages chosen: $languagesBuilder
                |Location: $location
                |Rating: $rating
                |Slider rating: $sliderRating
                |Dark Mode enabled: ${if (isDarkModeEnabled) "Yes" else "No"}
            """.trimMargin())

            binding.divider.visibility = View.VISIBLE
            binding.outputTextView.visibility = View.VISIBLE

            binding.root.post {
                binding.root.fullScroll(ScrollView.FOCUS_DOWN)
            }
        }
    }
}