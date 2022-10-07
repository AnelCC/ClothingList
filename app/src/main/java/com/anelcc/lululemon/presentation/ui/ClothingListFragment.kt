package com.anelcc.lululemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.anelcc.lululemon.R
import com.anelcc.lululemon.data.ClothingSortType
import com.anelcc.lululemon.databinding.FragmentClothingListBinding
import com.anelcc.lululemon.domain.Clothing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClothingListFragment : Fragment() {
    private val viewModel: ClothingListViewModel by viewModels()
    private lateinit var  clothingRecycleAdapter: ClothingRecycleAdapter
    private var _binding: FragmentClothingListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClothingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.clothingListRecycleView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            clothingRecycleAdapter = ClothingRecycleAdapter(emptyList())
            adapter = clothingRecycleAdapter
        }

        binding.rbAlpha.setOnClickListener { onRadioButtonClicked(it) }
        binding.rbTime.setOnClickListener { onRadioButtonClicked(it) }

        binding.addClothes.setOnClickListener { addClothes() }

        viewModel.clothingList.observe(viewLifecycleOwner) {
            updateInfo(it)
        }

        viewModel.fetchData()
    }


    private fun addClothes() {
        AddItemDialogFragment.newInstance(onPositiveClick = {
          onInsert(it)
        }).show(childFragmentManager, AddItemDialogFragment.TAG)
    }

    private fun onInsert(clothesName: String) {
        val tsLong = System.currentTimeMillis() / 1000
        val timestamp = tsLong.toString()
        val aux = Clothing(clothesName, timestamp)
        binding.rbAlpha.isChecked = true
        viewModel.insertClothes(aux)
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.rbAlpha ->
                    if (checked) { viewModel.sortByType(ClothingSortType.ALPHA) }
                R.id.rbTime ->
                    if (checked) { viewModel.sortByType(ClothingSortType.TIME) }
            }
        }
    }

    private fun updateInfo(clothingList: List<Clothing>) {
        if (clothingList.isEmpty()) {
            displayMessage("Sorry we did not find info, please try again")
        }
        clothingRecycleAdapter.setData(clothingList)
        binding.clothingListRecycleView.adapter?.notifyDataSetChanged()
    }

    private fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}