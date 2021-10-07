package com.giorgosneokleous.fullscreenintentexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jetbrains.anko.find
import org.w3c.dom.Text

class Adapter(private var team1: List<String> , private var team2: List<String>  , private var images1:List<String> , private var images2:List<String> , private var time: List<String> , private var Bollingteam : List<String>  , private var Battingteam : List<String> , private var Status : List<String>  , private var score1 : List<String> , private var score2 : List<String> , private var  runrate : List<String>  , private var reqrunrate : List<String> , private var TScore1 : List<String> , private var TScore2 :List<String>) : RecyclerView.Adapter<Adapter.Page2ViewHolder>() {

    inner class Page2ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val itemTeam1: TextView = itemView.findViewById(R.id.textView4)
        val itemTeam2: TextView = itemView.findViewById(R.id.textView8)

        val timetext: TextView = itemView.findViewById(R.id.textView10)
        val team1Image: ImageView = itemView.findViewById(R.id.imageView2)
        val team2Image: ImageView = itemView.findViewById(R.id.imageView3)

        val bolling: TextView = itemView.findViewById(R.id.textView28)
        val batting: TextView = itemView.findViewById(R.id.textView30)
        val MatchStatus : TextView = itemView.findViewById(R.id.textView16)
        val Scoreteam1 : TextView = itemView.findViewById(R.id.textView12)
        val Scoreteam2 : TextView = itemView.findViewById(R.id.textView13)

        val runrateVlue : TextView = itemView.findViewById(R.id.textView25)
        val reqrunrateVlue : TextView = itemView.findViewById(R.id.textView26)
        val Total1 : TextView = itemView.findViewById(R.id.textView17)
        val Total2 : TextView = itemView.findViewById(R.id.textView22)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.Page2ViewHolder {
        return Page2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pages,parent, false))
    }

    override fun onBindViewHolder(holder: Adapter.Page2ViewHolder, position: Int) {
          holder.itemTeam1.text=team1[position]
          holder.itemTeam2.text=team2[position]
           holder.timetext.text=time[position]
           holder.batting.text=Battingteam[position]
          holder.bolling.text= Bollingteam[position]
          holder.MatchStatus.text = Status[position]
        holder.Scoreteam1.text = score1[position]
        holder.Scoreteam2.text = score2[position]
        holder.runrateVlue.text = runrate[position]
                holder.reqrunrateVlue.text = reqrunrate[position]

        holder.Total1.text= TScore1[position]
        holder.Total2.text= TScore2[position]


        Glide.with(holder.itemView.getContext())
            .load(images1[position])
            .into(holder.team2Image)


        Glide.with(holder.itemView.getContext())
            .load(images2[position])
            .into(holder.team1Image)


    }



    override fun getItemCount(): Int {
        return team1.size
    }


}