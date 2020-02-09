package com.michaelbukachi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alamkanak.weekview.WeekViewDisplayable
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.concurrent.ThreadLocalRandom


class MainViewModel : ViewModel() {

    val events = MutableLiveData<List<WeekViewDisplayable<Event>>>()

    fun fetchEvents(startDate: LocalDate, endDate: LocalDate) {
        events.value = prepareEvents(startDate, endDate)
    }

    fun prepareEvents(startDate: LocalDate, endDate: LocalDate): List<WeekViewDisplayable<Event>> {
        Log.i(javaClass.name, "Range is $startDate to $endDate")
        val events = mutableListOf<WeekViewDisplayable<Event>>()
        val totalEvents = 5
        val today = LocalDate.now()

        if (startDate.monthValue == today.monthValue) {
            for (i in 0..totalEvents) {
                val startTime = randomDate(today, today.plusDays(3)).atTime(randomTime(LocalTime.MIN, LocalTime.MAX))
                val event = Event("Event ${i+1}", "Location $i",
                    startTime, startTime.plusHours(2))
                events.add(event)
            }
        }

        return events
    }

    fun randomDate(
        startInclusive: LocalDate,
        endExclusive: LocalDate
    ): LocalDate {
        val startEpochDay = startInclusive.toEpochDay()
        val endEpochDay = endExclusive.toEpochDay()
        val randomDay: Long = ThreadLocalRandom
            .current()
            .nextLong(startEpochDay, endEpochDay)
        return LocalDate.ofEpochDay(randomDay)
    }

    fun randomTime(startTime: LocalTime, endTime: LocalTime): LocalTime {
        val startSeconds: Int = startTime.toSecondOfDay()
        val endSeconds: Int = endTime.toSecondOfDay()
        val randomTime = ThreadLocalRandom
            .current()
            .nextInt(startSeconds, endSeconds)
        return LocalTime.ofSecondOfDay(randomTime.toLong())
    }
}