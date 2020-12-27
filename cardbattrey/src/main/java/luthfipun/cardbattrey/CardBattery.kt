package luthfipun.cardbattrey

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CardBattery(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_RADIUS = 10
        private const val DEFAULT_BG = Color.GRAY
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_PROGRESS_COLOR = Color.BLUE
        private const val DEFAULT_SHOW_PROGRESS = true
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bgColor = DEFAULT_BG
    private var radius = DEFAULT_RADIUS
    private var progress = DEFAULT_PROGRESS
    private var progressColor = DEFAULT_PROGRESS_COLOR
    private var loading = 0
    private var showProgress = DEFAULT_SHOW_PROGRESS

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {

        val typeArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CardBattery, 0 , 0)

        bgColor = typeArray.getColor(R.styleable.CardBattery_cbBackground, DEFAULT_BG)
        radius = typeArray.getInt(R.styleable.CardBattery_cbRadius, DEFAULT_RADIUS)
        progress = typeArray.getInt(R.styleable.CardBattery_cbProgress, DEFAULT_PROGRESS)
        progressColor = typeArray.getColor(R.styleable.CardBattery_cbProgressColor, DEFAULT_PROGRESS_COLOR)
        showProgress = typeArray.getBoolean(R.styleable.CardBattery_cbShowProgress, DEFAULT_SHOW_PROGRESS)

        typeArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawProgress(canvas)
    }

    private fun drawProgress(canvas: Canvas?) {
        if (showProgress){
            paint.style = Paint.Style.FILL
            paint.color = progressColor

            val rectF = RectF()
            rectF.set(
                0f,
                calculateProgress(),
                width.toFloat(),
                height.toFloat()
            )

            val corners = floatArrayOf(
                calculateRoundTop(), calculateRoundTop(),   // Top left radius in px
                calculateRoundTop(), calculateRoundTop(),   // Top right radius in px
                radius.toFloat(), radius.toFloat(),     // Bottom right radius in px
                radius.toFloat(), radius.toFloat()      // Bottom left radius in px
            )

            val path = Path()
            path.addRoundRect(rectF, corners, Path.Direction.CW)
            canvas?.drawPath(path, paint)
        }
    }

    private fun calculateProgress(): Float {
        loading = ((progress / 100.0) * height).toInt() // 0 - height
        if (loading < radius) {
            return ((height - loading) - radius).toFloat()
        }

        return (height - loading).toFloat()
    }

    private fun drawBackground(canvas: Canvas?) {

        paint.color = bgColor
        paint.style = Paint.Style.FILL

        val rectF = RectF()
        rectF.set(0f,
            0f,
            width.toFloat(),
            height.toFloat())

        canvas?.drawRoundRect(
            rectF,
            radius.toFloat(), radius.toFloat(), paint
        )
    }

    fun setProgress(int: Int) {
        if (progress != int){
            progress = int
            invalidate()
        }
    }

    private fun calculateRoundTop(): Float {
        if (loading > height - radius){
            return ((radius - (height - loading)).toFloat())
        }

        return 0f
    }

    fun setRadius(int: Int) {
        if (radius != int){
            radius = int
            invalidate()
        }
    }

    fun setBackground(colorInt: Int){
        if (bgColor != colorInt) {
            bgColor = colorInt
            invalidate()
        }
    }

    fun setProgressColor(colorInt: Int) {
        if (progressColor != colorInt) {
            progressColor = colorInt
            invalidate()
        }
    }

    fun getProgress(): Int {
        return progress
    }

    fun getRadius(): Int {
        return radius
    }

    fun setShowProgress(boolean: Boolean) {
        showProgress = boolean
        invalidate()
    }

    fun isShowProgress(): Boolean {
        return showProgress
    }
}