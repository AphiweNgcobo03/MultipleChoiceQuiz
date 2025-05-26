package vcmsa.aphiwe.multiplechoicequiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Questions : AppCompatActivity() {

    private var counter = 0

    private val historyQuestions = arrayOf(
        "Nelson Mandela was imprisoned for 27 years for his fight against apartheid.",
        "Nelson Mandela was the first black president of South Africa.",
        "Nelson Mandela won the Nobel Peace Prize in 1999.",
        "Nelson Mandela led the African National Congress (ANC) Youth League.",
        "Nelson Mandela served two full terms as president of South Africa."
    )

    private val answerChoices = arrayOf(
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        arrayOf("True", "False"),
        arrayOf("True", "False")
    )

    private val correctAnswers = arrayOf("True", "True", "False", "True", "False")
    private val userAnswers = arrayOfNulls<String>(5)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz2)

        val textQuestions = findViewById<TextView>(R.id.textQuestions)
        val rbtngAnswers = findViewById<RadioGroup>(R.id.rbtngQuizAnswers)
        val btnNext = findViewById<Button>(R.id.btnnextquestion)

        displayQuestion(textQuestions, rbtngAnswers)

        btnNext.setOnClickListener {
            val selectedId = rbtngAnswers.checkedRadioButtonId
            if (selectedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                userAnswers[counter] = selectedRadioButton.text.toString()
                counter++

                if (counter < historyQuestions.size) {
                    displayQuestion(textQuestions, rbtngAnswers)
                } else {
                    val score = calculateScore()
                    val feedback = when {
                        score == 5 -> "Excellent work!"
                        score >= 3 -> "Good job, keep practicing!"
                        else -> "Try again and keep learning!"
                    }

                    val intent = Intent(this, scoreScreen::class.java)
                    intent.putExtra("SCORE", score)
                    intent.putExtra("FEEDBACK", feedback)
                    intent.putExtra("Correct", score) // Optional if you use this extra
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion(tvQuestion: TextView, rbtngAnswers: RadioGroup) {
        tvQuestion.text = historyQuestions[counter]
        rbtngAnswers.clearCheck()

        for (i in 0 until rbtngAnswers.childCount) {
            val radioButton = rbtngAnswers.getChildAt(i) as RadioButton
            radioButton.text = answerChoices[counter][i]
        }
    }

    private fun calculateScore(): Int {
        var score = 0
        for (i in correctAnswers.indices) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++
            }
        }
        return score
    }
}
