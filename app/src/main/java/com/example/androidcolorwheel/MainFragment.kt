package com.example.androidcolorwheel

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
        }

        binding.cardTeal.setOnClickListener(this)

        binding.cardGreen.setOnClickListener(this)

        binding.cardOrange.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.cardTeal.id -> {
                binding.cardTeal.isActivated = !binding.cardTeal.isActivated
                binding.cardGreen.isActivated = false
                binding.cardOrange.isActivated = false

                setColor(R.color.teal)
            }
            binding.cardGreen.id -> {
                binding.cardGreen.isActivated = !binding.cardGreen.isActivated
                binding.cardTeal.isActivated = false
                binding.cardOrange.isActivated = false

                setColor(R.color.green)
            }
            binding.cardOrange.id -> {
                binding.cardOrange.isActivated = !binding.cardOrange.isActivated
                binding.cardGreen.isActivated = false
                binding.cardTeal.isActivated = false

                setColor(R.color.orange)
            }
        }
    }

    private fun setColor(int: Int) {
        val color = resources.getColor(int)
        val r: Int = Color.red(color)
        val g: Int = Color.green(color)
        val b: Int = Color.blue(color)

        binding.colorWheel.setRgb(r,g,b)
    }
}