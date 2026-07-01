package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.databinding.FragmentCheckoutBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)

        val mainActivity = (activity as? MainActivity)
        val cartList = mainActivity?.cartList ?: arrayListOf()

        // 🟢 თანხის დათვლის ლოგიკა
        var totalSum = 0
        for (item in cartList) {
            val priceOnly = item.price.replace(" GEL", "").toIntOrNull() ?: 0
            totalSum += priceOnly
        }

        binding.txtTotalAmount.text = "ჯამი: $totalSum GEL"
        binding.txtItemCount.text = "ნივთების რაოდენობა: ${cartList.size}"

        // 🔴 შეკვეთის დადასტურების ღილაკი
        binding.btnConfirmOrder.setOnClickListener {
            Toast.makeText(context, "შეკვეთა მიღებულია! გმადლობთ.", Toast.LENGTH_LONG).show()

            // 1. ვასუფთავებთ კალათას
            mainActivity?.cartList?.clear()

            // 2. ქვედა მენიუში ავტომატურად გადავრთავთ პირველ (Home) გვერდზე
            val windowDecor = mainActivity?.window?.decorView
            val bottomNav = windowDecor?.let { findBottomNav(it) }
            bottomNav?.let { nav ->
                if (nav.menu.size() > 0) {
                    nav.selectedItemId = nav.menu.getItem(0).itemId
                }
            }

            // 3. 🟢 ვთიშავთ კალათას და ჩეკაუტს ერთად (ვასუფთავებთ ფრაგმენტების მთელ ისტორიას Home-მდე)
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            // 4. თუ ისტორიის გასუფთავების მერე ეკრანი ცარიელი დარჩა, თავიდან ვტვირთავთ HomeFragment-ს
            if (parentFragmentManager.fragments.isEmpty() || parentFragmentManager.fragments.last() is CheckoutFragment) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }

        return binding.root
    }

    // დამხმარე ფუნქცია BottomNavigationView-ს საპოვნელად
    private fun findBottomNav(view: View): BottomNavigationView? {
        if (view is BottomNavigationView) return view
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                val result = findBottomNav(child)
                if (result != null) return result
            }
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}