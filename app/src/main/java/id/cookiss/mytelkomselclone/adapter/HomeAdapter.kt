package id.cookiss.mytelkomselclone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.cookiss.mytelkomselclone.R
import id.cookiss.mytelkomselclone.data.HomeListData
import id.cookiss.mytelkomselclone.data.paket
import java.text.NumberFormat
import java.util.*

class HomeAdapter(

    private var mcontext: Context,
    private val itemClickListener: OnItemClickListener


) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val data: MutableList<HomeListData> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val iv_banner: ImageView = itemView.findViewById(R.id.iv_banner)
        val tv_title: TextView = itemView.findViewById(R.id.tv_title)
        val cl_home_banner: ConstraintLayout = itemView.findViewById(R.id.cl_home_banner)


        fun bind(mcontext: Context, homeList: HomeListData, itemClickListener: OnItemClickListener)
        {
            val myIndonesianLocale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(myIndonesianLocale)
            numberFormat.maximumFractionDigits = 0

//            if(!homeList.images.isNullOrEmpty()){
//                val bitmap = ImageUtils.decodeBase46ToBitmap(homeList.images[0].image)
//
//                if(bitmap != null){
//                    picture_katalog.setImageBitmap(bitmap)
//                }
//            }

            cl_home_banner.setOnClickListener {
                itemClickListener.onItemHomeClicked(itemView, absoluteAdapterPosition)
            }

            Glide.with(mcontext)
                .load(R.drawable.gambar_telkom1)
                .into(iv_banner)

            tv_title.text = homeList.nama_paket

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_home, parent, false)
        return ViewHolder(view)
    }

    fun setData(newListData: List<HomeListData>?) {
        if (newListData == null) return
        data.clear()
        data.addAll(newListData)
        notifyDataSetChanged()
    }

    fun getNamaProduk(position: Int) : String{
        return data[position].nama_paket
    }


    fun removeData(){
        data.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val home = data.get(position)
        holder.bind(mcontext,home,itemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener{
        fun onItemHomeClicked(v: View, position: Int)
    }
}