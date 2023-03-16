package com.archik.notes.utilits

import com.archik.notes.MainActivity
import com.archik.notes.database.DatabaseRepository

lateinit var APP_ACTIVITY:MainActivity
lateinit var REPOSITORY:DatabaseRepository

lateinit var EMAIL:String
lateinit var PASSWORD:String

const val TYPE_DATABESE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"