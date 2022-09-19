package com.muhammaddevin.android.app_note

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object{
        // Singleton mencegah banyak
        // contoh pembukaan database di
        // waktu yang sama.

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            // jika INSTANCE bukan null, kembalikan,
            // jika ya, buat databasenya

            return INSTANCE ?: synchronized(this){
                val instace = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instace
                // return instance
                instace
            }
        }
    }
}