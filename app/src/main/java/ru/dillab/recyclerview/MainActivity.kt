package ru.dillab.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSet = DataSource().loadStrings()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = ItemAdapter(this, dataSet)

        recyclerView.setHasFixedSize(true)
    }
}

data class Item(val stringResourceId: Int)

class DataSource {

    fun loadStrings(): List<Item> {
        return listOf(
            Item(R.string.string_1),
            Item(R.string.string_2),
            Item(R.string.string_3),
            Item(R.string.string_4),
            Item(R.string.string_5),
            Item(R.string.string_6),
            Item(R.string.string_7),
            Item(R.string.string_8),
            Item(R.string.string_9),
            Item(R.string.string_10)
        )
    }
}

class ItemAdapter(
    private val context: Context,
    private val data: List<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_layout_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount() = data.size
}