package com.skill_factory.unit6

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.*
import com.skill_factory.unit6.databinding.ActivityMainBinding
import kotlin.random.Random

fun getExRate(): Double {
    return 75.842
}

class MainActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer
    var flag: ObservableBoolean = ObservableBoolean(true)
    lateinit var observableUser: ObservableField<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user = User(50_000, arrayListOf(Stock("Яндекс", 5000, R.drawable.yandex_icon), Stock("Mail.ru Group", 2175, R.drawable.mailru_icon)))
        observableUser = ObservableField(user)
        binding.user = observableUser
        binding.exRate = getExRate()

        binding.f = {
            flag.set(!flag.get())
            if (flag.get()) {
                countDownTimer.start()
            }else {
                countDownTimer.cancel()
            }
        }
        binding.flag = flag
        (binding.recyclerView.adapter) = StockAdapter(user.stockList)
        val r = Random(System.currentTimeMillis())
        countDownTimer = object : CountDownTimer(10_000, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                user.cash += -1000 + r.nextInt(2000)
                user.stockList.forEach {
                    it.price += (-0.1 * it.price).toInt() + r.nextInt((0.2 * it.price).toInt())
                }
            }

            override fun onFinish() {
                countDownTimer.start()
            }
        }
        countDownTimer.start()
    }
}
