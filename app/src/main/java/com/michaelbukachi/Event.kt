package com.michaelbukachi

import com.alamkanak.weekview.WeekViewDisplayable
import com.alamkanak.weekview.WeekViewEvent
import com.alamkanak.weekview.threetenabp.setEndTime
import com.alamkanak.weekview.threetenabp.setStartTime
import org.threeten.bp.LocalDateTime

data class Event(
    val title: String,
    val location: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) :  WeekViewDisplayable<Event> {

    override fun toWeekViewEvent(): WeekViewEvent<Event> {
        val style = WeekViewEvent.Style.Builder()
            .build()

        return WeekViewEvent.Builder(this)
            .setTitle(title)
            .setLocation(location)
            .setStartTime(startTime)
            .setEndTime(endTime)
            .setAllDay(false)
            .setStyle(style)
            .build()
    }
}