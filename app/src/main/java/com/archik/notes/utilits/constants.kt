package com.archik.notes.utilits

import com.archik.notes.MainActivity
import com.archik.notes.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var APP_ACTIVITY:MainActivity
lateinit var REPOSITORY:DatabaseRepository

lateinit var EMAIL:String
lateinit var PASSWORD:String

const val TYPE_DATABESE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"

lateinit var AUTH:FirebaseAuth
lateinit var CURRENT_ID:String
lateinit var REF_DATABASE:DatabaseReference