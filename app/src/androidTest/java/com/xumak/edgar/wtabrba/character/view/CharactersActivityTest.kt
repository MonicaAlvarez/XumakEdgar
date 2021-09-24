package com.xumak.edgar.wtabrba.character.view

import android.content.res.Resources
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.xumak.edgar.wtabrba.R
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.graphics.Bitmap
import android.graphics.Canvas

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.get
import org.hamcrest.CoreMatchers


@LargeTest
@RunWith(AndroidJUnit4::class)
class CharactersActivityTest{

    @get:Rule
    val mRule: ActivityTestRule<CharactersActivity> =
        ActivityTestRule(CharactersActivity::class.java)
    @Test
    fun testFlow(){

        Thread.sleep(3000)
        onView(withId(R.id.rvResponseList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //seleccionando la 4ta posicion
        onView(withId(R.id.rvResponseList))
            .perform(actionOnItemAtPosition<CharacterAdapter.CharacterViewHolder>(4, click()))

        onView(withId(R.id.ivLikeDetail)).perform(click())
        onView(withId(R.id.ivLikeDetail)).perform(click())
        onView(withId(R.id.ivLikeDetail)).perform(click())
        onView(withId(R.id.ivLikeDetail)).perform(click())
        onView(withId(R.id.ivLikeDetail)).perform(click())
        Espresso.pressBack()

        Thread.sleep(1000)
        //seleccionando el favorito en la posición 16
        onView(withId(R.id.rvResponseList))
            .perform(actionOnItemAtPosition<CharacterAdapter.CharacterViewHolder>(16,
                clickOnViewChild(R.id.ivLikeItem)))

        Thread.sleep(2000)

        //probando busqueda
        onView(withId(R.id.action_search))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.search_src_text)).perform(typeText("Walter"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.search_src_text)).perform(clearText(), typeText("Algo"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        onView(withId(R.id.rvResponseList))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        onView(withId(R.id.search_src_text)).perform(clearText())

        Thread.sleep(2000)
    }

    @Test
    fun uncheckFavoriteItems(){
        Thread.sleep(2000)
        onView(withId(R.id.rvResponseList))
            .perform(actionOnItem<CharacterAdapter.CharacterViewHolder>(
                hasDescendant(CoreMatchers.allOf(matchesDrwable(R.drawable.ic_favorite))),
                clickOnViewChild(R.id.ivLikeItem)
            ).atPosition(0))



        Thread.sleep(2000)
    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "La vista hijo es " + viewId

        override fun perform(uiController: UiController?, view: View?) {
            click().perform(uiController, view?.findViewById<View>(viewId))
        }

    }

    fun matchesDrwable(drwableId : Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText(" drawable from resource id: ")
            description?.appendValue(drwableId)
        }

        override fun matchesSafely(target: View?): Boolean {
            val childView = (target as ViewGroup).get(2)
            if (childView !is ImageView) {
                return false
            }
            val imageView: ImageView = childView
            if (drwableId < 0) {
                return imageView.getDrawable() == null
            }
            val resources: Resources = childView.getContext().getResources()
            val expectedDrawable: Drawable = resources.getDrawable(drwableId)
                ?: return false
            val bitmap = getBitmap(imageView.getDrawable())
            val otherBitmap = getBitmap(expectedDrawable)
            return bitmap!!.sameAs(otherBitmap)
        }

    }

    private fun getBitmap(drawable: Drawable): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
}