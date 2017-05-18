package org.wiredwizards.ludum.dare.objects.blocks

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.wiredwizards.ludum.dare.LD34Main
import org.wiredwizards.ludum.dare.objects.Block
import java.util.*

/**
 * Created by brandon on 5/17/2017.
 */
class PuffBlock(type: Int) : Block(type) {

    val puffs = LinkedList<Puff>()
    val puffs2Rem = LinkedList<Puff>()
    var puffTime = 0F

    override fun draw(batch: SpriteBatch, delta: Float) {
        for (puff in puffs.reversed()) {
            puff.draw(batch, delta)
        }
        for (puff in puffs2Rem) {
            puffs.remove(puff)
        }
        puffs2Rem.clear()
        puffTime += delta
        if (puffTime > maxPuffTime / puffNum)
            puffs.add(Puff(x, y))
    }

    inner class Puff(var x: Float, var y: Float) {

        var time = 0F
        val dir = rand.nextDouble() + rand.nextInt(31415)
        val myDriftSpeed = rand.nextInt(driftSpeed)

        fun draw(batch: SpriteBatch, delta: Float) {
            time += delta
            x += (Math.cos(dir) * delta).toFloat() * myDriftSpeed
            y += (Math.sin(dir) * delta).toFloat() * myDriftSpeed
            batch.draw(tex, x - size * scale * 0.5F, y - size * scale * 0.5F, size * scale, size * scale)
            if (time > maxPuffTime)
                puffs2Rem.add(this)
        }

    }

    companion object {
        val rand = Random()
        val scale = 0.6F
        val maxPuffTime = 1F
        val puffNum = 5 // Number of puffs that will appear on screen
        val driftSpeed = 70
    }

}