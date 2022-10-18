package com.example.weatherbrkic.ui.locations.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherbrkic.data.model.Location


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class LocationsAdapter(private val mContext: Context, val locations: ArrayList<Location>) : ArrayAdapter<Location>(mContext,android.R.layout.simple_dropdown_item_1line,locations) {
    private val allLocations: MutableList<Location> = ArrayList(locations)

    override fun getCount(): Int {
        return allLocations.size
    }
    override fun getItem(position: Int): Location {
        return allLocations[position]
    }
    override fun getItemId(position: Int): Long {
        return allLocations[position].Rank.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val location: Location = getItem(position)
        var mConvertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            mConvertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        }
        val tvName = mConvertView?.findViewById<View>(android.R.id.text1) as TextView
        // Populate the data into the template view using the data object
        tvName.text = location.LocalizedName + ", "+ location.Country.LocalizedName
        return mConvertView
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun convertResultToString(resultValue: Any) :String {
                return (resultValue as Location).LocalizedName
            }
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    val locationSuggestion: MutableList<Location> = ArrayList()
                    for (location in locations) {
                        if (location.LocalizedName.lowercase().startsWith(constraint.toString().lowercase())
                        ) {
                            locationSuggestion.add(location)
                        }
                    }
                    filterResults.values = locationSuggestion
                    filterResults.count = locationSuggestion.size
                }
                return filterResults
            }
            override fun publishResults(
                constraint: CharSequence?,
                results: FilterResults
            ) {
                allLocations.clear()
                if (results.count > 0) {
                    for (result in results.values as List<*>) {
                        if (result is Location) {
                            allLocations.add(result)
                        }
                    }
                    notifyDataSetChanged()
                } else if (constraint == null) {
                    allLocations.addAll(locations)
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}
