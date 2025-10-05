package com.agri.connect.ui.crop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.agri.connect.databinding.FragmentCropListBinding
import com.agri.connect.data.local.AppDatabase
import com.agri.connect.data.local.entities.CropGuide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CropListFragment : Fragment() {
    private var _binding: FragmentCropListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CropAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCropListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CropAdapter()
        binding.rvCrops.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCrops.adapter = adapter

        // load from DB
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getInstance(requireContext())
            if (db.cropDao().count() == 0) {
                // insert sample data
                val samples = listOf(
                    CropGuide(0, "Rice", "Planting: wet season. Fertilizer: NPK..."),
                    CropGuide(0, "Maize", "Well drained soil. Planting depth..."),
                    CropGuide(0, "Chili", "Require warm climate. Spacing...")
                )
                db.cropDao().insertAll(samples)
            }
            val crops = db.cropDao().getAll()
            withContext(Dispatchers.Main) {
                adapter.submitList(crops)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CropListFragment()
    }
}
