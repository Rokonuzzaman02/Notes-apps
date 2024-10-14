package com.example.notes

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login_Fragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginbtt.setOnClickListener {

            val email = binding.emailltex.text.toString().trim()
            val password = binding.passwtex.text.toString().trim()

            if (isEmaillvaild(email) && isPasswordvaild(password)) {
                loginUser(email, password)
            } else {
                Toast.makeText(requireContext(), "Invalid eamil and password", Toast.LENGTH_SHORT)
                    .show()
            }


        }
        binding.creataccont.setOnClickListener {
            findNavController().navigate(R.id.action_login_Fragment_to_singinFragment)
        }
        return binding.root
    }

    private fun loginUser(email: String, password: String) {

        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(
                    requireContext(),
                    "login successfuly${user?.email}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_login_Fragment_to_home_Fragment)

            } else {
                Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    fun isEmaillvaild(email: String): Boolean {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }

    fun isPasswordvaild(password: String): Boolean {

        return password.length >= 4

    }
}

