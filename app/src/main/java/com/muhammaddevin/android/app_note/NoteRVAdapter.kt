package com.muhammaddevin.android.app_note

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    // pada baris di bawah ini kita membuat a
    // variabel untuk semua daftar catatan kita.
    private val allNotes = ArrayList<Note>()

    // pada baris di bawah ini kita membuat kelas view holder.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // pada baris di bawah ini kita membuat inisialisasi semua
        // variabel yang telah kita tambahkan di file layout.
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // mengembang file tata letak untuk setiap item recyclerview.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // pada baris di bawah ini kita menyetel data ke item tampilan pendaur ulang.
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Last Updated : " + allNotes.get(position).timeStamp)
        // pada baris di bawah ini kami menambahkan pendengar klik ke ikon tampilan gambar hapus kami.
        holder.deleteIV.setOnClickListener {
            // pada baris di bawah ini kita memanggil note click
            // antarmuka dan kami memberikan posisi ke sana.
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        // pada baris di bawah ini kami menambahkan pendengar klik
        // ke item tampilan pendaur ulang kami.
        holder.itemView.setOnClickListener {
            // pada baris di bawah ini kita memanggil antarmuka klik catatan
            // dan kami memberikan posisi ke sana.
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        // pada baris di bawah kita adalah
        // mengembalikan ukuran daftar kita.
        return allNotes.size
    }

    // metode di bawah ini digunakan untuk memperbarui daftar catatan kami.
    fun updateList(newList: List<Note>) {
        // pada baris di bawah ini kita hapus
        // daftar larik catatan kami
        allNotes.clear()
        // pada baris di bawah ini kita menambahkan a
        // daftar baru ke daftar semua catatan kami.v
        allNotes.addAll(newList)
        // pada baris di bawah ini kita memanggil notify data
        // ubah metode untuk memberi tahu adaptor kami.
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    // membuat metode untuk klik
    // tindakan menghapus tampilan gambar.
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
    // membuat metode untuk tindakan klik
    // pada item tampilan pendaur ulang untuk memperbaruinya.
    fun onNoteClick(note: Note)
}