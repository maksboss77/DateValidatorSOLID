package com.savelev.datevalidatorsolid.data

import android.content.Context
import android.content.SharedPreferences
import com.savelev.datevalidatorsolid.MainActivity
import pro.azhidkov.solid.date.domain.Date
import pro.azhidkov.solid.date.domain.DateStorage
import pro.azhidkov.solid.date.domain.DateStoringFailed

class DateSharedPreferenceStorage(private val activity: MainActivity) : DateStorage {

    lateinit var sharedPref: SharedPreferences

    init {
        initSharedPreference()
    }

    private fun initSharedPreference() {
        sharedPref = activity.getSharedPreferences(SAVE_DAY_KEY, Context.MODE_PRIVATE)
    }

    override fun loadDate(): Date? {
        try {

            val day = sharedPref.getInt(SAVE_DAY_KEY, 0)
            val month = sharedPref.getInt(SAVE_MONTH_KEY, 0)
            val year = sharedPref.getInt(SAVE_YEAR_KEY, 0)
            return Date(day, month, year)

        } catch (e: Throwable) {
            throw DateStoringFailed("Load date failed", e)
        }
    }

    override fun saveDate(date: Date) {
        try {

            val editor = sharedPref.edit()
            editor.putInt(SAVE_DAY_KEY, date.day)
            editor.putInt(SAVE_MONTH_KEY, date.month)
            editor.putInt(SAVE_YEAR_KEY, date.year)
            editor.apply()

        } catch (e: Throwable) {
            throw DateStoringFailed("Save date failed", e)
        }
    }

    companion object {
        const val SAVE_DAY_KEY = "SAVE_DAY_KEY"
        const val SAVE_MONTH_KEY = "SAVE_MONTH_KEY"
        const val SAVE_YEAR_KEY = "SAVE_YEAR_KEY"
    }
}