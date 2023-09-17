package com.example.imagesearchpage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imagesearchpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var fragstate: Boolean = true
    val searchResultFragment = searchResultFragment()
    val myArchiveFragment = myArchiveFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.topbarSearchButton.setOnClickListener {
//            binding.topbarSearch.text
        }
        binding.bottombarLeftButton.setOnClickListener {
            val direction = "Left"
            bottombarButton(direction)
        }
        binding.bottombarRightButton.setOnClickListener {
            val direction = "Right"
            bottombarButton(direction)
        }
    }

    fun bottombarButton(direction: String) {
        if (direction == "Left") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#BB00DA"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#BB00DA"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#666665"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#666665"))
            fragstate = true
            supportFragmentManager.beginTransaction().replace(R.id.Fragment,searchResultFragment).commit()
        } else if (direction == "Right") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#666665"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#666665"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#BB00DA"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#BB00DA"))
            fragstate = false
            supportFragmentManager.beginTransaction().replace(R.id.Fragment,myArchiveFragment).commit()
        }
    }
}

/*
1. binding과 gradle부터..
[레이아웃 만들기]
2. 레이아웃 만들기 : 2개의 프래그먼트와, 프래그먼트를 전환할 수 있는 버튼 추가
2. 레이아웃 만들다, 뒷배경 사각형 주는거 까먹어서 구글링으로 검색하고 shape에 대해 정리함
2. button은 색이 맘대로 되질 않아서 androidx.appcompat.widget.AppCompatButton로 수정
2. 하단바의 검색결과와 내 보관함에 대해, 영역을 반씩 차지하게끔 하는 방법을 몰랐다가 Guideline에 대해 알게됨.
2. 그리고 그 Guideline을
2. 그리고 그리드뷰도 추가해야됨
2. 코틀린 코드에 버튼 클릭시 이미지와 텍스트#BB00DA(보라색,현재 탭) #666665(회색,아닌 탭)
 */