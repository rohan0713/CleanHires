package com.maid.cleanhires.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maid.cleanhires.R
import com.maid.cleanhires.databinding.ActivityLoginFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginFirstActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginFirstBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onStart() {
        super.onStart()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            Toast.makeText(this, "Already Signed in as ${currentUser.displayName}", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@LoginFirstActivity, "No login detected", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityLoginFirstBinding.inflate(layoutInflater).also { binding = it }.root)
        window.statusBarColor = Color.WHITE

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        updateUI(currentUser)

        binding.btnLogin.setOnClickListener {
            signOutFromGoogle()
            if(validateCredentials()) {
                Intent(this@LoginFirstActivity, HomeActivity::class.java).also {
                    startActivity(it)
                }
            }else{
                Snackbar.make(
                    binding.root,
                    "Invalid Credentials",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.googleSignButton.setOnClickListener {
            signIn()
        }
    }

    private fun validateCredentials() : Boolean {

        val email = binding.etEmail.text
        val password = binding.etPassword.text

        if(email != null && password != null && password.length > 6  && password.length < 8){
            return true
        }
        return false
    }

    private fun signIn() {

        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOutFromGoogle() {
        auth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show()
        }
    }
}