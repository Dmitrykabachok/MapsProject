package com.example.firebasetest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var upButton: Button
    lateinit var inButton: Button
    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var auth: FirebaseAuth
    lateinit var userLogedInTextView: TextView
    lateinit var signOutButton: Button
    var counter = 1
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        val database = Firebase.database
        val myRef = database.getReference("message")
        val rf =
            FirebaseDatabase.getInstance("https://secondproject-a7337-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("messages")


    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            Log.d("USER_CHECK", " USER_NOT_NULL, data${firebaseUser}")
            userIsLoged()
            userLogedInTextView.setText("You are signed in as ${auth.currentUser?.email}")

        } else {
            Log.d("USER_CHECK", "USER_IS_NULL")
            userIsUnknown()

        }

    }

    private fun initViews() {
        upButton = findViewById(R.id.button)
        inButton = findViewById(R.id.button2)
        passwordText = findViewById(R.id.userPassword)
        emailText = findViewById(R.id.userEmail)
        auth = FirebaseAuth.getInstance()
        userLogedInTextView = findViewById(R.id.userLogedInText)
        signOutButton = findViewById(R.id.signOutButton)

        signOutButton.setOnClickListener{
            auth.signOut()
            userIsUnknown()

        }

        upButton.setOnClickListener {
            if (!TextUtils.isEmpty(passwordText.text.toString()) and !TextUtils.isEmpty(emailText.text.toString())) {
                auth.createUserWithEmailAndPassword(
                    emailText.text.toString(),
                    passwordText.text.toString()
                ).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            if(emailText.text.toString().trim().matches(emailPattern.toRegex())){
                                verifyEmail()
                                userIsLoged()
                                Log.d("USER_CHECK", "USER_SIGN_UP_SUCCESSFUL")
                            }
                            else{
                                Toast.makeText(applicationContext,"Invalid email",  Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Log.d("USER_CHECK", "USER_SIGN_UP_ERROR")

                        }
                    })
            }
        }


        inButton.setOnClickListener {
            if (!TextUtils.isEmpty(passwordText.text.toString()) and !TextUtils.isEmpty(emailText.text.toString())) {
                auth.signInWithEmailAndPassword(
                    emailText.text.toString(),
                    passwordText.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        if (it.isSuccessful) {
                            Log.d("USER_CHECK", "USER_SIGN_IN_SUCCESSFUL")
                            Log.d("USER_CHECK","as ${auth.currentUser?.email}")
                            userIsLoged()
                            
                        } else {
                            Log.d("USER_CHECK", "USER_SIGN_IN_ERROR")


                        }
                    }
                }
            }
        }
    }
    private fun userIsUnknown(){
        userLogedInTextView.visibility = View.GONE
        signOutButton.visibility = View.GONE
        emailText.visibility = View.VISIBLE
        passwordText.visibility = View.VISIBLE
        inButton.visibility = View.VISIBLE
        upButton.visibility = View.VISIBLE
    }

    private fun userIsLoged() {
         userLogedInTextView.visibility = View.VISIBLE
            signOutButton.visibility = View.VISIBLE
            emailText.visibility = View.GONE
            passwordText.visibility = View.GONE
            inButton.visibility = View.GONE
            upButton.visibility = View.GONE
        }

    @SuppressLint("ShowToast")
    private fun verifyEmail(){
        auth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
            if(it.isSuccessful){
                Log.d("USER_CHECK", "VERIFICATION")
                Toast.makeText(applicationContext,"check your email", Toast.LENGTH_SHORT)
            }
            else{
                Toast.makeText(applicationContext,"verification error", Toast.LENGTH_SHORT)

            }
        }
    }
    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

/*
 button.setOnClickListener{
            user = User("aaaaa$counter", "bbb@gmail.com")
            rf.push().setValue(user)
            myRef.setValue(user)
            Log.d("DATABASE_CHECK","SENT")
            counter++
        }

            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    val value = snapshot.getValue()

                    Log.d("TAG","VALUE IS " + value)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }

            })
 */