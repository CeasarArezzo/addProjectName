package com.study.addprojectname

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.study.addprojectname.R

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
    }

    fun signUp(view: View) {
        val email : String = findViewById<EditText>(R.id.enterEmail).text.toString()
        val password : String = findViewById<EditText>(R.id.enterPassword).text.toString()
        val confirmation : String = findViewById<EditText>(R.id.confirmPassword).text.toString()
        Log.i("am2021", email.isNotBlank().toString())
        Log.i("am2021", password.isNotBlank().toString())
        Log.i("am2021", password.equals(confirmation).toString())
        Log.i("am2021", password.toString())
        Log.i("am2021", confirmation.toString())
        if (email.isNotBlank() && password.isNotBlank() && password == confirmation)
        {
            if (password.length < 6)
            {
                findViewById<EditText>(R.id.enterPassword).error = "password too short"
                return
            }
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("am2021", "createUserWithEmail:success")
                        val user = auth.currentUser

                        val returnIntent: Intent = Intent()
                        returnIntent.putExtra("email", email)
                        returnIntent.putExtra("password", password)
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("am2021", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, task.exception?.message,
                            Toast.LENGTH_SHORT).show()
                    }
                }
//            val returnIntent: Intent = Intent()
//            returnIntent.putExtra("email", email)
//            returnIntent.putExtra("password", password)
//            setResult(Activity.RESULT_OK, returnIntent)
//            finish()
        }
    }
}