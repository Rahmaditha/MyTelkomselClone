package id.cookiss.mytelkomselclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import id.cookiss.mytelkomselclone.R
import id.cookiss.mytelkomselclone.data.paket
import java.text.NumberFormat
import java.util.*

class TopRekomendasiAdapter(

    private var context: Context,
    private val itemClickListener: OnItemClickListener


) : RecyclerView.Adapter<TopRekomendasiAdapter.ViewHolder>() {

    private val data: MutableList<paket> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tv_paket: TextView = itemView.findViewById(R.id.tv_paket)
        val tv_kuota: TextView = itemView.findViewById(R.id.tv_kuota)
        val tv_harga: TextView = itemView.findViewById(R.id.tv_harga)
        val tv_durasi: TextView = itemView.findViewById(R.id.tv_durasi)
        val cl_recommended: ConstraintLayout = itemView.findViewById(R.id.cl_recommended)


        fun bind(paketRekomendasi: paket, itemClickListener: OnItemClickListener)
        {
            val myIndonesianLocale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(myIndonesianLocale)
            numberFormat.maximumFractionDigits = 0

//            if(!paketRekomendasi.images.isNullOrEmpty()){
//                val bitmap = ImageUtils.decodeBase46ToBitmap(paketRekomendasi.images[0].image)
//
//                if(bitmap != null){
//                    picture_katalog.setImageBitmap(bitmap)
//                }
//            }

            cl_recommended.setOnClickListener {
                itemClickListener.onItemClicked(itemView, absoluteAdapterPosition)
            }

            tv_paket.text = paketRekomendasi.nama_paket
            tv_kuota.text = "${paketRekomendasi.kuota} GB"
            tv_harga.text = "${numberFormat.format(paketRekomendasi.harga)}"
            tv_durasi.text = "${paketRekomendasi.durasi} Hari"

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRekomendasiAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recommended, parent, false)
        return ViewHolder(view)
    }

    fun setData(newListData: List<paket>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getId(position: Int) : Int{
        return data[position].id
    }

    fun getNamaProduk(position: Int) : String{
        return data[position].nama_paket
    }

    fun getHargaJual(position: Int) : Int{
        return data[position].harga
    }

    fun removeData(){
        data.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: TopRekomendasiAdapter.ViewHolder, position: Int) {
        val topRekomendasi = data.get(position)
        holder.bind(topRekomendasi,itemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener{
        fun onItemClicked(v: View, position: Int)
    }
}