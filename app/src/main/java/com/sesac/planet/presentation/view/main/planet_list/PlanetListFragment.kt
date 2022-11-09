package com.sesac.planet.presentation.view.main.planet_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sesac.planet.R
import com.sesac.planet.databinding.FragmentPlanetListBinding
import com.sesac.planet.presentation.view.main.planet_list.adapter.PlanetListAdapter
import com.sesac.planet.presentation.viewmodel.main.PlanetInfoViewModel
import com.sesac.planet.presentation.viewmodel.main.PlanetViewModelFactory
import com.sesac.planet.utility.SystemUtility

class PlanetListFragment : Fragment() {
    private var _binding: FragmentPlanetListBinding? = null
    private val binding get() = _binding!!
    private lateinit var planetListAdapter: PlanetListAdapter

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            PlanetViewModelFactory()
        )[PlanetInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        initView()
    }

    private fun initialize() {
        SystemUtility.applyWindowInsetsTopPadding(binding.root)
    }

    private fun initView(){
        initPlanetListRecyclerView()
        binding.planetListMenuOptionBtn.setOnClickListener {
            val intent = Intent(requireContext(), CreatePlanetActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initPlanetListRecyclerView() {
        initObservers()
        viewModel.getPlanet(
            "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NjY1OTQwOTcsImV4cCI6MTY2ODA2NTMyNn0.Ro1EyIxo44NIi1Jos7ssbCvkDdlSWhYPIBaMfabY7QQ",
            4
        )
    }

    private fun initObservers() {
        viewModel.planetData.observe(viewLifecycleOwner) { response ->
            if(response.isSuccessful){
                response.body()?.result.let { body ->
                    if (body == null) {
                        //연결은 됐지만 값이 없을 때
                        Toast.makeText(requireContext(), "연결은 됐지만 값이 없습니다.", Toast.LENGTH_LONG).show()
                    } else {
                        planetListAdapter = PlanetListAdapter(body)
                        binding.planetListRecyclerView.layoutManager =
                            GridLayoutManager(requireContext(), 2)
                        binding.planetListRecyclerView.adapter = planetListAdapter

                        planetListAdapter.setItemClickListener(
                            object : PlanetListAdapter.OnItemClickListener {
                                override fun onClick(v: View, position: Int) {
                                    val intent =
                                        Intent(requireContext(), PlanetDetailActivity::class.java)
                                    intent.putExtra("planet_id", body?.get(position)?.planet_id)
                                    startActivity(intent)
                                }
                            })
                    }
                }
            } else{
                //서버에 문제가 생겼을 때
                Toast.makeText(requireContext(), "서버 문제가 생겼습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}