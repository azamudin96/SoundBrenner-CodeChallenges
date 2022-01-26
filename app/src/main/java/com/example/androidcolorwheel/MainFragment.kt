package com.example.androidcolorwheel

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidcolorwheel.databinding.MainFragmentBinding
import com.github.antonpopoff.colorwheel.ColorWheel

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var binding: MainFragmentBinding
    private var color1:Int = Color.parseColor("#00c2a3")
    private var color2:Int = Color.parseColor("#4ba54f")
    private var color3:Int = Color.parseColor("#ff6100")


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.colorWheel.colorChangeListener = { rgb: Int ->
            binding.tvRgb.text = "Selected RGB : $rgb"

            when {
                binding.card1.isActivated -> {
                    binding.view1.backgroundTintList = ColorStateList.valueOf(rgb)
                    color1 = rgb
                }
                binding.card2.isActivated -> {
                    binding.view2.backgroundTintList = ColorStateList.valueOf(rgb)
                    color2 = rgb
                }
                binding.card3.isActivated -> {
                    binding.view3.backgroundTintList = ColorStateList.valueOf(rgb)
                    color3 = rgb
                }
            }

        }

        binding.card1.setOnClickListener(this)

        binding.card2.setOnClickListener(this)

        binding.card3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.card1.id -> {
                binding.card1.isActivated = !binding.card1.isActivated
                binding.card2.isActivated = false
                binding.card3.isActivated = false

                setColor(color1)
            }
            binding.card2.id -> {
                binding.card2.isActivated = !binding.card2.isActivated
                binding.card1.isActivated = false
                binding.card3.isActivated = false

                setColor(color2)
            }
            binding.card3.id -> {
                binding.card3.isActivated = !binding.card3.isActivated
                binding.card2.isActivated = false
                binding.card1.isActivated = false

                setColor(color3)
            }
        }
    }

    private fun setColor(int: Int) {
        val r: Int = int shr 16 and 0xFF
        val g: Int = int shr 8 and 0xFF
        val b: Int = int and 0xFF

        binding.colorWheel.setRgb(r,g,b)
    }
}