package com.example.notes

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.notes.databinding.FragmentSinginBinding
import com.google.firebase.auth.FirebaseAuth

class singinFragment : Fragment() {
    lateinit var binding: FragmentSinginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinginBinding.inflate(inflater,container,false)
        binding.singinbtt.setOnClickListener {

            val email = binding.emailltex.text.toString().trim()
            val password = binding.passwtex.text.toString().trim()
            val user = binding.usernam.text.toString().trim()

            if (isEmaillvaild(email) && isPasswordvaild(password)){

                siginuser(email,password,user)
            }else{
                Toast.makeText(requireContext(), "Invalid email and password", Toast.LENGTH_SHORT).show()
            }



        }


        return binding.root
    }

    private fun siginuser(email: String, password: String, user: String) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

            if (task.isSuccessful){
                Toast.makeText(requireContext(), "Cerate account successfuly", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_singinFragment_to_login_Fragment)
            }else{

                Toast.makeText(requireContext(), "${task.exception?.message}", Toast.LENGTH_SHORT).show()
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