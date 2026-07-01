package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentPcBlocksBinding

class PcBlocksFragment : Fragment() {

    private lateinit var binding: FragmentPcBlocksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPcBlocksBinding.inflate(inflater, container, false)

        // 🟢 თითოეულ ღილაკს მიეცით თავისი შესაბამისი სურათის ID (R.drawable.შენი_სურათის_სახელი)
        binding.btnAdd1.setOnClickListener { addToCart("PC #101", "1900 GEL", R.drawable.pc_1) }
        binding.btnAdd2.setOnClickListener { addToCart("PC #102", "2100 GEL", R.drawable.pc_2) }
        binding.btnAdd3.setOnClickListener { addToCart("PC #103", "2500 GEL", R.drawable.pc_3) }
        binding.btnAdd4.setOnClickListener { addToCart("PC #104", "2800 GEL", R.drawable.pc_4) }
        binding.btnAdd5.setOnClickListener { addToCart("PC #105", "3200 GEL", R.drawable.pc_5) }
        binding.btnAdd6.setOnClickListener { addToCart("PC #106", "4100 GEL", R.drawable.pc_6) }
        binding.btnAdd7.setOnClickListener { addToCart("PC #107", "5000 GEL", R.drawable.pc_7) }

        return binding.root
    }

    private fun addToCart(name: String, price: String, imageRes: Int) {
        val item = CartItem(name, price, imageRes)
        (activity as? MainActivity)?.cartList?.add(item)
        Toast.makeText(context, "$name დაემატა კალათაში!", Toast.LENGTH_SHORT).show()
    }
}