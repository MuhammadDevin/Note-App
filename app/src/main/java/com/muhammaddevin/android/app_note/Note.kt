package com.muhammaddevin.android.app_note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// pada baris di bawah ini kami menentukan nama tabel kami
@Entity(tableName = "notesTabel")

// pada baris di bawah ini kami menentukan info kolom kami
// dan di dalamnya kita melewati nama kolom kita
class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: String
) {

    // pada baris di bawah ini kami menentukan kunci kami dan
    // lalu auto generate sebagai true dan kita
    // menentukan nilai awalnya sebagai 0
    @PrimaryKey(autoGenerate = true)
    var id = 0
}