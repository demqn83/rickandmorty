package com.example.rickandmorty.presentation.listepisodes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R

class EpisodesAdapter(private val retry: () -> Unit) :
    RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder>() {

    private val episodeUI = mutableListOf<EpisodeUI>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(new: List<EpisodeUI>) {
        episodeUI.clear()
        episodeUI.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (episodeUI[position]) {
        is EpisodeUI.Progress -> 1
        is EpisodeUI.Success -> 2
        is EpisodeUI.Fail -> 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder =
        when (viewType) {
            1 -> EpisodesViewHolder.Progress(
                LayoutInflater.from(parent.context).inflate(R.layout.progress, parent, false)
            )
            2 -> EpisodesViewHolder.Success(
                LayoutInflater.from(parent.context).inflate(R.layout.episode, parent, false)
            )
            else -> EpisodesViewHolder.Fail(
                LayoutInflater.from(parent.context).inflate(R.layout.fail, parent, false),
                retry
            )

        }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) =
        holder.bind(episodeUI[position])

    override fun getItemCount() = episodeUI.size

    abstract class EpisodesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(episodeUI: EpisodeUI) {
        }

        class Progress(view: View) : EpisodesViewHolder(view)

        class Success(view: View) :
            EpisodesViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.text_view_name_episode)
            private val airDate = itemView.findViewById<TextView>(R.id.text_view_air_date)
            override fun bind(episodeUI: EpisodeUI) {
                name.text = (episodeUI as EpisodeUI.Success).name
                airDate.text = episodeUI.air_date
            }
        }

        class Fail(view: View, private val retry: () -> Unit) : EpisodesViewHolder(view) {
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(episodeUI: EpisodeUI) {
                button.setOnClickListener {
                    retry()
                }
            }
        }
    }
}