package org.wiredwizards.ludum.dare.objects

import java.util.Random

import org.wiredwizards.ludum.dare.GameObject
import org.wiredwizards.ludum.dare.LD34Main
import org.wiredwizards.ludum.dare.screens.GameScreen

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable
import org.wiredwizards.ludum.dare.objects.blocks.MoveBlock
import org.wiredwizards.ludum.dare.objects.blocks.PuffBlock
import org.wiredwizards.ludum.dare.objects.blocks.SpinBlock
import org.wiredwizards.ludum.dare.objects.blocks.StandardBlock

abstract class Block(texX: Int) : GameObject(
        Block.rand.nextInt((GameScreen.camera.viewportWidth / Block.size).toInt() + 1) * Block.size - GameScreen.camera.viewportWidth / 2f,
        GameScreen.camera.position.y + LD34Main.HEIGHT * 2 + Block.rand.nextInt(4) * Block.size,
        Block.size,
        Block.size
) {

    val tex: TextureRegion
    var time = 0f
    var flip: Boolean = false

    init {
        tex = TextureRegion(sheet, 32 * texX, 32 * GameScreen.currentLayer, 32, 32)
        flip = rand.nextBoolean()
    }

    override fun render(batch: SpriteBatch, delta: Float) {
        time += delta
        if (time > 10) {
            GameScreen.objects.remove(this)
        }
        draw(batch, delta)
    }

    open fun draw(batch: SpriteBatch, delta: Float) {
        var h = (Math.sin((LD34Main.gameTime + x / LD34Main.WIDTH) * 2.5 * 2.0) * (size * 0.2f)).toFloat()
        h = if (h > 0) h else 0F
        batch.draw(tex, x + (if (flip) size else 0f), y, if (flip) -size else size, size + h)
    }

    companion object {
        @JvmField val sheet = Texture("spritesheet.png")
        val rand = Random()
        val size = 64f

        @JvmStatic fun create(): Block? {
            val type = rand.nextInt(4)
            return when (GameScreen.currentLayer) {
                1 -> when (type) {
                    0 -> MoveBlock(type)
                    else -> StandardBlock(type)
                }
                3 -> when (type) {
                    0 -> SpinBlock(type)
                    else -> StandardBlock(type)
                }
                4 -> PuffBlock(0)
                5 -> when (type) {
                    2 -> SpinBlock(type)
                    else -> StandardBlock(type)
                }
                6 -> MoveBlock(type)
                else -> StandardBlock(type)
            }
        }
    }

}
