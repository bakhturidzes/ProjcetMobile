package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAccessoriesBinding

class AccessoriesFragment : Fragment() {

    private lateinit var binding: FragmentAccessoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccessoriesBinding.inflate(inflater, container, false)

        // კლავიატურები
        binding.btnAddKeyboard1.setOnClickListener { addToCart("Keyboard #1", "120 GEL", R.drawable.keyboard_1) }
        binding.btnAddKeyboard2.setOnClickListener { addToCart("Keyboard #2", "180 GEL", R.drawable.keyboard_2) }
        binding.btnAddKeyboard3.setOnClickListener { addToCart("Keyboard #3", "290 GEL", R.drawable.keyboard_3) }

        // მაუსები
        binding.btnAddMouse1.setOnClickListener { addToCart("Mouse #1", "60 GEL", R.drawable.mouse_1) }
        binding.btnAddMouse2.setOnClickListener { addToCart("Mouse #2", "95 GEL", R.drawable.mouse_2) }
        binding.btnAddMouse3.setOnClickListener { addToCart("Mouse #3", "150 GEL", R.drawable.mouse_3) }

        // ყურსასმენები
        binding.btnAddHeadset1.setOnClickListener { addToCart("Headset #1", "140 GEL", R.drawable.headset_1) }
        binding.btnAddHeadset2.setOnClickListener { addToCart("Headset #2", "220 GEL", R.drawable.headset_2) }
        binding.btnAddHeadset3.setOnClickListener { addToCart("Headset #3", "380 GEL", R.drawable.headset_3) }

        return binding.root
    }

    private fun addToCart(name: String, price: String, imageRes: Int) {
        val item = CartItem(name, price, imageRes)
        (activity as? MainActivity)?.cartList?.add(item)
        Toast.makeText(context, "$name დაემატა კალათაში!", Toast.LENGTH_SHORT).show()
    }
}