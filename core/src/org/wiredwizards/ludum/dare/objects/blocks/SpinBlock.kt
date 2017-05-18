package org.wiredwizards.ludum.dare.objects.blocks

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.wiredwizards.ludum.dare.LD34Main
import org.wiredwizards.ludum.dare.objects.Block

/**
 * Created by brandon on 5/17/2017.
 */
class SpinBlock(type: Int) : Block(type) {

    val timeOffset = Block.rand.nextFloat() + Block.rand.nextInt(10)

    override fun draw(batch: SpriteBatch, delta: Float) {
        //batch.draw(tex, x + (if (flip) size else 0f), y, if (flip) -size else size, size + Math.abs(Math.sin(LD34Main.gameTime.toDouble() * 2.5 * 2.0).toFloat() * (size * 0.2f)))
        val t = time + timeOffset
        batch.draw(tex, x, y, size / 2, size / 2, size, size, 1F, 1F, Math.cos((t * 2).toDouble()).toFloat() * 720)
    }

}