package com.example.greetingcard

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExplicitLaunchTest {

    private lateinit var device: UiDevice
    private val packageName = "com.example.greetingcard"

    @Before
    fun startFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait until the app is open
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 3000)
    }

    @Test
    fun testLaunchExplicit() {
        // Tap the "Start Activity Explicitly" button
        val startButton = device.wait(Until.findObject(By.text("Start Activity Explicitly")), 3000)
        startButton?.click()

        // Wait for the second screen
        device.wait(Until.hasObject(By.pkg(packageName)), 3000)

        // Look for a challenge
        val challenge = device.findObject(UiSelector().textContains("Device Fragmentation"))
        assert(challenge.exists())
    }
}
