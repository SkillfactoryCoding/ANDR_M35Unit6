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
        val user1 = User(50_000, arrayListOf(Stock("Яндекс", 5000, R.drawable.yandex_icon), Stock("Mail.ru Group", 2175, R.drawable.mailru_icon)))
        val user2 = User(12_000,arrayListOf(Stock("Mail.ru Group", 2175, R.drawable.mailru_icon),Stock("Яндекс", 5000, R.drawable.yandex_icon)))
        observableUser = ObservableField(user1)
        binding.user = observableUser
        binding.exRate = getExRate()
        //Change this for test data binding
        observableUser.set(user2)
        binding.f = {
            flag.set(!flag.get())
            if (flag.get()) {
                countDownTimer.start()
            }else {
                countDownTimer.cancel()
            }
        }
        binding.flag = flag
        (binding.recyclerView.adapter) = observableUser.get()?.stockList?.let { StockAdapter(it) }
        val r = Random(System.currentTimeMillis())
        countDownTimer = object : CountDownTimer(10_000, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val u: User? = observableUser.get()
                if(u != null) {
                    u.cash += -1000 + r.nextInt(2000)
                    u.stockList.forEach {
                        it.price += (-0.1 * it.price).toInt() + r.nextInt((0.2 * it.price).toInt())
                    }
                }
            }

            override fun onFinish() {
                countDownTimer.start()
            }
        }
        countDownTimer.start()
    }
}
