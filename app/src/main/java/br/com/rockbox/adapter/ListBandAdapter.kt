package br.com.rockbox.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rockbox.R
import br.com.rockbox.model.Band

class ListBandAdapter (val bands: List<Band>, val context: Context) : RecyclerView.Adapter<ListBandAdapter.ViewHolder>() {

    private var inflater:LayoutInflater? = null

    init{
        inflater = LayoutInflater.from(context)
    }


    override fun getItemCount(): Int {
        return bands.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
       val view: View = inflater!!.inflate(R.layout.band_item_row, parent, false)
        val holder = ViewHolder(view)
        return holder
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {






        override fun onClick(p0: View?) {
            throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }



}