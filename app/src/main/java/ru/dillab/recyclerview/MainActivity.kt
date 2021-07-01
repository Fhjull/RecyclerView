package ru.dillab.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize data.
        val dataSet = DataSource().loadStrings()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = ItemAdapter(this, dataSet)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}

data class Item(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
    )

class DataSource {

    fun loadStrings(): List<Item> {
        return listOf(
            Item(R.string.string_1, R.drawable.image1),
            Item(R.string.string_2, R.drawable.image2),
            Item(R.string.string_3, R.drawable.image3),
            Item(R.string.string_4, R.drawable.image4),
            Item(R.string.string_5, R.drawable.image5),
            Item(R.string.string_6, R.drawable.image6),
            Item(R.string.string_7, R.drawable.image7),
            Item(R.string.string_8, R.drawable.image8),
            Item(R.string.string_9, R.drawable.image9),
            Item(R.string.string_10, R.drawable.image10)
        )
    }
}

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Item] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val data: List<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     *  Provide a reference to the views for each data item
     *  Complex data items may need more than one view per item, and
     *  you provide access to all the views for a data item in a view holder.
     *  Each data item is just an [Item] object.
     */
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_layout_id)
        val imageView: ImageView = view.findViewById(R.id.image_layout_id)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = data.size
}