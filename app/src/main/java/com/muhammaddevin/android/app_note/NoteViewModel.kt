package com.muhammaddevin.android.app_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModal (application: Application) : AndroidViewModel(application) {
    // pada baris di bawah ini kita membuat variabel
    // untuk semua daftar catatan dan repositori kami
    val allNotes : LiveData<List<Note>>
    val repository : NoteRepository

    // pada baris di bawah ini kita menginisialisasi
    // dao kami, repositori, dan semua catatan
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // pada baris di bawah ini kami membuat metode baru untuk menghapus catatan. Dalam hal ini kita
    // memanggil metode delete dari repositori kita untuk menghapus catatan kita.
    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // pada baris di bawah ini kami membuat metode baru untuk memperbarui catatan. Dalam hal ini kita
    // memanggil metode pembaruan dari repositori kami untuk memperbarui catatan kami.
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    // pada baris di bawah ini kami membuat metode baru untuk menambahkan catatan baru ke database kami
    // kita memanggil metode dari repositori kita untuk menambahkan catatan baru.
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}