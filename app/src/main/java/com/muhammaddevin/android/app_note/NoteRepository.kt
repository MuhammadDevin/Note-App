package com.muhammaddevin.android.app_note

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {
    // pada baris di bawah ini kami membuat variabel untuk daftar kami
    // dan kami mendapatkan semua catatan dari kelas DAO kami.

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    // pada baris di bawah ini kita membuat metode insert
    // untuk menambahkan catatan ke database kami.
    suspend fun insert(note: Note){
        notesDao.insert(note)
    }

    // pada baris di bawah ini kita membuat metode update
    // untuk menambahkan catatan ke database kami.
    suspend fun update(note: Note){
        notesDao.update(note)
    }

    // pada baris di bawah ini kita membuat metode delete
    // untuk menambahkan catatan ke database kami.
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
}