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
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout.GRID
import com.example.dogglers.data.DataSource.dogs

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // Initialize the dog list from DataSource
    private val lisOfDogs = dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // Declare and initialize all of the list item UI components
        val imageView: ImageView? = view!!.findViewById(R.id.dog_image)
        val nameTextView: TextView? = view!!.findViewById(R.id.dog_name)
        val ageTextView: TextView? = view!!.findViewById(R.id.dog_age)
        val hobbiesTextView: TextView? = view!!.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        //  If the layout variable is Layout.GRID the grid list item should be used.
        //  Otherwise the vertical/horizontal list item should be used.
        val adapterLayout = when(layout) {
            GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)                 //for "GRID"
            else -> LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false)  //for "HORIZONTAL" or "VERTICAL"
        }
        // pass adapterLayout(inflated) into the DogCardViewHolder and return
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = lisOfDogs.size // return the size of the list

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dog = lisOfDogs[position]                               // Get the data at the current position
        holder.imageView?.setImageResource(dog.imageResourceId)     // Set the image resource for the current dog
        holder.nameTextView?.text = dog.name                        // Set the text for the current dog's name

        val resources = context?.resources
        holder.ageTextView?.text = resources?.getString(R.string.dog_age, dog.age)              // Set the text for the current dog's age
        holder.hobbiesTextView?.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)  // Set the text for the current dog's hobbies
    }
}
