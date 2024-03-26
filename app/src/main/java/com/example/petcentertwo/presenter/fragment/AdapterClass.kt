package com.example.petcentertwo.presenter.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.petcentertwo.R
import com.example.petcentertwo.presenter.data.db.entity.RegisterPetEntity

class AdapterClass(
    private val dataList: List<RegisterPetEntity>,
    private val listener: RecyclerViewEvent
) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val rvNome: TextView = itemView.findViewById(R.id.tvItemName)
        val rvType: TextView = itemView.findViewById(R.id.tvItemTipo)
        val rvBreed: TextView = itemView.findViewById(R.id.tvItemRaca)
        val rvDateOfBrith: TextView = itemView.findViewById(R.id.tvItemDataNascimento)
        val tvRemove: TextView = itemView.findViewById(R.id.tvRemove)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_register_pet, parent, false)
        return ViewHolderClass(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        holder.apply {
            rvNome.text = currentItem.name
            rvType.text = currentItem.type
            rvBreed.text = currentItem.breed
            rvDateOfBrith.text = currentItem.dateOfBrith

            tvRemove.setOnClickListener {


            }
        }

    }
    override fun getItemCount(): Int {
      return dataList.size
    }

    interface RecyclerViewEvent{
        fun onItemClick(position: Int)
    }

}