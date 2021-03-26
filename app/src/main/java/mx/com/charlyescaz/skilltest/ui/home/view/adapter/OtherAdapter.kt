package mx.com.charlyescaz.skilltest.ui.home.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.charlyescaz.skilltest.R
import mx.com.charlyescaz.skilltest.databinding.ItemTestBinding
import mx.com.charlyescaz.skilltest.models.Test

class OtherAdapter(private val tests: List<Test>,
                   private val onClick: (Test) -> Unit):
    RecyclerView.Adapter<OtherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        )

    override fun getItemCount(): Int = tests.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val test = tests[position]
        holder.vBind.test = test
        holder.vBind.tvCoordinates.text = "${test.coord?.lat} , ${test.coord?.lon}"
        holder.vBind.tvTemp.text = "${test.main?.temp}°"
        holder.vBind.cvItem.setOnClickListener { onClick(test) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vBind = ItemTestBinding.bind(itemView)
    }
}