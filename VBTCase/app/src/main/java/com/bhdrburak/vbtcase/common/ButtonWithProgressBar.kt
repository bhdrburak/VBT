package com.bhdrburak.vbtcase.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bhdrburak.vbtcase.R

class ButtonWithProgressBar : FrameLayout {


    private lateinit var rootLayout: FrameLayout
    private lateinit var buttonText: TextView
    private lateinit var progressBar: ProgressBar
    private var text = ""
    private var size = ButtonWithprogressBarSize.DEFAULT
    private var isButtonEnabled = true
    private var backgroundColor = 0
    private var textColor = 0
    private var showingLoader = false


    enum class ButtonWithprogressBarSize {
        DEFAULT, MINI, LARGE, LARGEST
    }


    constructor(context: Context) : super(context) {
        initButtonWithProgressBar(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        getAttrsFromXml(attributeSet, context)
        initButtonWithProgressBar(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        getAttrsFromXml(attributeSet, context)
        initButtonWithProgressBar(context)
    }

    private fun getAttrsFromXml(attributeSet: AttributeSet?, context: Context) {
        val data = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonWithProgressBar)

        text = data.getString(R.styleable.ButtonWithProgressBar_text).toString()
        isButtonEnabled = data.getBoolean(R.styleable.ButtonWithProgressBar_enabled, true)
        backgroundColor = data.getColor(
            R.styleable.ButtonWithProgressBar_backgroundColor,
            context.getColor(R.color.orange)
        )
        textColor = data.getColor(
            R.styleable.ButtonWithProgressBar_custom_text_color,
            context.getColor(R.color.white)
        )

        when (data.getInt(R.styleable.ButtonWithProgressBar_size, 0)) {
            0 -> ButtonWithprogressBarSize.DEFAULT
            1 -> ButtonWithprogressBarSize.MINI
            2 -> ButtonWithprogressBarSize.LARGE
            3 -> ButtonWithprogressBarSize.LARGEST
        }

        data.recycle()

    }

    private fun initButtonWithProgressBar(context: Context) {

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        LayoutInflater.from(context).inflate(R.layout.button_with_progress_bar, this, true)

        rootLayout = findViewById(R.id.rootLayout)
        buttonText = findViewById(R.id.buttonText)
        progressBar = findViewById(R.id.progressBar)

        if (text.isNotEmpty()){
            buttonText.text = text
        }

        minimumWidth = resources.getDimension(R.dimen.button_mini_width).toInt()

        if (ButtonWithprogressBarSize.MINI == size){
            setMeasuredDimension(measuredWidth, resources.getDimension(R.dimen.button_mini_height).toInt())
            rootLayout.layoutParams.height = resources.getDimension(R.dimen.button_mini_height).toInt()
        } else if (ButtonWithprogressBarSize.LARGE == size){
            setMeasuredDimension(measuredWidth, resources.getDimension(R.dimen.button_large_height).toInt())
            rootLayout.layoutParams.height = resources.getDimension(R.dimen.button_large_height).toInt()
        } else if (ButtonWithprogressBarSize.LARGE == size){
            setMeasuredDimension(measuredWidth, resources.getDimension(R.dimen.button_largest_height).toInt())
            rootLayout.layoutParams.height = resources.getDimension(R.dimen.button_largest_height).toInt()
        }

        rootLayout.setPadding(10, 0, 10, 0)

        refreshDrawableState()

    }

    fun setEnable(enable: Boolean){
        isEnabled = enable
    }


    fun showLoader(){
        showingLoader = true
        buttonText.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    fun hideLoader(){
        showingLoader = false
        buttonText.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    fun setText(str : String){

        if (str.isNotEmpty()){
            if (!showingLoader){
                buttonText.visibility = VISIBLE
            }
            buttonText.text = str
        }
        text = str
    }


}