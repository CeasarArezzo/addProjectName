package com.study.addprojectname

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.study.addprojectname.R
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mDatabase = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

//        val connectedRef = Firebase.database.getReference(".info/connected")
//        connectedRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val connected = snapshot.getValue(Boolean::class.java) ?: false
//                if (connected) {
//                    Log.d("am2021", "connected")
//                } else {
//                    Log.d("am2021", "not connected")
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("am2021", "Listener was cancelled")
//            }
//        })
//
//        val tmp = mDatabase.child("lobbies").push()
//        tmp.setValue("kanapka2")
//        val data = tmp.get()
////            .addOnSuccessListener {
////            Log.i("firebase", "Got value ${it.value}")
////        }.addOnFailureListener{
////            Log.e("firebase", "Error getting data", it)
////        }
//        Log.i("am2021", "result = $data")


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
//                Log.i("am2021", "logged in")
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                if (response != null) {
                    Toast.makeText(baseContext, response.error.toString(),
                        Toast.LENGTH_SHORT).show()
                }
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }

    }

    companion object {
        var RC_SIGN_IN = 123
        var RC_SIGN_UP = 124
    }

    fun register(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun login()
    {
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
    }

    fun login(view: View) {
        login()
    }
}