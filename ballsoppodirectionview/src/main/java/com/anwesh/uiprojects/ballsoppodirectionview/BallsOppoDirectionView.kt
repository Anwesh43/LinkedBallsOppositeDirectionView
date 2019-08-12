package com.anwesh.uiprojects.ballsoppodirectionview

/**
 * Created by anweshmishra on 12/08/19.
 */

import android.app.Activity
import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.content.Context

val nodes : Int = 5
val balls : Int = 2
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#0D47A1")
val scGap : Float = 2.9f
val scDiv : Double = 0.51
val backColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 2.9f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawOppositeBalls(i : Int, h : Float, size : Float, sc : Float, paint : Paint) {
    val r : Float = size / rFactor
    save()
    translate(0f, (h - r) * (1 - sc) * (1 - 2 * i))
    drawCircle(0f, 0f, r, paint)
    restore()
}

fun Canvas.drawBODNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(gap * (i + 1), h / 2)
    rotate(90f * sc1)
    drawLine(0f, -size, 0f, size, paint)
    for (j in 0..1) {
        save()
        translate(0f, size * (1f - 2 * j))
        drawOppositeBalls(i, h / 2 - size, size, sc1, paint)
        restore()
    }
    restore()
}

class BallsOppoDirectionView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}