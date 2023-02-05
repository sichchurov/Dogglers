/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.data.DataSource
import com.example.dogglers.databinding.VerticalHorizontalListItemBinding

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    private val data = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

        private val viewer = VerticalHorizontalListItemBinding.bind(view!!)

        val dogImage = viewer.imDog
        val dogName = viewer.tvDogName
        val dogAge = viewer.tvDogAge
        val dogHobbies = viewer.tvDogHobby
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        val adapterLayout = LayoutInflater
            .from(parent.context)

        return when (layout) {
            1, 2 -> DogCardViewHolder(
                adapterLayout.inflate(
                    R.layout.vertical_horizontal_list_item,
                    parent,
                    false
                )

            )
            else -> DogCardViewHolder(
                adapterLayout.inflate(
                    R.layout.grid_list_item,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = data.size // TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = data[position]
        val resources = context?.resources

        holder.apply {
            dogImage.setImageResource(item.imageResourceId)
            dogName.text = item.name
            dogAge.text = resources?.getString(R.string.dog_age, item.age)
            dogHobbies.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
        }
    }
}
