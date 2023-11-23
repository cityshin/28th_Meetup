package com.example.meetup.adapter

import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meetup.R
import com.example.meetup.databinding.RowCategoryBinding
import com.example.meetup.databinding.RowSetBinding
import com.example.meetup.databinding.RowTop10Binding
import com.example.meetup.fragment.HomeCategoryFragment
import com.example.meetup.fragment.MenuFragment
import com.example.meetup.model.Food
import com.example.meetup.model.RecentSetFoodList
import com.example.meetup.sharedPreference.MyApplication
import com.example.meetup.viewmodel.FoodMenuDetailViewModel

class HomeSetAdapter(var manager: FragmentManager, var activity: ViewModelStoreOwner, var foodList: List<RecentSetFoodList>) :
    RecyclerView.Adapter<HomeSetAdapter.HomeSetViewHolder>() {
    private var onItemClickListener: ((Int) -> Unit)? = null
    private var context: Context? = null

    lateinit var viewModel: FoodMenuDetailViewModel

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSetViewHolder {
        context = parent.context
        val binding =
            RowSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        viewModel = ViewModelProvider(activity)[FoodMenuDetailViewModel::class.java]

        binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return HomeSetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeSetViewHolder, position: Int) {
        Glide.with(context!!).load(foodList.get(position).image).into(holder.image)
        holder.storeName.text = "${foodList.get(position).storeName}"
        holder.review.text = "⭐️ ${foodList.get(position).avgRate}"
        holder.foodName.text = "${foodList.get(position).name}"
        if(foodList.get(position).dollarPrice.toInt() == 0) {
            holder.price.text = "${foodList.get(position).canadaPrice}"
        }
        else {
            holder.price.text = "$ ${foodList.get(position).dollarPrice}"
        }
    }

    override fun getItemCount() = foodList.size

    inner class HomeSetViewHolder(val binding: RowSetBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageview
        val storeName = binding.textviewStoreName
        val review = binding.textviewReview
        val foodName = binding.textviewFoodName
        val price = binding.textviewPrice

        init {
            binding.root.setOnClickListener {

                MyApplication.foodId = foodList.get(adapterPosition).foodId.toInt()

                viewModel.getFoodMenuInfo(context!!, manager, MyApplication.foodId)

                true
            }
        }
    }
}