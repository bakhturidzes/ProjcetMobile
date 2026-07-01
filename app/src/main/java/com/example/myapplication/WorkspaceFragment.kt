package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentWorkspaceBinding

class WorkspaceFragment : Fragment() {

    private var _binding: FragmentWorkspaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkspaceBinding.inflate(inflater, container, false)

        setupWorkspaceItems()

        return binding.root
    }

    private fun setupWorkspaceItems() {
        // 1. მაგიდები (4 ცალი)
        configureItem(binding.itemTable1, "Table #01", "450 GEL", R.drawable.table_1)
        configureItem(binding.itemTable2, "Table #02", "550 GEL", R.drawable.table_2)
        configureItem(binding.itemTable3, "Table #03", "600 GEL", R.drawable.table_3)
        configureItem(binding.itemTable4, "Table #04", "850 GEL", R.drawable.table_4)

        // 2. სკამები (4 ცალი)
        configureItem(binding.itemChair1, "Chair #01", "150 GEL", R.drawable.chair_1)
        configureItem(binding.itemChair2, "Chair #02", "220 GEL", R.drawable.chair_2)
        configureItem(binding.itemChair3, "Chair #03", "300 GEL", R.drawable.chair_3)
        configureItem(binding.itemChair4, "Chair #04", "450 GEL", R.drawable.chair_4)

        // 3. საკიდები (4 ცალი)
        configureItem(binding.itemHanger1, "Hanger #01", "45 GEL", R.drawable.hanger_1)
        configureItem(binding.itemHanger2, "Hanger #02", "60 GEL", R.drawable.hanger_2)
        configureItem(binding.itemHanger3, "Hanger #03", "85 GEL", R.drawable.hanger_3)
        configureItem(binding.itemHanger4, "Hanger #04", "110 GEL", R.drawable.hanger_4)
    }

    // დამხმარე ფუნქცია, რომელიც ავტომატურად აწყობს თითოეულ ბარათს და ღილაკს
    private fun configureItem(
        itemBinding: com.example.myapplication.databinding.ItemWorkspaceCardBinding,
        name: String,
        price: String,
        imageRes: Int
    ) {
        // მონაცემების დასმა ვიზუალში
        itemBinding.itemName.text = name
        itemBinding.itemPrice.text = price
        itemBinding.itemImage.setImageResource(imageRes)

        // კალათაში დამატების ლოგიკა + ღილაკზე დაჭერისას
        itemBinding.btnAddToCart.setOnClickListener {
            val item = CartItem(name, price, imageRes)
            (activity as? MainActivity)?.cartList?.add(item)
            Toast.makeText(context, "$name დაემატა კალათაში!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}