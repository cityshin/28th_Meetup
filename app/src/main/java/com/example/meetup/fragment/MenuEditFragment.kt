package com.example.meetup.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meetup.R
import com.example.meetup.adapter.MenuListAdapter
import com.example.meetup.databinding.FragmentMenuEditBinding
import com.example.meetup.databinding.FragmentStoreDetailBinding
import com.example.meetup.model.MenuListResponseModel


class MenuEditFragment : Fragment() {

    private var _binding: FragmentMenuEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuListAdapter: MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuEditBinding.inflate(inflater,container,false)
        val view = binding.root


        var menulist = ArrayList<MenuListResponseModel>()

        menulist.add(MenuListResponseModel("a","aaa","aa","1,000"))
        menulist.add(MenuListResponseModel("a","bbb","bbb","2,000"))
        menulist.add(MenuListResponseModel("a","cc","cc","3,000"))


//        menuListAdapter = MenuListAdapter(menulist)

        menuListAdapter = MenuListAdapter(menulist)

        binding.recyclerviewMenuList.adapter = menuListAdapter

binding.recyclerviewMenuList.layoutManager = LinearLayoutManager(requireContext())


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}