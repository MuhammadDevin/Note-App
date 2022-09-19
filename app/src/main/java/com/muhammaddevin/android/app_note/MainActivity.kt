package com.muhammaddevin.android.app_note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    // pada baris di bawah ini kita membuat variabel
    // untuk tampilan pendaur ulang, teks keluar, tombol, dan model tampilan.
    lateinit var viewModel: NoteViewModal
    lateinit var noteRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // pada baris di bawah ini kita menginisialisasi
        // semua variabel kita.
        noteRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        // pada baris di bawah ini kita sedang mengatur tata letak
        // manajer ke tampilan pendaur ulang kami.
        noteRV.layoutManager = LinearLayoutManager(this)

        // pada baris di bawah ini kita menginisialisasi kelas adaptor kita.
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        // pada baris di bawah ini kami sedang mengatur
        // adaptor ke tampilan pendaur ulang kami.
        noteRV.adapter = noteRVAdapter

        // pada baris di bawah kita adalah
        // menginisialisasi modal tampilan kita.
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // pada baris di bawah ini kita memanggil semua metode catatan
        // dari kelas modal tampilan kami ke pengamat perubahan pada daftar.
        viewModel.allNotes.observe(this, Observer { List ->
            List?.let {
                // pada baris di bawah ini kami memperbarui daftar kami.
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            // menambahkan pendengar klik untuk tombol luar biasa
            // dan membuka maksud baru untuk menambahkan catatan baru.
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        // membuka maksud baru dan meneruskan data ke sana.
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        // dalam metode klik catatan yang kita panggil hapus
        // metode dari modal tampilan kami untuk menghapus not.
        viewModel.deleteNote(note)
        // menampilkan pesan bersulang
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}