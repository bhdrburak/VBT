package com.bhdrburak.vbtexample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView

class ButtonWithProgressBar : FrameLayout {

    private lateinit var rootLayout: FrameLayout
    private lateinit var buttonTextView: TextView
    private lateinit var progressBar: ProgressBar
    private var text = ""
    private var size = ButtonWithProgressBarSize.DEFAULT
    private var isButtonEnabled = true
    private var backgroundColor = 0
    private var textColor = 0
    private var showingLoader = false


    constructor(context: Context) : super(context) {
        initButtonWithProgressBar(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        getStuffFromXml(attributeSet, context)
        initButtonWithProgressBar(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        getStuffFromXml(attributeSet, context)
        initButtonWithProgressBar(context)
    }

    private fun getStuffFromXml(attributeSet: AttributeSet?, context: Context) {
        val data = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonWithProgressBar)
        text = data.getString(R.styleable.ButtonWithProgressBar_text).toString()
        isButtonEnabled = data.getBoolean(R.styleable.ButtonWithProgressBar_enabled, true)
        backgroundColor = data.getColor(
            R.styleable.ButtonWithProgressBar_backgroundColor,
            context.getColor(R.color.blue)
        )
        textColor = data.getColor(
            R.styleable.ButtonWithProgressBar_custom_text_color,
            context.getColor(R.color.white)
        )
        when (data.getInt(R.styleable.ButtonWithProgressBar_size, 0)) {
            0 -> ButtonWithProgressBarSize.DEFAULT
            1 -> ButtonWithProgressBarSize.MINI
            2 -> ButtonWithProgressBarSize.LARGE
            3 -> ButtonWithProgressBarSize.LARGEST
        }

        data.recycle()
    }

    private fun initButtonWithProgressBar(context: Context) {

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        LayoutInflater.from(context).inflate(R.layout.button_with_progress_bar, this, true)
        rootLayout = findViewById(R.id.root_layout)
        buttonTextView = findViewById(R.id.buttonText)
        progressBar = findViewById(R.id.progressBar)


        if (text.isNotEmpty()) {
            buttonTextView.text = text
        }

        minimumWidth = resources.getDimension(R.dimen.button_mini_width).toInt()

        if (ButtonWithProgressBarSize.MINI == size) {
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_mini_height).toInt()
            )
            rootLayout.layoutParams.height =
                resources.getDimension(R.dimen.button_mini_height).toInt()
        } else if (ButtonWithProgressBarSize.LARGE == size) {
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_large_height).toInt()
            )
            rootLayout.layoutParams.height =
                resources.getDimension(R.dimen.button_large_height).toInt()
        } else if (ButtonWithProgressBarSize.LARGEST == size) {
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_largest_height).toInt()
            )
            rootLayout.layoutParams.height =
                resources.getDimension(R.dimen.button_largest_height).toInt()
        } else {
            setMeasuredDimension(
                measuredWidth,
                resources.getDimension(R.dimen.button_large_height).toInt()
            )
            rootLayout.layoutParams.height =
                resources.getDimension(R.dimen.button_large_height).toInt()
        }

        rootLayout.setPadding(
            resources.getDimension(R.dimen.margin_10).toInt(),
            0,
            resources.getDimension(R.dimen.button_mini_height).toInt(),
            0
        )
        refreshDrawableState()
    }

    fun showLoader(){
        showingLoader = true
        buttonTextView.visibility = GONE
        progressBar.visibility = VISIBLE
    }

    fun hideLoader(){

        showingLoader = false
        buttonTextView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    fun setEnable(enable : Boolean){
        isEnabled = enable
    }

    fun setText(str : String){
        if (str.isNotEmpty()){
            if (!showingLoader){
                buttonTextView.visibility = VISIBLE
            }
            buttonTextView.text = str

        }
        text = str
    }


    enum class ButtonWithProgressBarSize {
        DEFAULT, MINI, LARGE, LARGEST
    }

}