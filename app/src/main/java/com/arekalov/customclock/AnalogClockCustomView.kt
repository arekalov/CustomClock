package com.arekalov.customclock

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.min

class AnalogClockCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defResAttrs: Int = 0
) : View(context, attrs, defStyleAttr, defResAttrs) {

    companion object {
        const val DEFAULT_SIZE = 230f
        const val DEFAULT_BORDER = DEFAULT_SIZE * 0.108f
    }

    private lateinit var paintSecondHand: Paint
    private lateinit var paintMinuteHand: Paint
    private lateinit var paintHourHand: Paint
    private lateinit var paintBorder: Paint
    private lateinit var paintDigit: Paint
    private lateinit var paintBackground: Paint
    private var backgroundColor = 0xFFE8E4E1.toInt()
    private var digitColor =  Color.BLACK
    private var borderColor =  Color.BLACK
    private var borderWidth = DEFAULT_BORDER
    private var secondHandColor =  Color.BLACK
    private var minuteHandColor =  Color.BLACK
    private var hourHandColor =  Color.BLACK
    private var isSecondHandVisible = true
    private var numbers = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private var DEFAULT_SIZE_DP = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, DEFAULT_SIZE, resources.displayMetrics
    ).toInt()
    private var DEFAULT_BOARDER_PX = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, borderWidth, resources.displayMetrics
    ).toInt()
    private var radius = width / 2F - DEFAULT_BOARDER_PX
    private var center = width / 2F
//    init {
//        if (attrs != null) {
//            val attrsSet = context.obtainStyledAttributes(attrs, R.styleable.MyClock)
//            backgroundColor = attrsSet.getColor(R.styleable.MyClock_backgroundColor, backgroundColor)
//            digitColor = attrsSet.getColor(R.styleable.MyClock_digitsColor, digitColor)
//            borderColor = attrsSet.getColor(R.styleable.MyClock_borderColor, borderColor)
//            borderWidth = attrsSet.getInteger(R.styleable.MyClock_borderWidth, borderWidth)
//            secondHandColor = attrsSet.getColor(R.styleable.MyClock_secondHandColor, secondHandColor)
//            minuteHandColor = attrsSet.getColor(R.styleable.MyClock_minuteHandColor, minuteHandColor)
//            hourHandColor = attrsSet.getColor(R.styleable.MyClock_hourHandColor, hourHandColor)
//            isSecondHandVisible = attrsSet.getBoolean(R.styleable.MyClock_isSecondHandVisible, isSecondHandVisible)
//        }
//    }
private fun initPainters() {
    paintBackground = Paint().apply {
        color = backgroundColor
        Paint.Style.FILL
        isAntiAlias = true
    }
    paintBorder = Paint().apply {
        color = borderColor
        style = Paint.Style.STROKE
        strokeWidth = DEFAULT_BOARDER_PX.toFloat()
        isAntiAlias = true
    }
    paintDigit = Paint().apply {
        color = hourHandColor
        Paint.Style.FILL
        isAntiAlias = true
        textSize = DEFAULT_SIZE_DP.toFloat() / 20F
    }
    paintHourHand = Paint().apply {
        color = hourHandColor
        Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = DEFAULT_SIZE_DP * 0.01F
    }

    paintMinuteHand = Paint().apply {
        color = minuteHandColor
        Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = DEFAULT_SIZE_DP * 0.005F
    }
    paintSecondHand = Paint().apply {
        color = secondHandColor
        Paint.Style.FILL
        isAntiAlias = true
        strokeWidth = DEFAULT_SIZE_DP * 0.002F
    }
}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(widthMeasureSpec)
            MeasureSpec.AT_MOST -> DEFAULT_SIZE_DP
            else -> DEFAULT_SIZE_DP
        }
        val height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.AT_MOST -> DEFAULT_SIZE_DP
            else -> DEFAULT_SIZE_DP
        }
        val size = min(height, width)
        DEFAULT_SIZE_DP = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, size.toFloat(), resources.displayMetrics
        ).toInt()
        DEFAULT_BOARDER_PX = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, size * 0.02f, resources.displayMetrics
        ).toInt()
        radius = size / 2f - DEFAULT_BOARDER_PX
        center = size / 2f
        initPainters()
        setMeasuredDimension(size, size)
    }
}