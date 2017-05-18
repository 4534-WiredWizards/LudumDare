package org.wiredwizards.ludum.dare

import org.wiredwizards.ludum.dare.screens.Splash
import org.wiredwizards.ludum.dare.screens.TitleScreen

import com.badlogic.gdx.Audio
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music

open class LD34Main : Game() {

    override fun create() {
        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("title.mp3"))
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"))
        titleMusic!!.isLooping = true
        gameMusic!!.isLooping = true
        setScreen(Splash())
        println("Hello from Kotlin!")
    }

    override fun render() {
        var delta = Gdx.graphics.deltaTime * if (LD34Main.GOD && Gdx.input.isTouched) GOD_SPEED else 1F
        gameTime += delta
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            setScreen(TitleScreen())
        }
        super.render()
    }

    companion object {

        @JvmField val WIDTH = 480
        @JvmField val HEIGHT = 720
        @JvmField val GOD = false
        @JvmField val GOD_SPEED = 48F

        @JvmField var gameTime = 0f
        @JvmField var titleMusic: Music? = null
        @JvmField var gameMusic: Music? = null

        @JvmStatic fun setTheScreen(screen: Screen) {
            (Gdx.app.applicationListener as Game).screen = screen
        }
    }
}
