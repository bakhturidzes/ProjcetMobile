package com.example.myapplication

import android.graphics.Color
import android.widget.Toast
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.example.myapplication.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        loadCartItems() // შენი ძველი ფუნქცია

        // 🟢 "ყველას ყიდვა" ღილაკზე კლიკი
        binding.btnBuyAll.setOnClickListener {
            val cartList = (activity as? MainActivity)?.cartList ?: arrayListOf()

            if (cartList.isEmpty()) {
                Toast.makeText(context, "კალათა ცარიელია!", Toast.LENGTH_SHORT).show()
            } else {
                // გადავდივართ ჩეკაუტზე
                (activity as? MainActivity)?.replaceFragment(CheckoutFragment(), "checkout")
            }
        }

        return binding.root
    }

    private fun loadCartItems() {
        binding.cartItemsContainer.removeAllViews()

        val mainActivity = activity as? MainActivity
        val cartList = mainActivity?.cartList ?: arrayListOf()

        if (cartList.isEmpty()) {
            val emptyTxt = TextView(context).apply {
                text = "კალათა ცარიელია :("
                textSize = 18f
                setTextColor(Color.parseColor("#757575"))
                setPadding(40, 40, 40, 40)
            }
            binding.cartItemsContainer.addView(emptyTxt)
            return
        }

        for (i in 0 until cartList.size) {
            val item = cartList[i]

            val cardView = MaterialCardView(requireContext()).apply {
                id = View.generateViewId()
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(24, 16, 24, 16)
                }
                cardElevation = 4f
                radius = 24f
                setCardBackgroundColor(Color.WHITE)
                strokeWidth = 0
            }

            val itemLayout = ConstraintLayout(requireContext()).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(24, 24, 24, 24)
            }

            val imageCard = MaterialCardView(requireContext()).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(200, 200)
                radius = 16f
                setCardBackgroundColor(Color.parseColor("#F5F5F5"))
                strokeWidth = 0
            }

            val imgView = ImageView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setImageResource(item.imageResId)
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
            imageCard.addView(imgView)

            val nameTxt = TextView(context).apply {
                id = View.generateViewId()
                text = item.name
                textSize = 18f
                setTypeface(null, Typeface.BOLD)
                setTextColor(Color.parseColor("#1A1A1A"))
                maxLines = 2
            }

            val priceTxt = TextView(context).apply {
                id = View.generateViewId()
                text = item.price
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(Color.parseColor("#0057FF"))
            }

            // 🟢 გასწორდა: ნაგვის ყუთის ზომა გაიზარდა 140x140-მდე და აიქონი გახდა დიდი
            val deleteBtn = ImageButton(context).apply {
                id = View.generateViewId()
                setImageResource(R.drawable.ic_delete)
                setBackgroundColor(Color.TRANSPARENT)
                scaleType = ImageView.ScaleType.FIT_CENTER

                // ფონის მოჭრისა და წითლად შეღებვის სწორი რეჟიმი
                setColorFilter(Color.parseColor("#D32F2F"), PorterDuff.Mode.SRC_IN)

                layoutParams = ConstraintLayout.LayoutParams(140, 140)
                setPadding(0, 0, 0, 0) // მოვაცილეთ შიდა დაშორება, რომ მაქსიმალურად დიდი გამოჩნდეს

                setOnClickListener {
                    mainActivity?.cartList?.removeAt(i)
                    loadCartItems()
                }
            }

            itemLayout.addView(imageCard)
            itemLayout.addView(nameTxt)
            itemLayout.addView(priceTxt)
            itemLayout.addView(deleteBtn)

            val set = androidx.constraintlayout.widget.ConstraintSet()
            set.clone(itemLayout)

            set.connect(imageCard.id, androidx.constraintlayout.widget.ConstraintSet.START, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.START)
            set.connect(imageCard.id, androidx.constraintlayout.widget.ConstraintSet.TOP, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.TOP)
            set.connect(imageCard.id, androidx.constraintlayout.widget.ConstraintSet.BOTTOM, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.BOTTOM)

            set.connect(nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.START, imageCard.id, androidx.constraintlayout.widget.ConstraintSet.END, 24)
            set.connect(nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.TOP, imageCard.id, androidx.constraintlayout.widget.ConstraintSet.TOP, 4)
            set.connect(nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.END, deleteBtn.id, androidx.constraintlayout.widget.ConstraintSet.START, 16)
            set.constrainWidth(nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.MATCH_CONSTRAINT)

            set.connect(priceTxt.id, androidx.constraintlayout.widget.ConstraintSet.START, nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.START)
            set.connect(priceTxt.id, androidx.constraintlayout.widget.ConstraintSet.TOP, nameTxt.id, androidx.constraintlayout.widget.ConstraintSet.BOTTOM, 8)

            set.connect(deleteBtn.id, androidx.constraintlayout.widget.ConstraintSet.END, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.END)
            set.connect(deleteBtn.id, androidx.constraintlayout.widget.ConstraintSet.TOP, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.TOP)
            set.connect(deleteBtn.id, androidx.constraintlayout.widget.ConstraintSet.BOTTOM, androidx.constraintlayout.widget.ConstraintSet.PARENT_ID, androidx.constraintlayout.widget.ConstraintSet.BOTTOM)

            set.applyTo(itemLayout)
            cardView.addView(itemLayout)
            binding.cartItemsContainer.addView(cardView)
        }
    }
}