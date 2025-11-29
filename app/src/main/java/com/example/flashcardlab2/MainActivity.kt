import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashcardlab2.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.questionInput )) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val questionInput = findViewById<EditText>(R.id.questionInput)
        val answer1Input = findViewById<EditText>(R.id.answer1Input)
        val answer2Input = findViewById<EditText>(R.id.answer2Input)
        val answer3Input = findViewById<EditText>(R.id.answer3Input)
        val saveButton = findViewById<Button>(R.id.saveButton)


        saveButton.setOnClickListener {

            val question = questionInput.text.toString()
            val answer1 = answer1Input.text.toString()
            val answer2 = answer2Input.text.toString()
            val answer3 = answer3Input.text.toString()


            if (question.isBlank() || answer3.isBlank()) {
                Toast.makeText(
                    this,
                    "Please enter both a question and the correct answer!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            Snackbar.make(saveButton, "Card Saved! Question: $question", Snackbar.LENGTH_LONG)
                .show()

        }
    }

    private fun findViewById(id: Any): View {
        return TODO("Provide the return value")
    }
}