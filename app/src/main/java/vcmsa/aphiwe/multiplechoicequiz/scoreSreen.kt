package vcmsa.aphiwe.multiplechoicequiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class scoreScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_sreen)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvfeedback = findViewById<TextView>(R.id.tvfeedback)
        val btnExit = findViewById<Button>(R.id.btnExit)


        val score = intent.getIntExtra("SCORE", 0)
        val feedback = intent.getStringExtra("FEEDBACK") ?: "No feedback available."

        tvScore.text = "Your Score: $score / 5"
        tvfeedback.text = feedback

        btnExit.setOnClickListener {
            finishAffinity()
        }

    }
}
// Sourced Code from ChatGPT
