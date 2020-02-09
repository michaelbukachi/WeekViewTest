package com.michaelbukachi

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.threetenabp.setOnLoadMoreListener
import org.threeten.bp.LocalDate

inline fun <reified T : View> Activity.lazyView(
    @IdRes viewId: Int
): Lazy<T> = lazy { findViewById<T>(viewId) }


class MainActivity : AppCompatActivity() {

    private val weekView: WeekView<Event> by lazyView(R.id.weekview)

    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.events.observe(this, Observer {
            weekView.submit(it)
        })


        weekView.setOnLoadMoreListener { startDate: LocalDate, endDate: LocalDate ->
            viewModel.fetchEvents(startDate, endDate)
        }
    }
}