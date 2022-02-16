package id.cookiss.mytelkomselclone.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.cookiss.mytelkomselclone.adapter.HomeAdapter
import id.cookiss.mytelkomselclone.adapter.TopRekomendasiAdapter
import id.cookiss.mytelkomselclone.data.HomeListData
import id.cookiss.mytelkomselclone.databinding.FragmentHomeBinding
import id.cookiss.mytelkomselclone.data.paket

class HomeFragment : Fragment(), TopRekomendasiAdapter.OnItemClickListener, HomeAdapter.OnItemClickListener{

    private lateinit var navController: NavController
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var topRekomendasiAdapter: TopRekomendasiAdapter
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var rv_Rekomendasi: RecyclerView
    private lateinit var rv_spesial: RecyclerView
    private lateinit var rv_whats_new: RecyclerView
    private lateinit var rv_jelajah: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var rekomendasiList : MutableList<paket> = ArrayList()
    private var homeList : MutableList<HomeListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_Rekomendasi = binding.rvRekomendasi
        rv_spesial = binding.rvSpesial
        rv_whats_new = binding.rvWhatsNew
        rv_jelajah = binding.rvJelajah

        initList1KatalogRecyclerView()
        initList2KatalogRecyclerView()

        val rekomendasi1 = paket("Paket PROMO Combo Special", 30000, 1, 30, 15)
        val rekomendasi2 = paket("Paket Promo Internet dan Telpon", 15000, 2, 7, 5)
        val rekomendasi3 = paket("Combo Sakti Spesial", 25000, 3, 30, 5)
        val rekomendasi4 = paket("Combo Sakti Spesial", 20000, 4, 30, 10)
        val rekomendasi5 = paket("Combo SAKTI Mingguan UNLIMITED", 36000, 5, 7, 10)
        rekomendasiList.add(rekomendasi1)
        rekomendasiList.add(rekomendasi2)
        rekomendasiList.add(rekomendasi3)
        rekomendasiList.add(rekomendasi4)
        rekomendasiList.add(rekomendasi5)

        topRekomendasiAdapter.setData(rekomendasiList)

        val home1 = HomeListData("#SupriseDeal Nonton")
        val home2 = HomeListData("Combo SAKTI MAX")
        homeList.add(home1)
        homeList.add(home2)
        homeAdapter.setData(homeList)
    }

    private fun initList2KatalogRecyclerView() {
        homeAdapter = HomeAdapter(requireActivity(), this)
        linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rv_spesial.layoutManager = linearLayoutManager
        rv_spesial.adapter = homeAdapter
    }

    private fun initList1KatalogRecyclerView() {
        topRekomendasiAdapter = TopRekomendasiAdapter(requireActivity(), this)
        linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rv_Rekomendasi.layoutManager = linearLayoutManager
        rv_Rekomendasi.adapter = topRekomendasiAdapter
    }

    override fun onItemClicked(v: View, position: Int) {

    }

    override fun onItemHomeClicked(v: View, position: Int) {

    }
}