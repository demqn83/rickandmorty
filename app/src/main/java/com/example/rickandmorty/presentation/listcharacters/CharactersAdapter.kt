package com.example.rickandmorty.presentation.listcharacters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

class CharactersAdapter(private val retry: () -> Unit, private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private val characters = mutableListOf<CharacterUI>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(new: List<CharacterUI>) {
        characters.clear()
        characters.addAll(new)
        this.notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (characters[position]) {
        is CharacterUI.Progress -> 1
        is CharacterUI.Success -> 2
        is CharacterUI.Fail -> 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        when (viewType) {
            1 -> CharactersViewHolder.Progress(
                LayoutInflater.from(parent.context).inflate(R.layout.progress, parent, false)
            )
            2 -> CharactersViewHolder.Success(
                LayoutInflater.from(parent.context).inflate(R.layout.characters, parent, false),
                listener
            )
            else -> CharactersViewHolder.Fail(
                LayoutInflater.from(parent.context).inflate(R.layout.fail, parent, false),
                retry
            )

        }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) =
        holder.bind(characters[position])

    override fun getItemCount() = characters.size

    abstract class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(character: CharacterUI) {
        }

        class Progress(view: View) : CharactersViewHolder(view)

        class Success(view: View, private val listener: (Int) -> Unit) :
            CharactersViewHolder(view) {
            private val name = itemView.findViewById<TextView>(R.id.textView)
            private val photo = itemView.findViewById<ImageView>(R.id.photo)
            override fun bind(character: CharacterUI) {
                name.text = (character as CharacterUI.Success).name
                Glide
                    .with(itemView.context)
                    .load(character.photo)
                    .into(photo)
                itemView.setOnClickListener { listener(character.id) }
            }
        }

        class Fail(view: View, private val retry: () -> Unit) : CharactersViewHolder(view) {
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(character: CharacterUI) {
                button.setOnClickListener {
                    retry()
                }
            }
        }
    }
}