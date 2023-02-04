package com.jke.dreamme.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jke.dreamme.R
import com.jke.dreamme.databinding.ActivityMainBinding
import com.jke.dreamme.ui.fragments.VozFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initFragment(){

        fragmentVisibility(VozFragment())
        true

    }

    // Manejo de fragmentos
    private fun fragmentVisibility(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.FragmentPrincipal.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}