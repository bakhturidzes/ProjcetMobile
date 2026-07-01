package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // გლობალური სია კალათაში დამატებული პროდუქტებისთვის
    val cartList = ArrayList<CartItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomButtonsContainer) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(bottom = systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            updateBottomMenuColors("home")
        }

        binding.btnHome.setOnClickListener { replaceFragment(HomeFragment(), "home") }
        binding.btnProfile.setOnClickListener { replaceFragment(ProfileFragment(), "profile") }
        binding.btnAbout.setOnClickListener { replaceFragment(AboutFragment(), "about") }
        binding.btnSettings.setOnClickListener { replaceFragment(SettingsFragment(), "settings") }

        // კალათის მცურავ წრეზე კლიკი
        binding.btnCart.setOnClickListener { replaceFragment(CartFragment(), "cart") }

        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            when (currentFragment) {
                is HomeFragment,
                is PcBlocksFragment,
                is AccessoriesFragment,
                is WorkspaceFragment,
                is MobilesFragment -> updateBottomMenuColors("home")

                is CartFragment -> updateBottomMenuColors("cart")
                is ProfileFragment -> updateBottomMenuColors("profile")
                is AboutFragment -> updateBottomMenuColors("about")
                is SettingsFragment -> updateBottomMenuColors("settings")
            }
        }
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)

        if (tag != "home") {
            transaction.addToBackStack(null)
        } else {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        transaction.commit()

        if (tag == "home" || tag == "pc_blocks" || tag == "accessories" || tag == "workspace" || tag == "mobiles") {
            updateBottomMenuColors("home")
        } else {
            updateBottomMenuColors(tag)
        }
    }

    private fun updateBottomMenuColors(activeTag: String) {
        val activeColor = ColorStateList.valueOf(Color.parseColor("#0057FF")) // ლურჯი
        val inactiveColor = ColorStateList.valueOf(Color.parseColor("#757575")) // ნაცრისფერი

        // 🟢 მცურავი წრიული კალათა გამოჩნდეს მხოლოდ პროდუქტების კატეგორიებში
        if (activeTag == "home" || activeTag == "pc_blocks" || activeTag == "accessories" || activeTag == "workspace" || activeTag == "mobiles") {
            binding.btnCart.visibility = View.VISIBLE
        } else {
            binding.btnCart.visibility = View.GONE
        }

        // 🟢 გასწორდა: კალათის აიქონის თეთრად დატინტვა (ლურჯ ფონზე)
        binding.imgCart.imageTintList = ColorStateList.valueOf(Color.WHITE)

        binding.imgHome.imageTintList = if (activeTag == "home") activeColor else inactiveColor
        binding.imgProfile.imageTintList = if (activeTag == "profile") activeColor else inactiveColor
        binding.imgAbout.imageTintList = if (activeTag == "about") activeColor else inactiveColor
        binding.imgSettings.imageTintList = if (activeTag == "settings") activeColor else inactiveColor
    }
}