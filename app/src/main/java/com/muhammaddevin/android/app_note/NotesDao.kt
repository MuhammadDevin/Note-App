package com.muhammaddevin.android.app_note

import androidx.lifecycle.LiveData
import androidx.room.*

// penjelasan untuk kelas dao.
@Dao
interface NotesDao {

    // di bawah ini adalah metode penyisipan untuk
    // menambahkan entri baru ke database kami.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (note: Note)

    // di bawah ini adalah metode untuk membaca semua catatan
    // dari database kami, kami telah menentukan kueri untuk itu.
    // di dalam kueri kita mengaturnya secara menaik
    // pesan pada baris di bawah dan kami tentukan
    // nama tabel dari mana
    // kita harus mendapatkan datanya.

    @Query("Select * from notesTabel order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>

    // metode di bawah ini digunakan untuk memperbarui catatan.
    @Update
    suspend fun update (note: Note)

    @Delete
    fun delete(note: Note)

}