package org.wiredwizards.ludum.dare.screens

import org.wiredwizards.ludum.dare.Graphics
import org.wiredwizards.ludum.dare.Input
import org.wiredwizards.ludum.dare.LD34Main

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.math.Vector2

class TitleScreen : Screen {

    private var batch: SpriteBatch? = null
    private var camera: OrthographicCamera? = null
    internal lateinit var generator: FreeTypeFontGenerator
    internal lateinit var parameter: FreeTypeFontParameter

    override fun show() {
        batch = SpriteBatch()
        camera = OrthographicCamera(getCamWidth(LD34Main.HEIGHT).toFloat(), LD34Main.HEIGHT.toFloat())
        resize(Gdx.graphics.width, Gdx.graphics.height)
        generator = FreeTypeFontGenerator(Gdx.files.internal("DroidSans-Bold.ttf"))
        parameter = FreeTypeFontParameter()
        if (!LD34Main.titleMusic!!.isPlaying) {
            LD34Main.titleMusic!!.play()
            LD34Main.gameMusic!!.stop()
        }
    }

    override fun render(delta: Float) {
        Graphics.clear(0f, 0.05f, 0f)
        /*Update*/run { camera!!.update() }
        batch!!.projectionMatrix = camera!!.combined
        batch!!.begin()
        /*Render*/run {
            batch!!.draw(tex, -(tex.width * 2) / 2 + Input.getX(camera) / 30f, LD34Main.HEIGHT / 2 - tex.height * 2 + Input.getY(camera) / 30f,
                    (tex.width * 2).toFloat(), (tex.height * 2).toFloat())

            if (font == null) {
                parameter.size = 40 * Gdx.graphics.height / LD34Main.HEIGHT
                font = generator.generateFont(parameter)
                font!!.data.setScale(camera!!.viewportHeight / Gdx.graphics.height)
            }
            val layout = GlyphLayout()
            // Play
            val play = "Play"
            layout.setText(font!!, play)
            font!!.draw(batch, play, -layout.width / 2, 0f)
            if (Input.touchGlyph(layout, Vector2(-layout.width / 2, 0f), camera) == 3) {
                LD34Main.setTheScreen(GameScreen())
            }
            // Credits
            val credits = "Credits"
            layout.setText(font!!, credits)
            font!!.draw(batch, credits, -layout.width / 2, (-LD34Main.HEIGHT / 10 * 3).toFloat())
            if (Input.touchGlyph(layout, Vector2(-layout.width / 2, (-LD34Main.HEIGHT / 10 * 3).toFloat()), camera) == 3) {
                LD34Main.setTheScreen(Credits())
            }
        }
        batch!!.end()
    }

    override fun resize(width: Int, height: Int) {
        if (LD34Main.WIDTH.toFloat() / LD34Main.HEIGHT < width.toFloat() / height) {
            camera!!.viewportHeight = LD34Main.HEIGHT.toFloat()
            camera!!.viewportWidth = getCamWidth(LD34Main.HEIGHT).toFloat()
        } else {
            camera!!.viewportHeight = getCamHeight(LD34Main.WIDTH).toFloat()
            camera!!.viewportWidth = LD34Main.WIDTH.toFloat()
        }
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch!!.dispose()
    }

    companion object {
        val tex = Texture("title2.png")
        val background = Texture("background_title.png")
        var font: BitmapFont? = null

        /**
         * Gets the width of a camera with specified height

         * @param camHeight
         * *            The height of the camera.
         * *
         * @return The width of the camera.
         */
        fun getCamWidth(camHeight: Int): Int {
            // cam.height / cam.width = width / height
            // cam.width = (width * cam.height) / height
            val camWidth = Gdx.graphics.width * camHeight / Gdx.graphics.height
            return camWidth
        }

        /**
         * Gets the height of a camera with specified height

         * @param camWidth
         * *            The width of the camera.
         * *
         * @return The height of the camera.
         */
        fun getCamHeight(camWidth: Int): Int {
            // cam.height / cam.width = width / height
            // cam.width = (width * cam.height) / height
            val camHeight = Gdx.graphics.height * camWidth / Gdx.graphics.width
            return camHeight
        }
    }

}
